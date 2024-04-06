package io.codeforall.javatars;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static ArrayList<ClientSide> clientList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int portNumber = 9000;

        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket;

        ExecutorService cachePool = Executors.newCachedThreadPool();
        int i = 0;
       while (true) {
               clientSocket = serverSocket.accept();
               clientList.add(new ClientSide(clientSocket));
               cachePool.submit(clientList.get(i));
               i++;
       }
    }
}
