package com.example.a12525.bhplanet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class TieziNeirongActivity extends Activity {
    private boolean is_hide = true;
    private RecyclerView recyclerView1, recyclerView2;
    private TextView _post_content, _post_title, _create_time, _user_name, _zan_num, _comment_num;
    private ImageView _picture, _content_picture, dianZan;
    private Button commentButton;
    private EditText comment_text;
    private TextView comment_send;
    private List<pinglun> pinglunList = new ArrayList<>();
    private LinearLayout mLayout;
    private LinearLayout outLayout;

    private String post_id;
    private String post_title;
    private String post_content;
    private String picture;
    private String content_picture;
    private String user_name;
    private String user_id;
    private String create_time;
    private int is_ok;
    private int zan_num;
    private int comment_num;

    private ArrayList<String> ping_content = new ArrayList<>();
    private ArrayList<String> ping_create_time = new ArrayList<>();
    private ArrayList<String> ping_user_name = new ArrayList<>();
    private ArrayList<String> ping_user_id = new ArrayList<>();
    private ArrayList<String> ping_user_picture = new ArrayList<>();
    public  ArrayList<String> ping_compose_id = new ArrayList<>();
    private ArrayList<Integer> ping_zan_num = new ArrayList<>();
    private ArrayList<Integer> ping_is_ok = new ArrayList<>();
    private ArrayList<Integer> ping_reply_num = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_tiezi_neirong);
        Intent intent = getIntent();
        post_id = intent.getStringExtra("post_id");
        initView();
        getContent();
        getPing("0");

        dianZan.setOnClickListener( v -> {
            if(is_ok == 0){
                setDianZan(post_id,"4", "1", user_id);
            }
            else if(is_ok == 1){
                setDianZan(post_id,"4", "0", user_id);
            }
        });

        commentButton.setOnClickListener( v-> ShowKeyboard("") );
        comment_send.setOnClickListener( v -> postComment() );
        outLayout.setOnClickListener( v -> hideKeyboard() );

    }

    private void initView(){
        recyclerView1 = findViewById(R.id.light_comment_list);
        recyclerView2 = findViewById(R.id.all_comment_list);
        recyclerView1.setFocusable(false);
        recyclerView2.setFocusable(false);

        _post_title = (TextView)findViewById(R.id.post_title);
        _post_content = (TextView)findViewById(R.id.content_text);
        _create_time = (TextView)findViewById(R.id.createtime);
        _user_name = (TextView)findViewById(R.id.username);
        _content_picture = (ImageView)findViewById(R.id.content_img);
        _picture = (ImageView)findViewById(R.id.user_img);
        _zan_num = findViewById(R.id.zan_num);
        _comment_num = findViewById(R.id.comment_num);

        dianZan = findViewById(R.id.zan);
        commentButton = findViewById(R.id.comment);

        comment_text = findViewById(R.id.comment_text);
        comment_send = findViewById(R.id.comment_send);

        mLayout = findViewById(R.id.mlayout);
        outLayout = findViewById(R.id.outLayout);
    }

    private void initPing(){
        pinglunList.clear();
        for(int i = 0; i < ping_content.size(); i++)
        {
            if (ping_is_ok.get(i) == 1){
                pinglun ping = new pinglun(ping_user_picture.get(i), ping_user_name.get(i), ping_user_id.get(i),
                        ping_create_time.get(i), ping_content.get(i), R.drawable.dianliang,
                        ping_zan_num.get(i), ping_compose_id.get(i), ping_is_ok.get(i));
                pinglunList.add(ping);
            }else if (ping_is_ok.get(i) == 0){
                pinglun ping = new pinglun(ping_user_picture.get(i), ping_user_name.get(i), ping_user_id.get(i),
                        ping_create_time.get(i), ping_content.get(i), R.drawable.undianliang,
                        ping_zan_num.get(i), ping_compose_id.get(i), ping_is_ok.get(i));
                pinglunList.add(ping);
            }
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        ViewCompat.setNestedScrollingEnabled(recyclerView1, false);
        ViewCompat.setNestedScrollingEnabled(recyclerView2, false);

        TieziPingAdapter adapter = new TieziPingAdapter(pinglunList, this);
        recyclerView1.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);

    }

    private void initContent(){
        _post_title.setText(post_title);
        _post_content.setText(post_content);
        _create_time.setText(create_time);
        _user_name.setText(user_name);
        _zan_num.setText(String.valueOf(zan_num));
        _comment_num.setText(String.valueOf(comment_num));

        Glide.with(this).load(picture).into(_picture);
        Glide.with(this).load(content_picture).into(_content_picture);
        if (is_ok == 1){
            dianZan.setImageDrawable(getResources().getDrawable(R.drawable.zan));
        }else if (is_ok == 0){
            dianZan.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
        }

    }

    public void ShowKeyboard(String msg){
        comment_text.setText(msg);
        mLayout.setVisibility(View.VISIBLE);
        mLayout.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private void hideKeyboard(){
        mLayout.setVisibility(View.GONE);
        comment_text.setText("");
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 捕获返回键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //隐藏键盘与布局
        hideKeyboard();
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x123) {
                initContent();
            }
            else if(msg.what == 0x456){
                initPing();
            }
            else if(msg.what == 0x789){
                hideKeyboard();
                getPing("0");
                Toast.makeText(TieziNeirongActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
            }
            else if(msg.what == 0x444){
                getContent();
            }
            else if(msg.what == 0x222){
                getPing("0");
            }
        }
    };

    public void getContent(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/post_content?post_id=" + post_id + "&user_id=" + Client.user_id;
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
            user_id = content.optString("user_id");
            create_time = content.optString("create_time");
            zan_num = content.optInt("zan_num");
            comment_num = content.optInt("comment_num");
            is_ok = content.optInt("is_ok");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getPing(String status){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/comment?compose_type=2&from_opusid=" + post_id + "&page=1&status=" + status + "&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
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
            ping_content.clear();
            ping_create_time.clear();
            ping_user_name.clear();
            ping_user_id.clear();
            ping_user_picture.clear();
            ping_zan_num.clear();
            ping_reply_num.clear();
            ping_compose_id.clear();
            ping_is_ok.clear();
            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                String p_content = object.optString("content");
                String p_create_time = object.optString("create_time");
                String p_user_name = object.optString("user_name");
                String p_user_id = object.optString("from_userid");
                String p_user_picture = object.optString("user_picture");
                String p_compose_id = object.optString("compose_id");
                int p_zan_num = object.optInt("zan_num");
                int p_reply_num = object.optInt("reply_num");
                int p_is_ok = object.optInt("is_ok");

                ping_content.add(p_content);
                ping_create_time.add(p_create_time);
                ping_user_name.add(p_user_name);
                ping_user_id.add(p_user_id);
                ping_user_picture.add(p_user_picture);
                ping_zan_num.add(p_zan_num);
                ping_reply_num.add(p_reply_num);
                ping_compose_id.add(p_compose_id);
                ping_is_ok.add(p_is_ok);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void postComment(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/comment";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("compose_type", "2")
                        .add("content", comment_text.getText().toString())
                        .add("from_userid", Client.user_id)
                        .add("from_opusid", post_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("postComment", "response.code()==" + response.code());
                    Log.d("postComment", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("postComment", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x789);
        }).start();
    }

    public void setDianZan(String type_id, String type, String status, String to_userid){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/zan";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("type_id", type_id)
                        .add("type", type)
                        .add("user_id", Client.user_id)
                        .add("status", status)
                        .add("to_userid", to_userid);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("setDianZan", "response.code()==" + response.code());
                    Log.d("setDianZan", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("setDianZan", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (type.equals("4")){
                handler.sendEmptyMessage(0x444);
            }else if (type.equals("2")){
                handler.sendEmptyMessage(0x222);
            }

        }).start();
    }
}