package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ZhuanActivity extends AppCompatActivity {

    private EditText zhuanneirong;
    private String content;
    private Intent intent;
    private String opus_id;
    private String opus_content;
    private String opus_picture;
    private String user_name;
    private ImageView picture;
    private TextView dongname, dongneirong;
    private Map<String, String> mydata = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan);
        picture=(ImageView)findViewById(R.id.dongimage);
        dongname=(TextView)findViewById(R.id.dongname);
        dongneirong=(TextView)findViewById(R.id.dongneirong);
        intent=getIntent();
        opus_id = getIntent().getStringExtra("opus_id");
        getDatasync(opus_id);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button fasong=(Button)findViewById(R.id.fasong);
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuanneirong = (EditText) findViewById(R.id.editText2);
                content=zhuanneirong.getText().toString();
                ZhuanFaZuoPing(opus_id,content);
                Toast.makeText(ZhuanActivity.this, "转发成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void ZhuanFaZuoPing(String opus_id, String content){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/transmit";
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)

                        .addFormDataPart("content", content)
                        .addFormDataPart("user_id", Client.user_id)
                        .addFormDataPart("opus_id", opus_id)
                        .build();
//                FormBody.Builder formBody = new FormBody.Builder();
//                formBody.add("opus_id", opus_id)
//                        .add("content", content)
//                        .add("user_id", Client.user_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(requestBody)
//                        .post(formBody.build())
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
    public void getDatasync(String opus_id){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/detail?opus_id=" + opus_id +
                        "&currpage=1" + "&user_id=" + Client.user_id;
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = Client.client.newCall(request).execute();
                if (response.isSuccessful()) {
                    Log.d("zhuanfa","response.code()==" + response.code());
                    Log.d("zhuan","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("zhuanfa1","res==" + resData);
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
//            opus_title = data.optString("opus_title");

            mydata.clear();
            mydata.put("opus_content", data.optString("opus_content"));
            mydata.put("user_name", data.optString("user_name"));
            mydata.put("opus_picture", data.optString("opus_picture"));
            Log.d("zhuanfa2",mydata.get("user_name"));

//            opus_content = data.optString("opus_content");
//            user_id = data.optString("user_id");
//            user_name = data.optString("user_name");
//            user_picture = data.optString("user_picture");
//            opus_picture = data.optString("opus_picture");
//            type = data.optInt("type");
//            zan_num = data.optInt("zan_num");
//            comment_num = data.optInt("comment_num");
//            transmit_num = data.optInt("transmit_num");
//            collect_num = data.optInt("collect_num");
//            is_ok = data.optInt("is_ok");
//            is_collect = data.optInt("is_collect");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                dongname.setText(mydata.get("user_name"));
                dongneirong.setText(mydata.get("opus_content"));
                initopus_pictures();
                //picture.setImageResource(Integer.parseInt(mydata.get("picture")));

            }
        }
    };
    private void initopus_pictures()
    {
        Glide.with(this).load(mydata.get("opus_picture")).into(picture);
    }
}
