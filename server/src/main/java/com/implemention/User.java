/**
 * User
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com.implemention;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.Server.clientMap;
import static com.Server.Db;

@Slf4j
public class User {
    public static String getUser(Socket client) {
        String user = "";
        for(String key : clientMap.keySet()) {
            if (clientMap.get(key).equals(client)) {
                user = key;
            }
        }
        return user;
    }

    public static String register(String user, String password, String age, String gender, String address) throws IOException {
        try {
            String sql = "insert into user (name, password, register_at, age, gender, address) values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = Db.conn.prepareStatement(sql);

            preparedStmt.setString (1, user);
            preparedStmt.setString (2, password);
            preparedStmt.setInt (3, Math.round(System.currentTimeMillis()/1000));
            preparedStmt.setInt (4, Integer.parseInt(age));
            preparedStmt.setInt (5, Integer.parseInt(gender));
            preparedStmt.setString (6, address);

            preparedStmt.executeUpdate();

            log.info("[Register] " + user);
            return "Register success! Login in now~";
        } catch (SQLException e) {
            return "Register fail! Maybe the username had been used.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Register fail! Please check your input.";
        }
    }

    public static String login(String user, String password) throws IOException {

        try {
            String sql = "select `name` FROM user WHERE `name` = ? AND `password` = ?";

            PreparedStatement preparedStmt = Db.conn.prepareStatement(sql);
            preparedStmt.setString (1, user);
            preparedStmt.setString (2, password);

            ResultSet resultSet = preparedStmt.executeQuery();

            if (resultSet.next()){
                log.info("[Login] " + user);
                return "Login success! Happy Chatting!";
            } else {
                return "Login fail! Please check username and password";
            }
        } catch (SQLException e) {
            log.error("[Login] " + user + " " + e.getMessage());
            return "Please try again.";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
