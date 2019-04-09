package com.example.a12525.bhplanet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends Fragment implements View.OnClickListener {
    private Button ping;
    private Button zan;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Activity_dongtai = inflater.inflate(R.layout.activity_dongtai, container, false);
        ping = (Button) Activity_dongtai.findViewById(R.id.ping);
        zan = (Button) Activity_dongtai.findViewById(R.id.zan);
        ping.setOnClickListener(this);
        zan.setOnClickListener(this);

        return Activity_dongtai;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ping:
                Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_PING");
                startActivity(intent);
        }
        switch (v.getId()) {
            case R.id.zan:

        }


    }
}