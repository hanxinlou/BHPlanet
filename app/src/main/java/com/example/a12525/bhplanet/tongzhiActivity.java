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
public class tongzhiActivity extends AppCompatActivity {
    private List<tongzhi> tongzhiList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongzhi);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTong();                 //初始化水果数据
        TongAdapter adapter=new TongAdapter(tongzhiActivity.this,R.layout.tongzhi_item,tongzhiList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }
    private void initTong(){
        for(int i=0;i<3;i++){
            tongzhi apple=new tongzhi(R.drawable.head1,"小怪兽","12-08","12:08","赞了你的XXX");
            tongzhiList.add(apple);
            tongzhi apple1=new tongzhi(R.drawable.head,"小老鼠","11-11","10:48","赞了你的XXX");
            tongzhiList.add(apple1);
            tongzhi apple2=new tongzhi(R.drawable.head2,"奥特曼","10-24","17:36","赞了你的XXX");
            tongzhiList.add(apple2);
            tongzhi apple3=new tongzhi(R.drawable.head8,"可爱的小象","10-12","09:37","赞了你的XXX");
            tongzhiList.add(apple3);
        }
    }

}

