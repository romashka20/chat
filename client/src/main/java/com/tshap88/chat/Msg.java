package com.tshap88.chat;

import java.io.Serializable;

public class Msg implements Serializable {

    private String username;
    private String msg;

    public Msg() {
       // this.username = username;
        //this.msg = msg;
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
}
