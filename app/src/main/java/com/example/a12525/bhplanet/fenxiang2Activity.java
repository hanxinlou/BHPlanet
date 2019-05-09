package com.example.a12525.bhplanet;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class fenxiang2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiang2);
        ImageView fdongtai=(ImageView) findViewById(R.id.fdongtai);
        fdongtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fenxiang2Activity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView qq=(ImageView)findViewById(R.id.qq);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fenxiang2Activity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView weixin=(ImageView)findViewById(R.id.weixin);
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fenxiang2Activity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton quxiao=(ImageButton)findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
