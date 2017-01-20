package com.example.tacademy.callfinder.util;

import android.util.Log;

/**
 * Created by Tacademy on 2017-01-20.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }
    ////////////////////////////////////////////
    // 로그
    public void log(String msg){
        Log.i("U-","--------------------");
        Log.i("U-",""+msg); //null을 출력하면 죽으니까 ""를 앞에 추가해줌
        Log.i("U-","--------------------");
    }
}
