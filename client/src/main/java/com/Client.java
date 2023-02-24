/**
 * Client
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import com.formdev.flatlaf.FlatLightLaf;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Send send;
    public static Socket client;
    public static Login login;
    public static Register register;

    public static void main(String[] args) {
        // create a client
        try {
            Client client = new Client();

            // first: try to connect server
            try {
                client.client = new Socket("127.0.0.1", 9001);
                System.out.println("Connecting Server Success. Typing /register <username> <password> <age> <gender>(0:male 1:female) <address> to register in.");
                // /register 123 321 20 0 Street.1*
            } catch (IOException e) {
                System.out.println("Connecting Server Failed.");
                throw e;
            }

            Thread readThread = new Thread(new ReadThread()); // receive msg
            readThread.start();

            WriteThread writeThreadRaw = new WriteThread(); // send msg thread
            Thread writeThread = new Thread(writeThreadRaw);
            writeThread.start();

            client.send = new Send(writeThreadRaw);

            System.out.println("Create client success.");
            // create login windows
            FlatLightLaf.setup();
            client.login = new Login();
            client.login.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
