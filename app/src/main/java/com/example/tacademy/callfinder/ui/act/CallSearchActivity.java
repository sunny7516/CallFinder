package com.example.tacademy.callfinder.ui.act;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tacademy.callfinder.R;
import com.example.tacademy.callfinder.net.Network;
import com.example.tacademy.callfinder.ui.frag.CallSearchFragment;
import com.example.tacademy.callfinder.ui.frag.FaceBookFragment;
import com.example.tacademy.callfinder.ui.frag.WebSearchFragment;

public class CallSearchActivity extends AppCompatActivity
        implements CallSearchFragment.OnFragmentInteractionListener,
        FaceBookFragment.OnFragmentInteractionListener,
        WebSearchFragment.OnFragmentInteractionListener {

    TabLayout tabLayout;
    ViewPager viewpager;
    FragmentAdapter fragmentAdapter;
    EditText editText;
    ImageButton imageButton;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_search);
        editText = (EditText)findViewById(R.id.editText);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewpager);    // 연결 마무리

        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSearchHp(v);
            }
        });
    }

    // 전번 검색 > 결과 파싱 > 이벤트 전달 > 화면 구성(그리드+리스트 퓨전)
    // 핸드폰 위로 띄우기
    public void onSearchHp(View view){
        String tel = editText.getText().toString();
        Network.getInstance().searchHp(this, tel);
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        Fragment[] frags = new Fragment[]{
                new CallSearchFragment(),
                new FaceBookFragment(),
                new WebSearchFragment()
        };
        String[] titles = new String[]{
                "전화번호검색", "페이스북검색", "웹검색"
        };

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }
}
