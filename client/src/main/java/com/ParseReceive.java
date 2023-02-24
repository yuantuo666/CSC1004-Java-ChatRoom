/**
 * ParseRecieve
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import sun.rmi.runtime.Log;

public class ParseReceive {
    public static String[] handle(String input) {
        // Type<|>Msg<|>From<|>To
        // Type: SysMsg, Msg, Login, Reg, Bye, List
        // From: null when sent to server
        // To: null when sent to user, 0 represent sent to all
        String[] inputs = input.split("<\\|>");
        if (inputs.length!=4) return new String[]{null,null}; // invalid msg
        for (int i = 0; i < inputs.length; i++) {
            if(inputs[i].equals("null")) inputs[i]="";
            inputs[i]=inputs[i].replaceAll("\\\\\\|","|");
        }

        switch (inputs[0]){
            case "SysMsg":
                Client client = new Client();
                if (inputs[1].contains("Login success")){
                    // destroy login windows
                    client.login.dispose();
                    // loading chat windows
                    
                }
                if (inputs[1].contains("Register success")){
                    // destroy login windows
                    client.register.dispose();
                }
                return new String[]{"System Message", inputs[1]};
            case "Msg":
                return new String[]{inputs[2], inputs[1]};
            default:
                return new String[]{null,null};
        }
    }
}
