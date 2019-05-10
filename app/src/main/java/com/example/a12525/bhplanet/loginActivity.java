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
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class loginActivity extends AppCompatActivity {


    private EditText phonenumber,passwd;
    private Button login,regist;
    private  String phonenumber2,passwd2;
    private Map<String, String> mydata = new HashMap<>();
    private String code;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                if(code.equals("dxbvEcEX7d"))
                {
                    Toast.makeText(loginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(loginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
//                if(phonenumber1.equals(phonenumber2)&&passwd1.equals(passwd2))
//                {
//                    Toast.makeText(loginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(loginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
//                }
//                nickname.setText(mydata.get("user_name"));
//                uid.setText(mydata.get("user_id"));
////                sexx.setText(mydata.get("birthday"));
//                birthh.setText(mydata.get("sex"));
//                qianmingg.setText(mydata.get("introduce"));
                //picture.setImageResource(Integer.parseInt(mydata.get("picture")));

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber=(EditText)findViewById(R.id.phonenumber);
        passwd=(EditText)findViewById(R.id.passwd);
        login=(Button)findViewById(R.id.login);
        regist=(Button)findViewById(R.id.regist);
        phonenumber2=phonenumber.getText().toString();
        passwd2=passwd.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatasync();
                Intent intent=new Intent(loginActivity.this,DaohangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this,registActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getDatasync(){
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("https://nei.netease.com/api/apimock/53f5f289ef8648edd032ecb28f5ac8da/user/login?user_id="+phonenumber2+"&user_passwd=" + passwd2)//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("ndxq","response.code()=="+response.code());
                    Log.d("ndxq","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("ndxq","res=="+resData);
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
            //Log.d("ndxq", "jsonObject==" + jsonObject);
//            JSONObject dataObject =jsonObject.getJSONObject("content");
//            Log.d("ndxq", "jsonObject2==" + dataObject);
              code = jsonObject.optString("code");

//            mydata.clear();
//            mydata.put("phonenumber1", dataObject.optString("user_id"));
//            mydata.put("passwd1", dataObject.optString("user_passwd"));
//            mydata.put("birthday", dataObject.optString("birthday"));
//            mydata.put("sex", dataObject.optString("sex"));
//            mydata.put("introduce", dataObject.optString("introduce"));
//            mydata.put("picture", dataObject.optString("picture"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
