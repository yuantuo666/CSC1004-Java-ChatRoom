/**
 * Chat
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com.implemention;

import com.Send;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

import static com.Server.clientMap;

public class Chat {
    public static void groupChat(String text, String user) {
        Set<Map.Entry<String, Socket>> clientSet = clientMap.entrySet();
        for (Map.Entry<String, Socket> entry : clientSet) {
            try {
                Send.format(entry.getValue(),"Msg",text,user,"0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void privateChat(String sendUser, String text, String user) {
        try {
            Send.format(clientMap.get(sendUser),"Msg",text,sendUser,user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
