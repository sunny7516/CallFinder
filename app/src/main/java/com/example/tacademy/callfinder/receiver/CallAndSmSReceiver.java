package com.example.tacademy.callfinder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import com.example.tacademy.callfinder.util.U;

public class CallAndSmSReceiver extends BroadcastReceiver {
    public CallAndSmSReceiver() {
    }

    // 방송 수신 : 전화, 문자
    @Override
    public void onReceive(Context context, Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 액티비티가 없기 때문에 화면에 띄우는 방법이 다른 것.
        switch (intent.getAction()) {
            case "android.intent.action.PHONE_STATE":   //전화
            {
                Bundle bundle = intent.getExtras();
                String state = bundle.getString(TelephonyManager.EXTRA_STATE);
                // 전화벨이 울리고 있다!
                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                    // 전번 획득
                    String call_tel =
                    bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    U.getInstance().log("전화 수신된 전번 : "+call_tel);
                    // 전번 검색
                    // UI 구성
                }else{
                }
            }
            break;
            case "android.provider.Telephony.SMS_RECEIVED": //문자
            {

            }
            break;
        }
    }
}
