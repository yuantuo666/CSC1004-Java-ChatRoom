/**
 * WebSocket
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/02/25
 */
package com;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/ws/{token}")
public class WebSocket {
    private static final CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();

    /**
     * save session
     */
    private static final Map<String, Session> SESSION_POOL = new HashMap<>();

    private static String checkJWT(String token){
        // check jwt
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("NeverGonnaGiveUp");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")// specify a specific claim validations
                    .build();// reusable verifier instance
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return "";
        }
        return decodedJWT.getClaim("username").asString();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "token") String token) throws IOException {
        String userId = checkJWT(token);
        if (userId.equals("")) {
            session.getAsyncRemote().sendText("Invalid Token");
            session.close();
            return;
        }

        try {
            SESSIONS.add(session);
            SESSION_POOL.put(userId, session);
            log.info("[WebSocket] New connect: "+userId+", size: " + SESSIONS.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        try {
            SESSIONS.remove(session);
            log.info("[WebSocket] New disconnect, size: " + SESSIONS.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam(value = "token") String token) throws IOException {
        String userId = checkJWT(token);
        if (userId.equals("")) {return;}

        log.info("[WebSocket] "+userId+": " + message);
        sendAllMessage(message);
    }


    public void sendAllMessage(String message) {
        log.info("[WebSocket] Broadcast message: " + message);
        for (Session session : SESSIONS) {
            try {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String userId, String message) {
        Session session = SESSION_POOL.get(userId);
        if (session != null && session.isOpen()) {
            try {
                synchronized (session) {
                    log.info("[WebSocket] Private message: " + message);
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
