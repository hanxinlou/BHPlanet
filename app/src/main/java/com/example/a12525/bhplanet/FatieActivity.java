package com.example.a12525.bhplanet;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FatieActivity extends Activity {
    private EditText editText1, editText2;
    private Button fabiao;
    private Button insert_img;
    private ImageView inserted_img;
    private static final int CHOOSE_PHOTO = 2;

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private String community_id;
    private File file;
    private String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatie);
        editText1 = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        insert_img = (Button)findViewById(R.id.insert_img);
        inserted_img = (ImageView)findViewById(R.id.inserted_img);
        fabiao = (Button)findViewById(R.id.fabiao);
        community_id = getIntent().getStringExtra("community_id");
        editText1.setHint("请输入标题");
        editText2.setHint("请输入内容");
        insert_img.setOnClickListener(v-> {
                if(ContextCompat.checkSelfPermission(FatieActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FatieActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
        });
        fabiao.setOnClickListener(v-> {
            getDatasync();
            finish();
        });
    }


    public void getDatasync(){
        new Thread(() -> {
            try {
                Log.d("imgPath", imgPath);
                file = new File(imgPath);

                String url = "http://129.211.5.66:8080/upload";
                RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", imgPath, fileBody)
                        .addFormDataPart("picture_type", String.valueOf(4))
                        .addFormDataPart("user_id", Client.user_id)
                        .addFormDataPart("title", editText1.getText().toString())
                        .addFormDataPart("content", editText2.getText().toString())
                        .addFormDataPart("opus_type", String.valueOf(4))
                        .addFormDataPart("community_id", community_id)
                        .build();

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(requestBody)
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("fatie","response.code()=="+response.code());
                    Log.d("fatie","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("fatie","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT >= 19){
                        imgPath = handleImageOnKitKat(data);
                    }else{
                        imgPath = handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private String handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+"id";
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.andriod.providers.downloads.document".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
        return imagePath;
    }

    private String handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
        return imagePath;
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            inserted_img.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
}
