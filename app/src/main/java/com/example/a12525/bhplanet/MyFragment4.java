package com.example.a12525.bhplanet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jay on 2015/8/28 0028.
 */

public class MyFragment4 extends Fragment implements View.OnClickListener{
    private ImageButton shezhi;
    private Button dongtai;
    private Button guanzhu;
    private Button fensi;
    private Button shoucang;
    private Button lishi;
    private Button xiazai;
    private Button zuopin;
    private Button xitong;
    private ImageView headpicture;
    private Map<String, String> mydata = new HashMap<>();
    private String picture1;
    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Activity_main = inflater.inflate(R.layout.activity_main, container, false);
        shezhi = (ImageButton) Activity_main.findViewById(R.id.shezhi);
        dongtai = (Button)Activity_main.findViewById(R.id.dongtai);
        guanzhu = (Button)Activity_main.findViewById(R.id.guanzhu);
        fensi = (Button)Activity_main.findViewById(R.id.fensi);
        shoucang = (Button)Activity_main.findViewById(R.id.shoucang);
        lishi = (Button)Activity_main.findViewById(R.id.lishi);
        headpicture = (ImageView)Activity_main.findViewById(R.id.head);
        String id =Client.user_id;
        getDatasync(id);
        getDatasync2(id);
//        zuopin = (Button)Activity_main.findViewById(R.id.zuopin);
        xitong = (Button)Activity_main.findViewById(R.id.xitong);
        shezhi.setOnClickListener(this);
        dongtai.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        fensi.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        lishi.setOnClickListener(this);
//        xiazai.setOnClickListener(this);
//        zuopin.setOnClickListener(this);
//        xitong.setOnClickListener(this);
        return Activity_main;
    }
    public  void onClick(View v) {
        switch (v.getId()){
            case R.id.shezhi:
                Intent intent=new Intent("com.example.a12525.bhplanet.ACTION_SHE");
                startActivity(intent);
                break;
           case R.id.dongtai:
                Intent intent1=new Intent("com.example.a12525.bhplanet.ACTION_ZDONG");
                startActivity(intent1);
                break;
           case R.id.guanzhu:
                Intent intent2=new Intent("com.example.a12525.bhplanet.ACTION_GUAN");
                intent2.putExtra("id", "1");
                startActivity(intent2);
                break;
            case R.id.fensi:
                Intent intent3=new Intent("com.example.a12525.bhplanet.ACTION_FEN");
                startActivity(intent3);
                break;
            case R.id.shoucang:
                Intent intent4=new Intent("com.example.a12525.bhplanet.ACTION_SHOU");
                startActivity(intent4);
                break;
            case R.id.lishi:
                Intent intent5=new Intent("com.example.a12525.bhplanet.ACTION_LI");
                startActivity(intent5);
                break;

            case R.id.zuopin:
                Intent intent7=new Intent("com.example.a12525.bhplanet.ACTION_ZUO");
                startActivity(intent7);
                break;
            case R.id.xitong:
                Intent intent8=new Intent("com.example.a12525.bhplanet.ACTION_TONG");
                startActivity(intent8);
                break;

        }

    }
    public void getDatasync2(String id){
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/information?user_id=" +id )//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("ndxq","response.code()=="+response.code());
                    Log.d("ndxq","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("ndxq","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData2(resData);  //  处理对象的函数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x456);
        }).start();
    }


    private void parseData2(String resData){
        try{
            JSONObject jsonObject = new JSONObject(resData);
            Log.d("ndxq", "jsonObject==" + jsonObject);
            JSONObject dataObject =jsonObject.getJSONObject("content");
            Log.d("ndxq", "jsonObject2==" + dataObject);

            mydata.clear();
            mydata.put("user_name", dataObject.optString("user_name"));
            mydata.put("user_id", dataObject.optString("user_id"));
            mydata.put("birthday", dataObject.optString("birthday"));
            mydata.put("sex", dataObject.optString("sex"));
            mydata.put("introduce", dataObject.optString("introduce"));
            picture1 = dataObject.optString("picture");
//            mydata.put("picture", dataObject.optString("picture"));

        }catch (Exception e){
            e.printStackTrace();
        }

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
            else if(msg.what == 0x456)
            {
                inithead();;
            }
        }
    };
    private void initcenter()
    {
        dongtai.setText("动态 "+mydata.get("dynamic_num"));
        guanzhu.setText("关注 "+mydata.get("concern_num"));
        fensi.setText("粉丝 "+mydata.get("fan_num"));


    }
    private void  inithead()
    {
        Glide.with(this).load(picture1).into(headpicture);
    }

  /*  @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }*/
}

