/**
 * WriteThread
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class WriteThread implements Runnable {
    private Socket client;
    private String content = "";

    public WriteThread() {
        Client client = new Client();
        this.client = client.client;
    }

    public synchronized void handle(String content){
        this.content = content;
    }

    @Override
    public void run() {
        try {
            PrintStream out = new PrintStream(client.getOutputStream(),
                    true, "UTF-8");
            String str = "";
            while (true) {
                synchronized(this){
                    if (this.content.isEmpty()){
                        Thread.sleep(1);
                        continue;
                    }
                    str = this.content;
                    this.content = "";
                }

                out.println(str);
                if (str.contains("bye")) {
                    out.close();
                    client.close();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
