package com.example.tacademy.callfinder.model;

/**
 * Created by Tacademy on 2017-01-23.
 */

public class ReqSearchHpBody {

    String tel, uid;

    public ReqSearchHpBody(String tel, String uid) {
        this.tel = tel;
        this.uid = uid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
