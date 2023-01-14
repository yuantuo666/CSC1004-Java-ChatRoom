/**
 * FormatSend
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Send {
    public static void format(Socket client, String type, String msg) throws IOException {
        format(client, type, msg, "null", "null");
    }
    public static void format(Socket client, String type, String msg, String from, String to) throws IOException {
        PrintStream out = new PrintStream(client.getOutputStream(),
                true, "UTF-8");
        msg = msg.replaceAll("\\|","\\\\|");
        from = from.replaceAll("\\|","\\\\|");
        to = to.replaceAll("\\|","\\\\|");
        if (from.isEmpty()) from = "null";
        if (to.isEmpty()) to = "null";
        out.println(type+"<|>"+msg+"<|>"+from+"<|>"+to);
    }
}
