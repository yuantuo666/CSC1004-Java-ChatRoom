/**
 * ExecuteClient
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ExecuteClient implements Runnable{
    private Socket client;
    public ExecuteClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String str = "";
            Scanner in = new Scanner(client.getInputStream());

            while(true){
                if(in.hasNextLine()){
                    str = in.nextLine();
                    System.out.println(str);
                    ParseReceive.handle(str,client);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
