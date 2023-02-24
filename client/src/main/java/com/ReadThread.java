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

    public ReadThread() {
        Client client = new Client();
        this.client = client.client;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(client.getInputStream());
            while (true) {
                if (in.hasNextLine()) {
                    String[] Msg = ParseReceive.handle(in.nextLine());
                    if (Msg[0] == null) continue;
                    if (Msg[0]=="System Message"){
                        // pop dialog
                        new Dialog("System Message", Msg[1]).setVisible(true);
                    }else {
                        // add to chat windows
                        new Dialog("TEST msg", Msg[1]).setVisible(true);
                    }
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
