package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class jinkelaActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private EditText editText1, editText2, editText3, editText4, editText5, editText6;
    private String []text = new String[6];
    private String resData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinkela);
        editText1 = (EditText) findViewById(R.id.one);
        editText2 = (EditText) findViewById(R.id.two);
        editText3 = (EditText) findViewById(R.id.three);
        editText4 = (EditText) findViewById(R.id.four);
        editText5 = (EditText) findViewById(R.id.five);
        editText6 = (EditText) findViewById(R.id.six);


        ImageView wancheng = (ImageView) findViewById(R.id.wancheng);
        wancheng.setOnClickListener(v-> getDatasync());
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(v -> finish());
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                Intent intent = new Intent(jinkelaActivity.this,wangjingzewActivity.class);
                intent.putExtra("gif_url", "https://sorry.xuty.tk" + resData);
                Toast.makeText(jinkelaActivity.this, "图片生成中……", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }
    };

    public void getDatasync(){
        new Thread(() -> {
            try {
                String url = "https://sorry.xuty.tk/api/jinkela/make";
                String json = getJson();
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = Client.client.newCall(request).execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("jinkela","response.code()=="+response.code());
                    Log.d("jinkela","response.message()=="+response.message());
                    resData = response.body().string();
                    Log.d("jinkela","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }


    private String getJson() throws JSONException {
        text[0] = editText1.getText().toString();
        text[1] = editText2.getText().toString();
        text[2] = editText3.getText().toString();
        text[3] = editText4.getText().toString();
        text[4] = editText5.getText().toString();
        text[5] = editText6.getText().toString();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("0", text[0]);
        jsonObject.put("1", text[1]);
        jsonObject.put("2", text[2]);
        jsonObject.put("3", text[3]);
        jsonObject.put("4", text[4]);
        jsonObject.put("5", text[5]);

        return jsonObject.toString();
    }
}
