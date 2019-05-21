package com.example.a12525.bhplanet;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class settingsActivity extends AppCompatActivity {

    private Button ziliao, anquan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ziliao=(Button)findViewById(R.id.ziliao);
        ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingsActivity.this, ziliaoActivity.class);
                intent.putExtra("id", Client.user_id );
                startActivity(intent);
            }
        });


//        anquan=(Button)findViewById(R.id.anquan);
//        anquan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(settingsActivity.this,anquanActivity.class);
//                startActivity(intent);
//            }
//        });
        Button zhongxin=(Button)findViewById(R.id.zhongxin);
        zhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(settingsActivity.this,zhongxinActivity.class);
                startActivity(intent);
            }
        });
        Button yinsi=(Button)findViewById(R.id.yinsi);
        yinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(settingsActivity.this,kongjianActivity.class);
                startActivity(intent);
            }
        });
    }

}
