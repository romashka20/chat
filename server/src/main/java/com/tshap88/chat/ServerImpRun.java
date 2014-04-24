package com.tshap88.chat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ServerImpRun implements Runnable {

    private ServerConnections serverConnections = new ServerConnections();
    private Socket socket;
    private ObjectInputStream ois = null;
    private Message m;
    private InetAddress client = null;

    public ServerImpRun(ServerConnections connection) {
        this.serverConnections = connection;
        this.socket = connection.getLast();
    }

    @Override
    public void run() {

        try {

            ois = new ObjectInputStream(socket.getInputStream());

            while (!false) {

                this.m = (Message) ois.readObject();
                System.out.println("User connect: " + m.getUsername() + " " + socket.getInetAddress().getHostName());
                System.out.println(m.getUsername() + ": " + m.getClientIp());
                serverConnections.putServerConnection(m.getUsername(), socket);

                if (m.getMsg().equals("exit")) {
                    serverConnections.removeServerConnection(socket);
                    System.out.println("User is logged out of the chat");

                } else {
                    System.out.println(m.getUsername() + ": " + m.getMsg());

                    for (Socket socket1 : serverConnections.getListSocket()) {
                        if (!socket1.equals(socket)) {
                            ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                            oos.writeObject(m);
                            oos.flush();
                        }
                    }
                }
            }

        } catch (NegativeArraySizeException e) {
            System.out.println("Connection with user has been interrupted");
            serverConnections.removeServerConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
