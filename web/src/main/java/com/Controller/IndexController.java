/**
 * IndexController
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/02/25
 */
package com.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    private static String genToken(String userId){
        try {
            Algorithm algorithm = Algorithm.HMAC256("NeverGonnaGiveUp");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", userId)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            return "";
        }
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam Map<String,Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        // check login
        try{
            if (username.equals("") || password.equals("")) throw new Exception("Invalid username or password");
            // check login


        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("msg", e.getMessage());
            return response;
        }
        // return token
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("msg", "Login in success, happy chatting~");
        response.put("token", genToken(username));
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam Map<String,Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String age = (String) params.get("age");
        String gender = (String) params.get("gender");
        String address = (String) params.get("address");
        // try register
        try{
            if (username.equals("") || password.equals("")) throw new Exception("Invalid username or password");


        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("msg", e.getMessage());
            return response;
        }
        // return
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("msg", "Register success, login in now~");
        return response;
    }
}
