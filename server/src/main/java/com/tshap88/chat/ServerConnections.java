package com.tshap88.chat;


import java.net.Socket;
import java.util.*;

public class ServerConnections {

    ArrayList<Socket> listSocket = null;
    Map<String, Socket> listMap = null;

    public ServerConnections() {
        listSocket = new ArrayList<Socket>();
        listMap = new LinkedHashMap<String, Socket>();
    }

    public synchronized void putServerConnection(String name, Socket socket) {
        listMap.put(name, socket);
    }

    public synchronized Map<String, Socket> getSetMap() {
        return listMap;
    }

    public synchronized ArrayList<Socket> getListSocket() {
        return listSocket;
    }

    public synchronized Integer getSizeListSocket() {
        int size = listSocket.size();
        return size;
    }

    public synchronized void addServerConnection(Socket socket) {
        listSocket.add(socket);
    }

    public synchronized Socket getLast() {
        return listSocket.get(listSocket.size() - 1);
    }

    public synchronized void removeServerConnection(Socket socket) {
        listSocket.remove(socket);
    }
}
