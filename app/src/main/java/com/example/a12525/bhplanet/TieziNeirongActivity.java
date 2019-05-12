package com.example.a12525.bhplanet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class TieziNeirongActivity extends Activity {
    private UnScrollListView listView1;
    private UnScrollListView listView2;
    private TextView _post_content, _post_title, _create_time, _user_name;
    private ImageView _picture, _content_picture;
    private List<pinglun> pinglunList = new ArrayList<>();

    private String post_id;
    private String post_title;
    private String post_content;
    private String picture;
    private String content_picture;
    private String user_name;
    private String create_time;
    private int zan_num;
    private int comment_num;

    private ArrayList<String> ping_content = new ArrayList<>();
    private ArrayList<String> ping_create_time = new ArrayList<>();
    private ArrayList<String> ping_user_name = new ArrayList<>();
    private ArrayList<String> ping_user_picture = new ArrayList<>();
    private ArrayList<Integer> ping_zan_num = new ArrayList<>();
    private ArrayList<Integer> ping_reply_num = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_tiezi_neirong);
        Intent intent = getIntent();
        post_id = intent.getStringExtra("post_id");
        getContent();
        getPing("0");
    }

    private void initPing(){
        for(int i = 0; i < ping_content.size(); i++)
        {
            pinglun ping = new pinglun(ping_user_picture.get(i),ping_user_name.get(i),ping_create_time.get(i),ping_content.get(i), R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(ping);
        }
        PingAdapter adapter = new PingAdapter(TieziNeirongActivity.this,R.layout.pinglun_item, pinglunList);
        listView1 = (UnScrollListView)findViewById(R.id.light_comment_list);
        listView2 = (UnScrollListView)findViewById(R.id.all_comment_list);
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
        listView1.setFocusable(false);
        listView2.setFocusable(false);
    }

    private void initContent(){
        _post_title = (TextView)findViewById(R.id.post_title);
        _post_content = (TextView)findViewById(R.id.content_text);
        _create_time = (TextView)findViewById(R.id.createtime);
        _user_name = (TextView)findViewById(R.id.username);
        _content_picture = (ImageView)findViewById(R.id.content_img);
        _picture = (ImageView)findViewById(R.id.user_img);

        _post_title.setText(post_title);
        _post_content.setText(post_content);
        _create_time.setText(create_time);
        _user_name.setText(user_name);

        Glide.with(this).load(picture).into(_picture);
        Glide.with(this).load(content_picture).into(_content_picture);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                initContent();
            }
            if(msg.what ==0x456){
                initPing();
            }
        }
    };

    public void getContent(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/post_content?post_id=" + post_id + "&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("getContent", "response.code()==" + response.code());
                    Log.d("getContent", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("getContent", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);
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
            JSONObject content = jsonObject.getJSONObject("content");
            post_title = content.optString("post_title");
            post_content = content.optString("post_content");
            picture = content.optString("picture");
            content_picture = content.optString("content_picture");
            user_name = content.optString("user_name");
            create_time = content.optString("create_time");
            zan_num = content.optInt("zan_num");
            comment_num = content.optInt("comment_num");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getPing(String status){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/comment?compose_type=2&from_opusid=" + post_id + "&page=1&status=" + status + "&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("getPing", "response.code()==" + response.code());
                    Log.d("getPing", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("getPing", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData2(resData);
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
            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray info = content.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                String p_content = object.optString("content");
                String p_create_time = object.optString("create_time");
                String p_user_name = object.optString("user_name");
                String p_user_picture = object.optString("user_picture");
                int p_zan_num = object.optInt("zan_num");
                int p_reply_num = object.optInt("reply_num");

                ping_content.add(p_content);
                ping_create_time.add(p_create_time);
                ping_user_name.add(p_user_name);
                ping_user_picture.add(p_user_picture);
                ping_zan_num.add(p_zan_num);
                ping_reply_num.add(p_reply_num);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}