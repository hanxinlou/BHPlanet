package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12525.bhplanet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class zdongtaiActivity extends AppCompatActivity {
    private TextView gnumber, fnumber, uname, qianming;
    private ImageView tou, ubeijing;
    private List<zdongtai> zdongtaiList = new ArrayList<>();

    private String picture;
    private String bg_picture;

    private String dynamic_id;
    private String user_id;
    private String user_name;
    private String user_picture;
    private String content;
    private String link_id;
    private String author_id;
    private String author_name;
    private String author_picture;
    private String opus_content;
    private String opus_picture;
    private Number zan_num;
    private Number comment_num;
    private Number transmit_num;
    private String create_time;
    private Number zan;
    private RecyclerView recyclerView;
    private ArrayList<String> dynamic_id_list = new ArrayList<>();
    private ArrayList<String> user_id_list = new ArrayList<>();
    private ArrayList<String> user_name_list = new ArrayList<>();
    private ArrayList<String> user_picture_list = new ArrayList<>();
    private ArrayList<String> content_list = new ArrayList<>();
    private ArrayList<String> link_id_list = new ArrayList<>();
    private ArrayList<String> author_id_list = new ArrayList<>();
    private ArrayList<String> author_name_list = new ArrayList<>();
    private ArrayList<String> author_picture_list = new ArrayList<>();
    private ArrayList<String> opus_content_list = new ArrayList<>();
    private ArrayList<String> opus_picture_list = new ArrayList<>();
    private ArrayList<Number> zan_num_list = new ArrayList<>();
    private ArrayList<Number> comment_num_list = new ArrayList<>();
    private ArrayList<Number> transmit_num_list = new ArrayList<>();
    private ArrayList<String> create_time_list = new ArrayList<>();
    private ArrayList<Number> zan_list = new ArrayList<>();

    private Map<String, String> mydata = new HashMap<>();
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            if (msg.what == 0x111) {
                initzdongtai();
                init1();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zdongtai);
        Intent intent = getIntent();
        String id = Client.user_id;
        getDatasync2(id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tou = (ImageView) findViewById(R.id.tou);
        ubeijing = (ImageView) findViewById(R.id.ubeijing);
//        gnumber = (TextView) findViewById(R.id.gnumber);
//        fnumber = (TextView) findViewById(R.id.fnumber);
        qianming = (TextView) findViewById(R.id.qianming);
        uname = (TextView) findViewById(R.id.uname);

        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    void init1()
    {

//        gnumber.setText(mydata.get("concern_num"));
//        fnumber.setText(mydata.get("fan_num"));
        uname.setText(mydata.get("user_name1"));
        qianming.setText(mydata.get("introduce"));

        Glide.with(this).load(picture).into(tou);
        Glide.with(this).load(bg_picture).into(ubeijing);
    }



    private void initzdongtai() {
        zdongtaiList.clear();
        for (int i = 0; i < user_name_list.size(); i++) {
            zdongtai apple = new zdongtai(user_picture_list.get(i), user_name_list.get(i), create_time_list.get(i),
                    content_list.get(i), author_picture_list.get(i), author_name_list.get(i), opus_picture_list.get(i),
                    opus_content_list.get(i), R.drawable.zhuan, transmit_num_list.get(i), R.drawable.ping, comment_num_list.get(i),
                    R.drawable.unzan, zan_num_list.get(i),link_id_list.get(i));
            zdongtaiList.add(apple);

            recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            ViewCompat.setNestedScrollingEnabled(recyclerView, false);
            ZdongtaiAdapter adapter = new ZdongtaiAdapter(zdongtaiList, this);
            recyclerView.setAdapter(adapter);
        }
    }

    public void getDatasync2(String id){
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/homepage?user_id=" + id + "&currpage=1"+"&vistor_id=" + id )//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("zdongtai3","response.code()=="+response.code());
                    Log.d("zdongtai3","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("zdongtai3","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData2(resData);  //  处理对象的函数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x111);
        }).start();
    }
    private void parseData2(String resData) {
        try {
            JSONObject jsonObject = new JSONObject(resData);
            JSONObject data = jsonObject.getJSONObject("content");

            mydata.put("fan_num", data.optString("fan_num"));
            mydata.put("concern_num", data.optString("concern_num"));
            mydata.put("user_name1", data.optString("user_name"));
            mydata.put("introduce", data.optString("introduce"));
            bg_picture = data.optString("bg_picture");
            picture = data.optString("picture");



            JSONArray info = data.getJSONArray("info");

            dynamic_id_list.clear();
            user_id_list.clear();
            user_name_list.clear();
            user_picture_list.clear();
            content_list.clear();
            link_id_list.clear();
            author_id_list.clear();
            author_name_list.clear();
            author_picture_list.clear();
            opus_picture_list.clear();
            opus_content_list.clear();
            zan_num_list.clear();
            comment_num_list.clear();
            transmit_num_list.clear();
            create_time_list.clear();
            zan_list.clear();

            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                dynamic_id = object.optString("dynamic_id");
                user_id = object.optString("user_id");
                user_name = object.optString("user_name");
                user_picture = object.optString("user_picture");
                content = object.optString("content");
                link_id = object.optString("link_id");
                author_id = object.optString("author_id");
                author_name = object.optString("author_name");
                author_picture = object.optString("author_picture");
                opus_content = object.optString("opus_content");
                opus_picture = object.optString("opus_picture");
                zan_num = object.optDouble("zan_num");
                comment_num = object.optDouble("comment_num");
                transmit_num = object.optDouble("transmit_num");
                create_time = object.optString("create_time");
                zan = object.optDouble("zan");

                dynamic_id_list.add(dynamic_id);
                user_id_list.add(user_id);
                user_name_list.add(user_name);
                user_picture_list.add(user_picture);
                content_list.add(content);
                link_id_list.add(link_id);
                author_id_list.add(author_id);
                author_name_list.add(author_name);
                author_picture_list.add(author_picture);
                opus_picture_list.add(opus_picture);
                opus_content_list.add(opus_content);
                zan_num_list.add(zan_num);
                comment_num_list.add(comment_num);
                transmit_num_list.add(transmit_num);
                create_time_list.add(create_time);
                zan_list.add(zan);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
