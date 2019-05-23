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
import android.widget.ListView;
import android.widget.Toast;

import com.example.a12525.bhplanet.R;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class registActivity extends AppCompatActivity {

    private Button regist1;
    private EditText number, passwd1;

    private  String user_id, user_passwd;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                Toast.makeText(registActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        number =(EditText)findViewById(R.id.number);
        passwd1 =(EditText)findViewById(R.id.password1);
        regist1 = (Button) findViewById(R.id.regist1);
        regist1.setOnClickListener( v-> {
//                getDatasync();

            user_id=number.getText().toString();
            user_passwd=passwd1.getText().toString();
            Log.d("number",user_id);
            Log.d("number",user_passwd);
            postRegist(user_id,user_passwd);
        });

        }
    private void postRegist(String user_id,String user_passwd){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/user/regist";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("user_id", user_id)
                        .add("user_passwd", user_passwd);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("postRegist", "response.code()==" + response.code());
                    Log.d("postRegist", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("postRegist", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }
}
