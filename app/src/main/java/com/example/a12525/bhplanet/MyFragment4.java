package com.example.a12525.bhplanet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageButton;
/**
 * Created by Jay on 2015/8/28 0028.
 */

public class MyFragment4 extends Fragment implements View.OnClickListener{
    private ImageButton shezhi;
    private Button dongtai;
    private Button guanzhu;
    private Button fensi;
    private Button shoucang;
    private Button lishi;
    private Button xiazai;

    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Activity_main = inflater.inflate(R.layout.activity_main, container, false);
        shezhi = (ImageButton) Activity_main.findViewById(R.id.shezhi);
        dongtai = (Button)Activity_main.findViewById(R.id.dongtai);
        guanzhu = (Button)Activity_main.findViewById(R.id.guanzhu);
        fensi = (Button)Activity_main.findViewById(R.id.fensi);
        shoucang = (Button)Activity_main.findViewById(R.id.shoucang);
        lishi = (Button)Activity_main.findViewById(R.id.lishi);
        xiazai = (Button)Activity_main.findViewById(R.id.xiazai);

        shezhi.setOnClickListener(this);
        dongtai.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        fensi.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        lishi.setOnClickListener(this);
        xiazai.setOnClickListener(this);
        return Activity_main;
    }
    public  void onClick(View v) {
        switch (v.getId()){
            case R.id.shezhi:
                Intent intent=new Intent("com.example.a12525.bhplanet.ACTION_SHE");
                startActivity(intent);
                break;
           case R.id.dongtai:
                Intent intent1=new Intent("com.example.a12525.bhplanet.ACTION_ZDONG");
                startActivity(intent1);
                break;
           case R.id.guanzhu:
                Intent intent2=new Intent("com.example.a12525.bhplanet.ACTION_GUAN");
                startActivity(intent2);
                break;
            case R.id.fensi:
                Intent intent3=new Intent("com.example.a12525.bhplanet.ACTION_FEN");
                startActivity(intent3);
                break;
            case R.id.shoucang:
                Intent intent4=new Intent("com.example.a12525.bhplanet.ACTION_SHOU");
                startActivity(intent4);
                break;
            case R.id.lishi:
                Intent intent5=new Intent("com.example.a12525.bhplanet.ACTION_LI");
                startActivity(intent5);
                break;
            case R.id.xiazai:
                Intent intent6=new Intent("com.example.a12525.bhplanet.ACTION_XIA");
                startActivity(intent6);
                break;

        }

    }


  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }*/
}

