/**
 * Client
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket client;
        // first: try to connect server
        try {
            if (args.length == 1) {
                client = new Socket(args[0], 9001);
            } else {
                client = new Socket("127.0.0.1", 9001);
            }
            System.out.println("Connecting Server Success. Typing /register <username> <password> <age> <gender>(0:male 1:female) <address> to register in.");
            // /register 123 321 20 0 Street.1*
        } catch (IOException e) {
            System.out.println("Connecting Server Failed.");
            e.printStackTrace();
            return;
        }
        Thread readThread = new Thread(new ReadThread(client)); // receive msg
        readThread.start();

        WriteThread writeThreadRaw = new WriteThread(client);
        Thread writeThread = new Thread(writeThreadRaw);
        writeThread.start();

        Thread parseInput = new Thread(new Send(writeThreadRaw));
        parseInput.start();

    }
}
