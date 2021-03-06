package com.tshap88.chat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class InputThread implements Runnable {

    private Socket socket = null;
    private ObjectInputStream ois = null;
    private Message m;

    public InputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            while (true ) {
                ois = new ObjectInputStream(socket.getInputStream());
                this.m = (Message) ois.readObject();

                if (!m.getMsg().equals("exit")) {
                    System.out.println(m.getUsername() + ": " + m.getMsg());
                }
            }

        } catch (SocketException e) {
            System.out.println("Bye");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
