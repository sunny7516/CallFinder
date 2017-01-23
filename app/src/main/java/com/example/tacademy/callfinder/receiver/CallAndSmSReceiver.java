package com.example.tacademy.callfinder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import com.example.tacademy.callfinder.net.Network;
import com.example.tacademy.callfinder.util.U;

import java.util.ArrayList;

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
                    Network.getInstance().searchHp(context, call_tel);
                    // UI 구성 (핸드폰 화면 위로 구동 : 앱이 구동되지 않아도, 단독으로 위젯처럼 뜬다)
                }else{
                }
            }
            break;
            case "android.provider.Telephony.SMS_RECEIVED": //문자
            {
                // Pdu(Protocol Data Unit:SMS를 보내는 데이터 포맷으로 3GPP라는 기관에서
                // , 일종의 데이터 그램형식으로 숫자와 알파벳의 연속적으로 표시됨.
                    Object[] objects = (Object[])intent.getExtras().get("pdus");
                ArrayList<SmsMessage> smsMessages = new ArrayList<SmsMessage>();
                for(Object obj:objects){
                    SmsMessage sms = SmsMessage.createFromPdu((byte[])(byte[]) obj); // pdu : sms 수집하는 공간
                    smsMessages.add(sms);
                    U.getInstance().log("전번:"+sms.getOriginatingAddress());
                    U.getInstance().log("내용:"+sms.getMessageBody().toString());
                }
                // 인증 번호 획득이나 기타 정보를 추출할 목적이면 메시지 내용을 분석하여
                // 획득하면 된다.
            }
            break;
        }
    }
}
