package com.tshap88.chat;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client {
    public static void main(String[] args) {
        Socket socket;

        try {
            System.out.println("Сlient starting.");
            socket = new Socket(ConfFile.getADDRESS(), ConfFile.getPORT());

            final ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.submit(new InputThread(socket));
            executorService.submit(new OutputThread(socket));
            executorService.shutdown();

        } catch (NullPointerException e) {
            System.out.println("Connection with server lost.");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
