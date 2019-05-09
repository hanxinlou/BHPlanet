package com.example.a12525.bhplanet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class welcomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent intent1=new Intent(welcomeActivity.this,loginActivity.class);
                startActivity(intent1);
                welcomeActivity.this.finish();
            }
        };
        timer.schedule(timerTask,1000*2);
    }
}

