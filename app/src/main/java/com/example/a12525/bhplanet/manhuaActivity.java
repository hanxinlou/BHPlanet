package com.example.a12525.bhplanet;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class manhuaActivity extends AppCompatActivity {
    private Button btn_botton_dialog;
    public static final int TAKE_PHOTO=1;
    private ImageView picture;
    private Uri imageUri;
    public static final int CHOOSE_PHOTO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhua);
        Button quxiao=(Button)findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
