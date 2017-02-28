package com.example.mxhung.fcmchat;

import java.util.Date;

/**
 * Created by MXHung on 2/15/2017.
 */

public class TinNhan {
    public String name;
    public String msg;
    public  long msgTime;

    public TinNhan() {
        //mac dinh khi nhan data tu FireBase
    }

    public TinNhan(String name, String msg) {
        this.name = name;
        this.msg = msg;
        this.msgTime = new Date().getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }
}
