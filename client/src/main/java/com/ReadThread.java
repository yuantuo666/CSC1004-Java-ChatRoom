/**
 * ReadThread
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            Client client = new Client();
            String str;
            while ((str = in.nextLine())!=null) {
                if (client.client.isClosed()) {
                    break;
                }

                String[] Msg = ParseReceive.handle(str);
                if (Msg[0] == null) continue;
                if (Msg[0]=="System Message"){
                    if (Msg[1].contains("Login success")){
                        // destroy login windows
                        client.login.dispose();
                        // loading chat windows
                        Chatroom chatroom = new Chatroom();
                        chatroom.setVisible(true);
                        client.chatroom = chatroom;
                    }
                    if (Msg[1].contains("Register success")){
                        // destroy login windows
                        client.register.dispose();
                    }
                    // pop dialog
                    new Dialog("System Message", Msg[1]).setVisible(true);
                }
                // add to chat windows
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                client.chatroom.receive(Msg[0]+ " " +sdf.format(date) +"\n"+Msg[1]+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
