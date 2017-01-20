package com.example.tacademy.callfinder.model;

/**
 * Created by Tacademy on 2017-01-20.
 */

public class AddressModel {
    String uid, name, tel;

    public AddressModel(String uid, String name, String tel) {
        this.uid = uid;
        this.name = name;
        this.tel = tel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
