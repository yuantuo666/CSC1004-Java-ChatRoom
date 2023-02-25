/**
 * Server
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import com.implemention.Db;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    // client map (user, socket)
    public static Map<String, Socket> clientMap = new ConcurrentHashMap<String, Socket>();
    public static SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Db Db;

    public static void main(String[] args) throws Exception {
        // connect to database
        try{
            Db = new Db();
            System.out.println("[Database] Connect success");
        }catch (Exception e){
            System.out.println("[Database] Connect Fail");
            e.printStackTrace();
            return;
        }


        //creat thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        //creat server socket
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            System.out.println("[Waiting] for client connect...");
            Socket client = serverSocket.accept();
            System.out.println("[New client] port:" + client.getPort());

            executorService.submit(new ExecuteClient(client));
        }
//        executorService.shutdown();
//        serverSocket.close();
    }
}
