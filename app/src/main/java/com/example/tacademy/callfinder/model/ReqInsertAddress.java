package com.example.tacademy.callfinder.model;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-01-20.
 */

public class ReqInsertAddress {
    ReqHeader header;
    ArrayList<AddressModel> body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public ArrayList<AddressModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<AddressModel> body) {
        this.body = body;
    }
}
