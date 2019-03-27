package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import com.example.yy.mee.R;

public class zdongtaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zdongtai);

        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(zdongtaiActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
