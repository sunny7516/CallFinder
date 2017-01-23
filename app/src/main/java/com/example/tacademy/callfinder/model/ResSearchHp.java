package com.example.tacademy.callfinder.model;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-01-23.
 */

public class ResSearchHp {
    int code;
    ArrayList<ResSearchHpBody> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ResSearchHpBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<ResSearchHpBody> body) {
        this.body = body;
    }
}
