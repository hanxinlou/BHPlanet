package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuanActivity extends AppCompatActivity {

    private EditText zhuanneirong;
    private String content;
    private Intent intent;
    private String opus_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan);

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
                content=zhuanneirong.toString();
                intent=getIntent();
                opus_id=intent.getExtras().toString();
                ZhuanFaZuoPing(opus_id,content);
                Toast.makeText(ZhuanActivity.this, "转发成功", Toast.LENGTH_SHORT).show();
                intent.putExtra("content", content);
                finish();
            }
        });

    }
    public void ZhuanFaZuoPing(String opus_id, String content){
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
}
