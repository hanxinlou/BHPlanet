package com.example.a12525.bhplanet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class tupianwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tupianw);
        ImageButton fanhui=(ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button quxiao=(Button) findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button fenxiang=(Button)findViewById(R.id.fenxiang);
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tupianwActivity.this,fenxiang2Activity.class);
                startActivity(intent);
            }
        });
        Button baocun=(Button)findViewById(R.id.baocun);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tupianwActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        Button fabu=(Button)findViewById(R.id.fabu);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tupianwActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
            }
        });
//        private void downloadFile(){
//            if (Build.VERSION.SDK_INT >= 23) {
//                int REQUEST_CODE_CONTACT = 101;
//                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                //验证是否许可权限
//                for (String str : permissions) {
//                    if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                        //申请权限
//                        this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
//                    }
//                }
//            }
//
//            //下载路径，如果路径无效了，可换成你的下载路径
//            final long startTime = System.currentTimeMillis();
//            Log.i("DOWNLOAD","startTime="+startTime);
//            Toast.makeText(tupianwActivity.this, "保存中……", Toast.LENGTH_SHORT).show();
//            Request request = new Request.Builder().url(picture_url).build();
//            new OkHttpClient().newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    // 下载失败
//                    e.printStackTrace();
//                    Log.i("DOWNLOAD","download failed");
//                    Toast.makeText(tupianwActivity.this, "保存失败！请重试", Toast.LENGTH_SHORT).show();
//                }
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    Sink sink = null;
//                    BufferedSink bufferedSink = null;
//                    try {
//                        String mSDCardPath= Environment.getExternalStorageDirectory().getAbsolutePath();
//                        File dest = new File(mSDCardPath,   gif_url.substring(gif_url.lastIndexOf("/") + 1));
//                        sink = Okio.sink(dest);
//                        bufferedSink = Okio.buffer(sink);
//                        bufferedSink.writeAll(response.body().source());
//                        Toast.makeText(tupianwActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
//                        bufferedSink.close();
//                        Log.i("DOWNLOAD","download success");
//                        Log.i("DOWNLOAD","totalTime="+ (System.currentTimeMillis() - startTime));
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Log.i("DOWNLOAD","download failed");
//                        Toast.makeText(wangjingzewActivity.this, "保存失败！请重试", Toast.LENGTH_SHORT).show();
//                    } finally {
//                        if(bufferedSink != null){
//                            bufferedSink.close();
//                        }
//
//                    }
//                }
//            });
//        }

    }
}
