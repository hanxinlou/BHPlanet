package com.example.a12525.bhplanet;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.Handler;
import com.example.a12525.bhplanet.R;

import java.util.HashMap;
import java.util.Map;

public class settingsActivity extends AppCompatActivity {

    private Button ziliao, anquan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ziliao=(Button)findViewById(R.id.ziliao);
        ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingsActivity.this, ziliaoActivity.class);
                intent.putExtra("id", "1");
                startActivity(intent);
            }
        });


        anquan=(Button)findViewById(R.id.anquan);
        anquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(settingsActivity.this,anquanActivity.class);
                startActivity(intent);
            }
        });
    }

}
