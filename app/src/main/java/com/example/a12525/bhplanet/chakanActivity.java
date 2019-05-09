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
public class chakanActivity extends AppCompatActivity {
    private List<chakan> chakanList=new ArrayList<>();
    private boolean flag2=false;
    private ImageView dianliang1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakan);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianliang1=(ImageView)findViewById(R.id.dianliang1);
        dianliang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag2) {
                    dianliang1.setImageDrawable(getResources().getDrawable(R.drawable.dianliang));
                    flag2 = true;
                } else {
                    dianliang1.setImageDrawable(getResources().getDrawable(R.drawable.undianliang));
                    flag2 = false;
                }
            }
        });
        initCha();                 //初始化水果数据
       ChaAdapter adapter=new ChaAdapter(chakanActivity.this,R.layout.chakan_item,chakanList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }
    private void initCha(){
        for(int i=0;i<2;i++){
            chakan apple=new chakan(R.drawable.head3,"FGO秦始皇","2分钟前", R.drawable.undianliang,"你可真秀");
            chakanList.add(apple);
            chakan apple1=new chakan(R.drawable.head4,"安妮的独角兽","3分钟前", R.drawable.undianliang,"你是魔鬼吗？？？！！");
            chakanList.add(apple1);
            chakan apple2=new chakan(R.drawable.head5,"小可爱","4分钟前", R.drawable.undianliang,"收声啊雷！");
            chakanList.add(apple2);
        }
    }

}
