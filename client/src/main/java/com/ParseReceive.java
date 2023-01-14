/**
 * ParseRecieve
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

public class ParseReceive {
    public static String handle(String input) {
        // Type<|>Msg<|>From<|>To
        // Type: SysMsg, Msg, Login, Reg, Bye, List
        // From: null when sent to server
        // To: null when sent to user, 0 represent sent to all
        String[] inputs = input.split("<\\|>");
        if (inputs.length!=4) return ""; // invalid msg
        for (int i = 0; i < inputs.length; i++) {
            if(inputs[i].equals("null")) inputs[i]="";
            inputs[i]=inputs[i].replaceAll("\\\\\\|","|");
        }

        switch (inputs[0]){
            case "SysMsg":
                return "[System Message]: " + inputs[1] + "\n";
            case "Msg":
                return "["+inputs[2]+"]: " + inputs[1] + "\n";
            default:
                return "";
        }
    }
}
