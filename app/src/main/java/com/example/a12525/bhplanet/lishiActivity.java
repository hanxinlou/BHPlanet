package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.a12525.bhplanet.R;

import java.util.ArrayList;
import java.util.List;

public class lishiActivity extends AppCompatActivity {
    private List<lishi> lishiList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishi);

        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
            });

        initLi();                 //初始化水果数据
        LiAdapter adapter=new LiAdapter(lishiActivity.this,R.layout.lishi_item,lishiList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initLi(){
        for(int i=0;i<3;i++){
            lishi apple=new lishi(R.drawable.song,"太好笑了","你麻痹","1小时前");
            lishiList.add(apple);
            lishi cao=new lishi(R.drawable.song1,"咦我的头呢","小狐狸","2小时前");
            lishiList.add(cao);
            lishi hehe=new lishi(R.drawable.lu,"鬼才","小蜜蜂","2小时前");
            lishiList.add(hehe);
            lishi nima=new lishi(R.drawable.xiongmao,"牛逼","嗡嗡嗡","2小时前");
            lishiList.add(nima);
        }
    }

}
