package com.example.tacademy.callfinder.model;

/**
 * Created by Tacademy on 2017-01-23.
 */

public class ReqSearchHp {
    ReqHeader header;
    ReqSearchHpBody body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public ReqSearchHpBody getBody() {
        return body;
    }

    public void setBody(ReqSearchHpBody body) {
        this.body = body;
    }
}


