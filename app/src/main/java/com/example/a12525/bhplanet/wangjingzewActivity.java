package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import pl.droidsonroids.gif.GifImageView;
public class wangjingzewActivity extends AppCompatActivity {
    private GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangjingzew);

        Intent intent = getIntent();
        String gif_url = intent.getStringExtra("gif_url");
        gif = (GifImageView)findViewById(R.id.wanchengtu);
        Glide.with(this).load(gif_url).into(gif);

        ImageButton fanhui=(ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button quxiao=(Button) findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button fenxiang=(Button)findViewById(R.id.fenxiang);
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wangjingzewActivity.this,fenxiangActivity.class);
                startActivity(intent);
            }
        });
        Button baocun=(Button)findViewById(R.id.baocun);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(wangjingzewActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        Button fabu=(Button)findViewById(R.id.fabu);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(wangjingzewActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

