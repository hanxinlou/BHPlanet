package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class pinglunActivity extends AppCompatActivity {
    private ArrayList<pinglun> pinglunList = new ArrayList<>();
//    private boolean flag = false;
//    private boolean flag1 = false;
    private ImageView dianzan;
    private ImageView shoucang;
    private ImageButton fanhui;
    private ImageButton zhuanfa;
    private Button pinglun;
    private LinearLayout relativeLayout;
    private TextView tv_confirm;
    private LinearLayout mLayout;
    private EditText mEdit;
    private RecyclerView recyclerView;

    private TextView zan_num_ui, comment_num_ui, transmit_num_ui, collect_num_ui;
    private TextView opus_title_ui, opus_content_ui, user_name_ui;
    private ImageView user_picture_ui, opus_picture_ui;

    private String opus_id;
    private String opus_title;
    private String opus_content;
    private String opus_picture;
    private int type;
    private String user_id;
    private String user_name;
    private String user_picture;
    private int zan_num;
    private int comment_num;
    private int transmit_num;
    private int collect_num;
    private int is_ok;
    private int is_collect;
    private String content;


    private ArrayList<String> ping_content = new ArrayList<>();
    private ArrayList<String> ping_create_time = new ArrayList<>();
    private ArrayList<String> ping_user_name = new ArrayList<>();
    private ArrayList<String> ping_user_picture = new ArrayList<>();
    private ArrayList<String> ping_user_id = new ArrayList<>();
    public  ArrayList<String> ping_compose_id = new ArrayList<>();
    private ArrayList<Integer> ping_zan_num = new ArrayList<>();
    private ArrayList<Integer> ping_is_ok = new ArrayList<>();
    private ArrayList<Integer> ping_reply_num = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);
        initView();
        getDatasync();
        getPing();

        user_picture_ui.setOnClickListener( v -> {
                Intent intent = new Intent(pinglunActivity.this,tdongtaiActivity.class);
                startActivity(intent);
        });
        fanhui.setOnClickListener( v -> finish() );
        zhuanfa.setOnClickListener( v -> {
                Intent intent=new Intent(pinglunActivity.this,ZhuanActivity.class);
                intent.putExtra("opus_id", opus_id );
                content = intent.getExtras().toString();
                setZhuanfa(opus_id,content);
                getDatasync();
                startActivity(intent);

//
        });

        dianzan.setOnClickListener( v -> {
            if(is_ok == 0){
                setDianZan(opus_id,"1", "1", user_id);
            }
            else if(is_ok == 1){
                setDianZan(opus_id,"1", "0", user_id);
            }
        });

        shoucang.setOnClickListener( v -> {
                Log.d("is_collect",String.valueOf(is_collect));
                if(is_collect == 0) {
                    setShouCang(opus_id,"1");
                } else if(is_collect == 1){
                    setShouCang(opus_id,"2");
                }
        });

        pinglun.setOnClickListener( v-> ShowKeyboard("") );
        tv_confirm.setOnClickListener( v -> postComment() );
        relativeLayout.setOnClickListener( v -> hideKeyboard() );

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        initView();
        getDatasync();
        getPing();
    }
    void initView(){
        opus_id = getIntent().getStringExtra("home_opus_id");
        recyclerView = findViewById(R.id.recyclerView);
        zan_num_ui = findViewById(R.id.zan_num);
        comment_num_ui = findViewById(R.id.comment_num);
        transmit_num_ui = findViewById(R.id.transmit_num);
        collect_num_ui = findViewById(R.id.collect_num);
        opus_title_ui = findViewById(R.id.opus_title);
        opus_content_ui = findViewById(R.id.opus_content);
        user_name_ui = findViewById(R.id.user_name);
        user_picture_ui = findViewById(R.id.user_picture);
        opus_picture_ui = findViewById(R.id.opus_picture);


        mLayout = (LinearLayout) findViewById(R.id.layout);
        mEdit = (EditText) findViewById(R.id.et_discuss);
        fanhui=(ImageButton)findViewById(R.id.fanhui);
        zhuanfa = (ImageButton)findViewById(R.id.zhuanfa);
        dianzan = (ImageView) findViewById(R.id.dianzan);
        shoucang = (ImageView) findViewById(R.id.shoucang);
        pinglun = (Button)findViewById(R.id.pinglun);
        tv_confirm = (TextView)findViewById(R.id.tv_confirm);
        relativeLayout = (LinearLayout) findViewById(R.id.relativeLayout);

    }

    void initContent(){
        zan_num_ui.setText(String.valueOf(zan_num));
        comment_num_ui.setText(String.valueOf(comment_num));
        transmit_num_ui.setText(String.valueOf(transmit_num));
        collect_num_ui.setText(String.valueOf(collect_num));
        opus_title_ui.setText(opus_title);
        opus_content_ui.setText(opus_content);
        user_name_ui.setText(user_name);
        Glide.with(this).load(user_picture).into(user_picture_ui);
        Glide.with(this).load(opus_picture).into(opus_picture_ui);
        if (is_ok == 1){
            dianzan.setImageDrawable(getResources().getDrawable(R.drawable.zan));
        }else if (is_ok == 0){
            dianzan.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
        }
        if(is_collect == 1){
            shoucang.setImageDrawable(getResources().getDrawable(R.drawable.shoucang));
        }
        else if(is_collect == 0){
            shoucang.setImageDrawable(getResources().getDrawable(R.drawable.unshoucang));
        }
    }

    public void ShowKeyboard(String msg){
        mEdit.setText(msg);
        mLayout.setVisibility(View.VISIBLE);//显示布局
        mLayout.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private void hideKeyboard(){
        mLayout.setVisibility(View.GONE);//隐藏布局
        mEdit.setText("");//清空输入
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 捕获返回键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        hideKeyboard();
        return super.onKeyDown(keyCode, event);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        PingAdapter adapter = new PingAdapter(pinglunList, this);
        recyclerView.setAdapter(adapter);
    }

    public void getDatasync(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/detail?opus_id=" + opus_id +
                        "&currpage=1" + "&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = Client.client.newCall(request).execute();
                if (response.isSuccessful()) {
                    Log.d("shouye","response.code()==" + response.code());
                    Log.d("shouye","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("shouye","res==" + resData);
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
            JSONObject object = new JSONObject(resData);
            JSONObject data = object.getJSONObject("content");
            opus_title = data.optString("opus_title");
            opus_content = data.optString("opus_content");
            user_id = data.optString("user_id");
            user_name = data.optString("user_name");
            user_picture = data.optString("user_picture");
            opus_picture = data.optString("opus_picture");
            type = data.optInt("type");
            zan_num = data.optInt("zan_num");
            comment_num = data.optInt("comment_num");
            transmit_num = data.optInt("transmit_num");
            collect_num = data.optInt("collect_num");
            is_ok = data.optInt("is_ok");
            is_collect = data.optInt("is_collect");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getPing(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/comment?compose_type=1&from_opusid=" + opus_id + "&page=1&status=0&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("getPing", "response.code()==" + response.code());
                    Log.d("getPing", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("getPing", "res==" + resData);
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

    public void setShouCang(String opus_id, String type){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/collect";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("opus_id", opus_id)
                        .add("type", type)
                        .add("user_id", Client.user_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("setShouCang", "response.code()==" + response.code());
                    Log.d("setShouCang", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("setShouCang", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                handler.sendEmptyMessage(0x111);

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
            if (type.equals("1")){
                handler.sendEmptyMessage(0x111);
            }else if (type.equals("2")){
                handler.sendEmptyMessage(0x222);
            }

        }).start();
    }
    public void setZhuanfa(String opus_id, String content){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/transmit";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("opus_id", opus_id)
                        .add("content", content)
                        .add("user_id", Client.user_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("setShouCang", "response.code()==" + response.code());
                    Log.d("setShouCang", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("setShouCang", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            handler.sendEmptyMessage(0x111);

        }).start();
    }
    private void postComment(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/comment";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("compose_type", "1") //1 = 作品评论
                        .add("content", mEdit.getText().toString())
                        .add("from_userid", Client.user_id)
                        .add("from_opusid", opus_id);

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

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if(msg.what == 0x123) {
                initContent();
            }
            else if(msg.what == 0x456){
                initPing();
            }
            else if(msg.what == 0x789){
                hideKeyboard();
                getPing();
                Toast.makeText(pinglunActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
            }
            else if(msg.what == 0x111){
                getDatasync();
            }
            else if(msg.what == 0x222){
                getPing();
            }
        }
    };
}
