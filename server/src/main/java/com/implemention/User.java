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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void register(String user, String password, String age, String gender, String address, Socket client) throws IOException {
        Db Db = new Db();

        try {
            // 执行查询
            System.out.println("[Register] " + user);
            //Statement stmt = Db.conn.createStatement();
            String sql = "insert into user (name, password, register_at, age, gender, address) values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = Db.conn.prepareStatement(sql);

            preparedStmt.setString (1, user);
            preparedStmt.setString (2, password);
            preparedStmt.setInt (3, Math.round(System.currentTimeMillis()/1000));
            preparedStmt.setInt (4, Integer.parseInt(age));
            preparedStmt.setInt (5, Integer.parseInt(gender));
            preparedStmt.setString (6, address);

            preparedStmt.executeUpdate();

            clientMap.put(user, client);
            Send.format(client,"SysMsg","Register success! Happy Chatting~");

            Db.conn.close();
        } catch (SQLException e) {
            Send.format(client,"SysMsg","Register fail! Maybe the username had been used.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("[Login] " + user);
        System.out.println("Online User: " + clientMap.size());

    }



    public static void login(String user, String password, Socket client) throws IOException {
        Db Db = new Db();

        try {
            // 执行查询
            System.out.println("[Login] " + user);
            //Statement stmt = Db.conn.createStatement();
            String sql = "select `name` FROM user WHERE `name` = ? AND `password` = ?";

            PreparedStatement preparedStmt = Db.conn.prepareStatement(sql);
            preparedStmt.setString (1, user);
            preparedStmt.setString (2, password);

            ResultSet resultSet = preparedStmt.executeQuery();


            if (resultSet.next()){
                Send.format(client,"SysMsg","Login success! Happy Chatting!");
                clientMap.put(user, client);
            } else {
                Send.format(client,"SysMsg","Login fail! Please check username and password");
            }


            Db.conn.close();
        } catch (SQLException e) {
            Send.format(client,"SysMsg","Login fail...");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("[Login] " + user);
        System.out.println("Online User: " + clientMap.size());

    }
}
