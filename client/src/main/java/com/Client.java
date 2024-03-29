/**
 * Client
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Send send;
    public static Socket client;
    public static Login login;
    public static Register register;
    public static Chatroom chatroom;

    public static void main(String[] args) {
        // create a client
        try {
            FlatLightLaf.setup();
            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            Client client = new Client();

            // first: try to connect server
            try {
                Client.client = new Socket("127.0.0.1", 9001);
                System.out.println("Connecting Server Success. Typing /register <username> <password> <age> <gender>(0:male 1:female) <address> to register in.");
                // /register 123 321 20 0 Street.1*
            } catch (IOException e) {
                new Dialog("Connecting server fail", "Cannot connect to server, please check network.").setVisible(true);
                System.out.println("Connecting Server Failed.");
                throw e;
            }

            Thread readThread = new Thread(new ReadThread()); // receive msg
            readThread.start();

            WriteThread writeThreadRaw = new WriteThread(); // send msg thread
            Thread writeThread = new Thread(writeThreadRaw);
            writeThread.start();

            send = new Send(writeThreadRaw);

            System.out.println("Create client success.");
            // create login windows
            login = new Login();
            login.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
