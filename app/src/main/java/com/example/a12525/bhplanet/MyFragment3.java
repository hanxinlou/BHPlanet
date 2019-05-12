package com.example.a12525.bhplanet;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends Fragment implements View.OnClickListener {
    private ImageView ping;
    private ImageView ping1;
    private ImageView ping2;
    private ImageView zan;
    private ImageView zan1;
    private ImageView zan2;
    private ImageView tou;
    private ImageView tou1;
    private ImageView tou2;
    private ImageView zhuan;
    private ImageView zhuan1;
    private ImageView zhuan2;

    private LinearLayout dongtai1;
    private LinearLayout dongtai2;
    private LinearLayout zhuanfa;
    private boolean flag=false;
    private boolean flag3=false;
    private boolean flag4=false;
    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Activity_dongtai = inflater.inflate(R.layout.activity_dongtai, container, false);
        ping = (ImageView) Activity_dongtai.findViewById(R.id.ping);
        ping1 = (ImageView) Activity_dongtai.findViewById(R.id.ping1);
        ping2 = (ImageView) Activity_dongtai.findViewById(R.id.ping2);
        zan = (ImageView) Activity_dongtai.findViewById(R.id.zan);
        zan1 = (ImageView) Activity_dongtai.findViewById(R.id.zan1);
        zan2 = (ImageView) Activity_dongtai.findViewById(R.id.zan2);
        tou = (ImageView) Activity_dongtai.findViewById(R.id.tou);
        tou1 = (ImageView) Activity_dongtai.findViewById(R.id.tou1);
        tou2 = (ImageView)Activity_dongtai.findViewById(R.id.tou2);
        zhuan = (ImageView)Activity_dongtai.findViewById(R.id.zhuan);
        zhuan1 = (ImageView)Activity_dongtai.findViewById(R.id.zhuan1);
        zhuan2 = (ImageView)Activity_dongtai.findViewById(R.id.zhuan2);
        dongtai1=(LinearLayout) Activity_dongtai.findViewById(R.id.dongtai1);
        zhuanfa=(LinearLayout) Activity_dongtai.findViewById(R.id.zhuanfa);
        dongtai2=(LinearLayout) Activity_dongtai.findViewById(R.id.dongtai2);
        ping.setOnClickListener(this);
        ping1.setOnClickListener(this);
        ping2.setOnClickListener(this);
        zan.setOnClickListener(this);
        zan1.setOnClickListener(this);
        zan2.setOnClickListener(this);
        tou.setOnClickListener(this);
        tou1.setOnClickListener(this);
        tou2.setOnClickListener(this);
        zhuan.setOnClickListener(this);
        zhuan1.setOnClickListener(this);
        zhuan2.setOnClickListener(this);
        dongtai1.setOnClickListener(this);
        dongtai2.setOnClickListener(this);
        zhuanfa.setOnClickListener(this);
        return Activity_dongtai;

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ping:
                Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent);
                break;
            case R.id.ping1:
                Intent intent5 = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent5);
                break;
            case R.id.ping2:
                Intent intent6 = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent6);
                break;
            case R.id.tou:
                Intent intent1 = new Intent("com.example.a12525.bhplanet.ACTION_TDONG");
                startActivity(intent1);
                break;
            case R.id.tou1:
                Intent shabi = new Intent("com.example.a12525.bhplanet.ACTION_TDONG");
                startActivity(shabi);
                break;
            case R.id.tou2:
                Intent inten = new Intent("com.example.a12525.bhplanet.ACTION_TDONG");
                startActivity(inten);
                break;
            case R.id.zan:
                if(!flag){
                    zan.setImageDrawable(getResources().getDrawable(R.drawable.zan));
                    flag=true;
                }
                else
                {
                    zan.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
                    flag=false;

                }
                break;
            case R.id.zan1:
                if(!flag4){
                    zan1.setImageDrawable(getResources().getDrawable(R.drawable.zan));
                    flag4=true;
                }
                else
                {
                    zan1.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
                    flag4=false;

                }
                break;


            case R.id.zan2:
                if(!flag3){
                    zan2.setImageDrawable(getResources().getDrawable(R.drawable.zan));
                    flag3=true;
                }
                else
                {
                    zan2.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
                    flag3=false;

                }
                break;
            case R.id.zhuan:
                Intent intent2 = new Intent("com.example.a12525.bhplanet.ACTION_ZHUAN");
                startActivity(intent2);
                break;
            case R.id.zhuan1:
                Intent in = new Intent("com.example.a12525.bhplanet.ACTION_ZHUAN");
                startActivity(in);
                break;
            case R.id.zhuan2:
                Intent inte = new Intent("com.example.a12525.bhplanet.ACTION_ZHUAN");
                startActivity(inte);
                break;

            case R.id.dongtai1:
                Intent intent3 = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent3);
                break;
            case R.id.zhuanfa:
                Intent intent4 = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent4);
                break;
            case R.id.dongtai2:
                Intent inte4 = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(inte4);
                break;
        }


    }
}