package com.example.tacademy.callfinder.ui.frag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.callfinder.R;
import com.example.tacademy.callfinder.evt.OTTOBus;
import com.example.tacademy.callfinder.model.ResSearchHp;
import com.example.tacademy.callfinder.model.ResSearchHpBody;
import com.example.tacademy.callfinder.util.U;
import com.squareup.otto.Subscribe;

public class CallSearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    GridView grideView;

    MyAdapter myAdapter;
    MyAdapter myAdapter1;

    // 전번 검색 후 나온 결과를 받아온 객체이다. (otto bus를 통하여 세팅됨)
    ResSearchHp resSearchHp;
    LayoutInflater inflater;

    public CallSearchFragment() {
        // 이벤트 받을 녀석 설정 (액티비티, 플래그먼트)
        OTTOBus.getInstance().getBus().register(this);
    }

    public static CallSearchFragment newInstance(String param1, String param2) {
        CallSearchFragment fragment = new CallSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_call_search, container, false);
        grideView = (GridView)view.findViewById(R.id.gridView);
        myAdapter = new MyAdapter();
        myAdapter1 = new MyAdapter();
        grideView.setAdapter(myAdapter);
        // 버튼이 토글화 되어서 뷰의 생김새를 변화시킨다!!
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 한번 누르면 리스트류, 또 누르면 그리드뷰
                if(grideView.getNumColumns() == 1){
                    grideView.setNumColumns(3);
                    grideView.setAdapter(myAdapter);
                }else{
                    grideView.setNumColumns(1);
                    grideView.setAdapter(myAdapter1);
                }
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            if(resSearchHp == null) return 0;
            return resSearchHp.getBody().size();
        }

        @Override
        public ResSearchHpBody getItem(int position) {
            return resSearchHp.getBody().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                // 리스트뷰와 그리드뷰 분기 => 각각 아답터 객체를 만들어서 사용함.
                if(grideView.getNumColumns()==1){
                    // 리스트뷰
                    convertView = inflater.inflate(R.layout.cell_list_layout, parent, false);
                }else{
                    // 그리드뷰
                    convertView = inflater.inflate(R.layout.cell_grid_layout, parent, false);
                }
            }
            // 본래는 viewholder를 만들어서 담을그릇 생성 후 view를 각각 담아주면 됨.
            // 화면 설정한 뷰
            ImageView profile = (ImageView)convertView.findViewById(R.id.profile);
            TextView nickname = (TextView) convertView.findViewById(R.id.nickname);

            // 세팅할 셀 데이터 획득
            ResSearchHpBody item = getItem(position);
            // 사진 세팅
            // 이름 세팅
            nickname.setText(item.getNickname());

            return convertView;
        }
    }

    @Subscribe
    public void FinishLoad(ResSearchHp data){
        U.getInstance().log("데이터가 넘어왔다!!");
        resSearchHp = data;
        ((MyAdapter)grideView.getAdapter()).notifyDataSetChanged();
    }
}
