/**
 * ReadThread
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ReadThread implements Runnable {
    private Socket client;

    public ReadThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(client.getInputStream());
            while (true) {
                if (in.hasNextLine()) {
                    System.out.print(ParseReceive.handle(in.nextLine()));
                }
                if (client.isClosed()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
