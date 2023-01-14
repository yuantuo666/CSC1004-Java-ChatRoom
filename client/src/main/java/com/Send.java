/**
 * ParseInput
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;


import java.io.IOException;
import java.util.Scanner;

class Send implements Runnable {
    private WriteThread writeThread;

    public Send(WriteThread writeThread){this.writeThread = writeThread;}

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (true) {
            if (!scanner.hasNextLine()) {continue;}
            str = scanner.nextLine();

            String[] args = str.split(" ");

            // Group Msg
            if(!str.startsWith("/") || args.length == 0){
                try {
                    format(writeThread,"Msg",str);
                    continue;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // command: /register username
            //          /msg username msg
            //          /list
            // TODO     /login username

            String command = args[0].substring(1);
            try {
                switch (command){
                    case "register":
                        if (args.length != 2) {
                            System.out.println("Command error! Please type: /register <username>");
                            break;
                        }
                        format(writeThread,"Reg",args[1]);
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
//            if (str.contains("bye")) {
//                scanner.close();
//            }
        }

    }
    public static void format(WriteThread server, String type, String msg) throws IOException {
        format(server,type, msg, "null", "null");
    }
    public static void format(WriteThread server, String type, String msg, String from, String to) throws IOException {
        msg = msg.replaceAll("\\|","\\\\|");
        from = from.replaceAll("\\|","\\\\|");
        to = to.replaceAll("\\|","\\\\|");
        if (from.isEmpty()) from = "null";
        if (to.isEmpty()) to = "null";
        server.handle(type+"<|>"+msg+"<|>"+from+"<|>"+to);
    }
}
