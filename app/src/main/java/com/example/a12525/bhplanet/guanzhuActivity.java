package com.example.a12525.bhplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a12525.bhplanet.R;

import java.util.ArrayList;
import java.util.List;


public class guanzhuActivity extends AppCompatActivity {
    private List<guanzhu> guanzhuList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanzhu);
        initguanzhu();                 //初始化水果数据
        guanAdapter adapter=new guanAdapter(guanzhuActivity.this,R.layout.guanzhu_item,guanzhuList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initguanzhu(){
        for(int i=0;i<2;i++){
            guanzhu apple=new guanzhu(R.drawable.head,"江澄夫人");
            guanzhuList.add(apple);
            guanzhu apple1=new guanzhu(R.drawable.head,"小狐狸");
            guanzhuList.add(apple1);
            guanzhu apple2=new guanzhu(R.drawable.head,"鹿小草");
            guanzhuList.add(apple2);
            guanzhu apple3=new guanzhu(R.drawable.head,"孙笑川");
            guanzhuList.add(apple3);
            guanzhu apple4=new guanzhu(R.drawable.head,"团子");
            guanzhuList.add(apple4);
        }
    }

}