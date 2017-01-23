package com.example.tacademy.callfinder.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tacademy.callfinder.evt.OTTOBus;
import com.example.tacademy.callfinder.model.AddressModel;
import com.example.tacademy.callfinder.model.ReqHeader;
import com.example.tacademy.callfinder.model.ReqInsertAddress;
import com.example.tacademy.callfinder.model.ReqSearchHp;
import com.example.tacademy.callfinder.model.ReqSearchHpBody;
import com.example.tacademy.callfinder.model.ResSearchHp;
import com.example.tacademy.callfinder.service.ContactsService;
import com.example.tacademy.callfinder.util.U;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-01-20.
 */

public class Network {
    private static Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private Network() {

        ////////////////////////////////////////////////////////
        // 통신큐

        ////////////////////////////////////////////////////////
        // 통신 API
    }

    private RequestQueue requestQueue;
    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public void sendAddress(Context context, ArrayList<AddressModel> cc) {
        U.getInstance().log("서버 전송");
        // 전송 : {header:{code:AD}, body:[{uid:xx, name:xx, tel:xx}]}
        // 응답 : {code:1, msg:"ok"}
        // 1. 파라미터 구성
        ReqInsertAddress json = new ReqInsertAddress();
        ReqHeader header = new ReqHeader();

        header.setCode("SU");
        json.setHeader(header);
        json.setBody(cc);
        // 2. 요청 구성
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            "http://52.78.30.74:3000/insertAddr",
                            new JSONObject(new Gson().toJson(json)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답처리
                                    Log.i("RES:insertAddr", response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }
    // 전화번호 검색
    public void searchHp(Context context, String tel){
        // 전송 : {header:{code:SH}, body:{tel:xx, uid:xx}}
        // 응답 : {code:1, msg:{name:xxx, ... nickname:xxxx}}
        // 1. 파라미터 구성
        ReqSearchHp reqSearchHp = new ReqSearchHp();
        ReqHeader reqHeader = new ReqHeader();
        ReqSearchHpBody reqSearchHpBody= new ReqSearchHpBody(tel, ContactsService.uid);
        reqSearchHp.setHeader(reqHeader);
        reqSearchHp.setBody(reqSearchHpBody);

        // 2. 요청 생성 : /selectHp
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            "http://52.78.30.74:3000/selectTel",
                            new JSONObject(new Gson().toJson(reqSearchHp)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답
                                    Log.i("RES", response.toString());
                                    ResSearchHp resSearchHp = new Gson().fromJson(response.toString(), ResSearchHp.class);
                                    // 이벤트 발생 등록
                                    OTTOBus.getInstance().getBus().post(resSearchHp);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 응답큐에 요청 추가
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }
}
