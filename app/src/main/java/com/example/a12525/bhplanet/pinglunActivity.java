package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a12525.bhplanet.R;

import java.util.ArrayList;
import java.util.List;


public class pinglunActivity extends AppCompatActivity {
    private List<pinglun> pinglunList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);

        ImageView zuotou=(ImageView)findViewById(R.id.zuotou);
        zuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(pinglunActivity.this,tdongtaiActivity.class);
                startActivity(intent);
            }
        });
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initPing();                 //初始化水果数据
        PingAdapter adapter=new PingAdapter(pinglunActivity.this,R.layout.pinglun_item,pinglunList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initPing(){
        for(int i=0;i<3;i++){
            pinglun apple=new pinglun(R.drawable.head,"你麻痹","1小时前","太好笑了", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(apple);
            pinglun cao=new pinglun(R.drawable.head1,"小狐狸","2小时前","咦我的头呢", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(cao);
            pinglun hehe=new pinglun(R.drawable.head2,"小蜜蜂","2小时前","鬼才", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(hehe);
            pinglun nima=new pinglun(R.drawable.head3,"嗡嗡嗡","2小时前","牛逼", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(nima);
        }
    }




}

