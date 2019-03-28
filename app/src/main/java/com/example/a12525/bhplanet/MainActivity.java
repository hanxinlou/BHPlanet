package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.example.a12525.bhplanet.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton shezhi=(ImageButton)findViewById(R.id.shezhi);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,settingsActivity.class);
                startActivity(intent);
            }
        });

        Button dongtai=(Button)findViewById(R.id.dongtai);
        dongtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,zdongtaiActivity.class);
                startActivity(intent);
            }
        });

        Button guanzhu=(Button)findViewById(R.id.guanzhu);
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,guanzhuActivity.class);
                startActivity(intent);
            }
        });
        Button fensi=(Button)findViewById(R.id.fensi);
        fensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,fensi.class);
                startActivity(intent);
            }
        });
        Button shoucang=(Button)findViewById(R.id.shoucang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,shoucangActivity.class);
                startActivity(intent);
            }
        });
        Button xiazai=(Button)findViewById(R.id.xiazai);
        xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,xiazaiActivity.class);
                startActivity(intent);
            }
        });
        Button lishi=(Button)findViewById(R.id.lishi);
        lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,lishiActivity.class);
                startActivity(intent);
            }
        });



    }
}
