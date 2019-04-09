package com.example.a12525.bhplanet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FatieActivity extends Activity {
    private EditText editText1, editText2;
    private Button fabiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatie);
        editText1 = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        fabiao = (Button)findViewById(R.id.fabiao);
        editText1.setHint("请输入标题");
        editText2.setHint("请输入内容");
        fabiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FatieActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
