/**
 * Db
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/02/07
 */
package com.implemention;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    Connection conn;
    public Db() throws SQLException, ClassNotFoundException {
        String driverName = "com.mysql.cj.jdbc.Driver";

        String dbURL="jdbc:mysql://localhost:3306/chatroom?&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true";
        String userName = "chatroom";
        String userPwd = "chatroom";

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
        }  catch (Exception e) {
            throw e;
        }
    }
}
