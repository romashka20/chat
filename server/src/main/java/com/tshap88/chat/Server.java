package com.tshap88.chat;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        ServerSocket ssocket;

        try {
            ServerConnections sc = new ServerConnections();
            ssocket = new ServerSocket(ConfFile.getPORT());
            System.out.println("Server is up: " + ssocket.getInetAddress().getHostName());

            while (true) {
                Socket socket = ssocket.accept();
                sc.addServerConnection(socket);

                final ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.submit(new ServerImpRun(sc));
                //executorService.shutdown();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
