/**
 * Server
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/01/13
 */
package com;

import com.implemention.Db;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Server {
    // client map (user, socket)
    public static Map<String, Socket> clientMap = new ConcurrentHashMap<String, Socket>();
    public static SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Db Db;
    public static WebSocket webSocket = null;

    public static void run(String[] args) throws Exception {
        // connect to database
        try{
            Db = new Db();
            log.info("[Database] Connect success");
        }catch (Exception e){
            log.error("[Database] Connect Fail", e);
            return;
        }

        //creat thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        //creat server socket
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            log.info("[Waiting] for client connect...");
            Socket client = serverSocket.accept();
            log.info("[New Java Client] port:" + client.getPort());

            executorService.submit(new ExecuteClient(client));
        }
//        executorService.shutdown();
//        serverSocket.close();
    }
}
