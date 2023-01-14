/**
 * User
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com.implemention;

import com.Send;

import java.io.IOException;
import java.net.Socket;

import static com.Server.clientMap;

public class User {
    public static String getUser(Socket client) {
        String user = "";
        for(String key : clientMap.keySet()) {
            if (clientMap.get(key).equals(client)) {
                user = key;
            }
        }
//        if(user == ""){
//            Send.format(client,"SysMsg","You haven't register!");
//        }
        return user;
    }

    public static void register(String user, Socket client) throws IOException {
        System.out.println("[Login] "+user);
        clientMap.put(user, client);
        System.out.println("Online User: " + clientMap.size());

        Send.format(client,"SysMsg","Register success! Happy Chatting~");
    }

}
