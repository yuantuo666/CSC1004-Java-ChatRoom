/**
 * ParseInput
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;


import java.io.IOException;
import java.util.Scanner;

class Send {
    private WriteThread writeThread;

    public Send(WriteThread writeThread){this.writeThread = writeThread;}

    public void send(String command) {

            String[] args = command.split(" ");

            // Group Msg
            if(!command.startsWith("/") || args.length == 0){
                try {
                    format(writeThread,"Msg",command);
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // command: /register username
            //          /msg username msg
            //          /list
            // TODO     /login username

            String commands = args[0].substring(1);
            try {
                switch (commands){
                    case "register":
                        if (args.length != 6) {
                            System.out.println("Command error! Please type: /register <username> <password> <age> <gender>(0:male 1:female) <address>");
                            break;
                        }
                        format(writeThread,"Reg",args[1]+"|"+args[2]+"|"+args[3]+"|"+args[4]+"|"+args[5]);
                        break;
                    case "login":
                        if (args.length != 3) {
                            System.out.println("Command error! Please type: /login <username> <password>");
                            break;
                        }
                        format(writeThread,"Login",args[1]+"|"+args[2]);
                        break;
                    case "msg":
                        if (args.length != 3) {
                            System.out.println("Command error! Please type: /msg <username> <msg>");
                            break;
                        }
                        format(writeThread,"Msg",args[2],"",args[1]);
                        break;
                    case "list":
                        format(writeThread,"List","");
                        break;
                    case "heartbeat":
                        format(writeThread,"Heartbeat","");
                        break;
                    case "bye":
                        format(writeThread,"Bye","");
                        break;
                    case "help":
                        System.out.println("Supported command are listed below\n > Public Message: <msg>\n > Register: /register <username>\n > Secret Message: /msg <username> <msg>");
                        break;
                    default:
                        System.out.println("Command error! Please check the command by typing: /help");
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    public static void format(WriteThread server, String type, String msg) throws IOException {
        format(server,type, msg, "null", "null");
    }
    public static void format(WriteThread server, String type, String msg, String from, String to) throws IOException {
        msg = msg.replaceAll("\\|","\\\\|");
        msg = msg.replaceAll("\r","");
        msg = msg.replaceAll("\n","<br />");

        from = from.replaceAll("\\|","\\\\|");
        to = to.replaceAll("\\|","\\\\|");
        if (from.isEmpty()) from = "null";
        if (to.isEmpty()) to = "null";
        server.handle(type+"<|>"+msg+"<|>"+from+"<|>"+to);
    }
}
