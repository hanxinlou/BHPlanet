package com.example.a12525.bhplanet;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.a12525.bhplanet.R;

public class dongtaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtai);

        Button pinglun=(Button) findViewById(R.id.ping);
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dongtaiActivity.this,pinglunActivity.class);
                startActivity(intent);
            }
        });



    }
}
