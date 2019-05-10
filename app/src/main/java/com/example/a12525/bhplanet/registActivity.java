package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a12525.bhplanet.R;

public class registActivity extends AppCompatActivity {

    private Button regist1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        regist1 = (Button) findViewById(R.id.regist1);
        regist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getDatasync();
                Intent intent = new Intent(registActivity.this, loginActivity.class);
                startActivity(intent);
                Toast.makeText(registActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
