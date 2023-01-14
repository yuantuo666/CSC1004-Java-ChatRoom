/**
 * ParseReceive
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import com.implemention.Chat;
import com.implemention.User;

import java.io.IOException;
import java.net.Socket;

import static com.Server.clientMap;

public class ParseReceive {
    public static void handle(String input, Socket client) throws IOException {
        // Type<|>Msg<|>From<|>To
        // Type: SysMsg, Msg, Login, Reg, Bye, List
        // From: null when sent to server
        // To: null when sent to user, null represent sent to all
        String[] inputs = input.split("<\\|>");

        if (inputs.length!=4) return ; // invalid msg
        for (int i = 0; i < inputs.length; i++) {
            if(inputs[i].equals("null")) inputs[i]="";
            inputs[i]=inputs[i].replaceAll("\\\\\\|","|");
        }

        String user = User.getUser(client);
        if(user.isEmpty() && !inputs[0].equals("Reg")){
            Send.format(client,"SysMsg","You haven't register! Type /register <username> to register in.");
            return;
        }
        switch (inputs[0]){
            case "Reg":
                User.register(inputs[1], client);
                return;
            case "List":
                String msg ="Current USER\n";
                for(String key : clientMap.keySet()){
                    msg+=" · " + key+ "\n";
                }
                msg+="All num: " + clientMap.size();
                Send.format(client,"SysMsg",msg);
                return;
            case "Msg":
                String text = inputs[1];
                String to = inputs[3];
                if(to.equals("")) Chat.groupChat(text, user);
                else Chat.privateChat(to, text, user);
                return;
            case "Bye":
                System.out.println("[Bye] " + user);
                clientMap.remove(user);
                return;
            default:
                Send.format(client,"SysMsg","Invalid operation");
        }
    }
}
