package com.tshap88.chat;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {

    private String username;
    private String msg;
    private InetAddress clientIp;

    public Message() {

    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setClientIp(InetAddress clientIp){
        this.clientIp = clientIp;
    }

    public InetAddress getClientIp() {
        return clientIp;
    }
}
