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
public class shoucangActivity extends AppCompatActivity {
    private List<shoucang> shoucangList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initShou();                 //初始化水果数据
        ShouAdapter adapter=new ShouAdapter(shoucangActivity.this,R.layout.shoucang_item,shoucangList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }
    private void initShou(){
        for(int i=0;i<3;i++){
            shoucang apple=new shoucang(R.drawable.song,"默认收藏夹");
            shoucangList.add(apple);
            shoucang cao=new shoucang(R.drawable.song1,"收藏夹1");
            shoucangList.add(cao);
            shoucang hehe=new shoucang(R.drawable.xiongmao,"收藏夹2");
            shoucangList.add(hehe);
            shoucang nima=new shoucang(R.drawable.head3,"收藏夹3");
            shoucangList.add(nima);
        }
    }

}
