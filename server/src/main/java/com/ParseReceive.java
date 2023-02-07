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
        if(user.isEmpty() && !inputs[0].equals("Reg") && !inputs[0].equals("Login")){
            Send.format(client,"SysMsg","You haven't register or login! Type /register <username> <password> <age> <gender>(0:male 1:female) <address> to register in. Type /login <username> <password> to login.");
            return;
        }
        switch (inputs[0]){
            case "Reg":
                String[] regInputs = inputs[1].split("\\|");
                User.register(regInputs[0], regInputs[1], regInputs[2], regInputs[3], regInputs[4], client);
                return;
            case "Login":
                String[] loginInputs = inputs[1].split("\\|");
                User.login(loginInputs[0], loginInputs[1], client);
                return;
            case "List":
                StringBuilder msg = new StringBuilder("Current USER\n");
                for(String key : clientMap.keySet()){
                    msg.append(" · ").append(key).append("\n");
                }
                msg.append("All num: ").append(clientMap.size());
                Send.format(client,"SysMsg", msg.toString());
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
