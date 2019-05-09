package com.example.a12525.bhplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initguanzhu();                 //初始化水果数据
        guanAdapter adapter=new guanAdapter(guanzhuActivity.this,R.layout.guanzhu_item,guanzhuList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initguanzhu(){
        for(int i=0;i<2;i++){
            guanzhu apple=new guanzhu(R.drawable.head5,"江澄夫人");
            guanzhuList.add(apple);
            guanzhu apple1=new guanzhu(R.drawable.head12,"小狐狸");
            guanzhuList.add(apple1);
            guanzhu apple2=new guanzhu(R.drawable.head11,"鹿小草");
            guanzhuList.add(apple2);
            guanzhu apple3=new guanzhu(R.drawable.head3,"孙笑川");
            guanzhuList.add(apple3);
            guanzhu apple4=new guanzhu(R.drawable.head6,"团子");
            guanzhuList.add(apple4);
            guanzhu apple5=new guanzhu(R.drawable.head8,"团子");
            guanzhuList.add(apple5);
            guanzhu apple6=new guanzhu(R.drawable.head4,"团子");
            guanzhuList.add(apple6);
            guanzhu apple7=new guanzhu(R.drawable.head10,"团子");
            guanzhuList.add(apple7);
            guanzhu apple8=new guanzhu(R.drawable.head10,"团子");
            guanzhuList.add(apple8);
            guanzhu apple9=new guanzhu(R.drawable.head9,"团子");
            guanzhuList.add(apple9);
            guanzhu apple10=new guanzhu(R.drawable.head8,"团子");
            guanzhuList.add(apple10);
        }
    }

}