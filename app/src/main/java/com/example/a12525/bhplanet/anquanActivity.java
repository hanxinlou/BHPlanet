package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.example.a12525.bhplanet.R;

public class anquanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anquan);

        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        Button zhongxin=(Button)findViewById(R.id.zhongxin);
        zhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(anquanActivity.this,zhongxinActivity.class);
                startActivity(intent);
            }
        });
        Button yinsi=(Button)findViewById(R.id.yinsi);
        yinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(anquanActivity.this,kongjianActivity.class);
                startActivity(intent);
            }
        });

    }
}
