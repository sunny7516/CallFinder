package com.example.tacademy.callfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.tacademy.callfinder.db.StorageHelper;
import com.example.tacademy.callfinder.net.Network;
import com.example.tacademy.callfinder.service.ContactsService;
import com.example.tacademy.callfinder.ui.act.CallSearchActivity;

public class MainActivity extends AppCompatActivity {
    final int PERMISSION_READ_CONTACTS = 0;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);

        // 단말기 버전이 6.0 이상부터 권한을 체크해야 한다.
        if (isSupport()) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_READ_CONTACTS);
            } else {
                // 6.0이상에서는 퍼미션을 동의 했으므로 바로 실행
                getAddress();
            }
        } else {
            // 6.0 이하 단말기는 동의가 필요 없으므로 바로 실행
            getAddress();
        }
        Intent intent = new Intent(this, CallSearchActivity.class);
        startActivity(intent);
    }

    public boolean isSupport() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 6.0 이상 적용
            return true;
        } else {
            // 6.0 부터 적용
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted!
                    // you may now do the action that requires this permission
                    getAddress();
                } else {
                    // 죽어도 않한다.!!
                    // 다시띠우던지, 서비스 종료 결정!!
                }
                return;
            }

        }
    }

    // 나의 주소록 획득
    public void getAddress() {
        // 이미 정보가 있는 것은 거절 return!
        if (StorageHelper.getInstance().getBoolean(this, "CONTACT_SEND_OK"))
            return;

        // 서비스로 구동(백그라운드 구동) -> 앱 사용간에 영향을 주면 안된다.
        Intent intent = new Intent(this, ContactsService.class);
        startService(intent);
    }

    // 전번 검색
    public void onSearchHp(View view){
        String tel = editText.getText().toString();
        Network.getInstance().searchHp(this, tel);
    }
}













