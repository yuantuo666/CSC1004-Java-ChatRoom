/**
 * Db
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/02/07
 */
package com.implemention;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    Connection conn;
    public Db() {
        String driverName = "com.mysql.cj.jdbc.Driver";

        String dbURL="jdbc:mysql://localhost:3306/chatroom?&useSSL=false&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String userPwd = "root";

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("[Database] Connect success");
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.print("[Database] Connect Fail");
        }
    }
}
