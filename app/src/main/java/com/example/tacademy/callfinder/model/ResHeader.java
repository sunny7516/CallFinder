package com.example.tacademy.callfinder.model;

/**
 * Created by Tacademy on 2017-01-20.
 */

public class ResHeader {
    int code;
    String msg;

    public ResHeader() {
    }

    public ResHeader(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}