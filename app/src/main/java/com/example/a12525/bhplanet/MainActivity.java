package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a12525.bhplanet.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private Button dongtai, guanzhu, fensi;
    private Map<String, String> mydata = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id =Client.user_id;
        getDatasync(id);
        dongtai = (Button)findViewById(R.id.dongtai);
        guanzhu = (Button)findViewById(R.id.guanzhu);
        fensi = (Button)findViewById(R.id.fensi);
        ImageButton shezhi = (ImageButton) findViewById(R.id.shezhi);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, settingsActivity.class);
                startActivity(intent);
            }
        });

        Button dongtai = (Button) findViewById(R.id.dongtai);
        dongtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, zdongtaiActivity.class);
                startActivity(intent);
            }
        });

        Button guanzhu = (Button) findViewById(R.id.guanzhu);
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, guanzhuActivity.class);
                startActivity(intent);
            }
        });
        Button fensi = (Button) findViewById(R.id.fensi);
        fensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, fensi.class);
                startActivity(intent);
            }
        });
        Button shoucang = (Button) findViewById(R.id.shoucang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shoucangActivity.class);
                startActivity(intent);
            }
        });

        Button lishi = (Button) findViewById(R.id.lishi);
        lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, lishiActivity.class);
                startActivity(intent);
            }
        });


    }
    public void getDatasync(String id){
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/center?user_id=" +id )//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("center","response.code()=="+response.code());
                    Log.d("center","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("center","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);  //  处理对象的函数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }


    private void parseData(String resData){
        try{
            JSONObject jsonObject = new JSONObject(resData);
            Log.d("center", "jsonObject==" + jsonObject);
            JSONObject dataObject =jsonObject.getJSONObject("content");
            Log.d("center", "jsonObject2==" + dataObject);
            mydata.clear();
            mydata.put("dynamic_num", dataObject.optString("dynamic_num"));
            mydata.put("concern_num", dataObject.optString("concern_num"));
            mydata.put("fan_num", dataObject.optString("fan_num"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initcenter();                 //初始化个人中心数据

            }
        }
    };
    private void initcenter()
    {
            dongtai.setText("动态 "+mydata.get("dynamic_num"));
            guanzhu.setText("关注 "+mydata.get("concern_num"));
            fensi.setText("粉丝 "+mydata.get("fan_num"));

    }
}
