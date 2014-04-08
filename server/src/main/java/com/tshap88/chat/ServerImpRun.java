package com.tshap88.chat;

import java.io.*;
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
    private Msg m;

    public ServerImpRun(ServerConnections connection) {
        this.serverConnections = connection;
        this.socket = connection.getLast();
    }

    @Override
    public void run() {

        try {

            ois = new ObjectInputStream(socket.getInputStream());
            serverConnections.putServerConnection(m.getUsername(), socket);

            System.out.println("User connect: " + m.getUsername() + " " + socket.getInetAddress().getHostName());

            while (!false) {

                this.m = (Msg) ois.readObject();

                if (m.getMsg().equals("exit")) {
                    serverConnections.removeServerConnection(socket);
                    System.out.println("User is logged out of the chat");
                } /*else if (!name.trim().equals(username) && name.trim().length() > 1) {

                    Set<Map.Entry<String, Socket>> set = serverConnections.getSetMap().entrySet();
                    for (Map.Entry<String, Socket> me : set) {
                        if (me.getKey().equals(name.trim())) {
                            PrintWriter out = new PrintWriter(me.getValue().getOutputStream());
                            out.print(str.substring(0, num2+2) + str.substring(num + 1));
                            out.flush();
                        }
                    }

                } */else {
                    System.out.println(m.getUsername() + " " + m.getMsg());

                    for (Socket socket1 : serverConnections.getListSocket()) {
                        if (!socket1.equals(socket)) {
                            ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                            oos.writeObject(m);
                            oos.flush();
                        }
                    }
                }
            }

            // in.close();
            // socket.close();

        } catch (NegativeArraySizeException e) {
            System.out.println("Connection with user has been interrupted");
            serverConnections.removeServerConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
