package com.example.a12525.bhplanet;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.io.FileNotFoundException;

public class dongtuActivity extends AppCompatActivity {
    private Button btn_botton_dialog;
    public static final int TAKE_PHOTO=1;
    private ImageView picture;
    private Uri imageUri;
    public static final int CHOOSE_PHOTO=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtu);
        ImageButton fanhui=(ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
       ImageView wangjingze=(ImageView)findViewById(R.id.wangjingze);
       wangjingze.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(dongtuActivity.this,wangjingzeActivity.class);
               startActivity(intent);
          }
       });
        ImageView dagong=(ImageView)findViewById(R.id.dagong);
        dagong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dongtuActivity.this,dagongActivity.class);
                startActivity(intent);
            }
        });
        ImageView sorry=(ImageView)findViewById(R.id.sorry);
        sorry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dongtuActivity.this,sorryActivity.class);
                startActivity(intent);
            }
        });
        ImageView jinkela=(ImageView)findViewById(R.id.jinkela);
        jinkela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dongtuActivity.this,jinkelaActivity.class);
                startActivity(intent);
            }
        });
    }

}
