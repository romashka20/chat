package com.tshap88.chat;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class OutputThread implements Runnable {

    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private Message m;
    private InetAddress ip = null;

    public OutputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            boolean exit = true;
            oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader bk = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Your username:");
            m = new Message();
            m.setUsername(bk.readLine());

            ip = InetAddress.getLocalHost();
            m.setClientIp(ip);

            while (exit) {
                m.setMsg(bk.readLine());

                if (!m.getMsg().equals("exit")) {
                    oos.writeObject(m);
                    oos.flush();
                } else {
                    oos.writeObject(m);
                    oos.flush();
                    exit = false;
                }
            }

            oos.close();
            bk.close();
            socket.close();

        } catch (NullPointerException e) {
            System.out.println("Connection with server has been interrupted");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
