package com.tshap88.chat;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class OutputThread implements Runnable {

    private Socket socket = null;
    //private PrintWriter out = null;
    private ObjectOutputStream oos = null;
    private String msgOut = null;
    private String username = "";
    private Msg m;

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
            m = new Msg();
            m.setUsername(bk.readLine());

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
