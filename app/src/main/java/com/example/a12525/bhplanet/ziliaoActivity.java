package com.example.a12525.bhplanet;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ziliaoActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO=1;
//    private static TextView mEdit;
    private ImageView picture;
    private Uri imageUri;
    public static final int CHOOSE_PHOTO=2;
    private TextView nickname, uid, sexx, birthh, qianmingg,tv_finish;
    private Map<String, String> mydata = new HashMap<>();
    private ImageButton gaitou, gainame, gaiid, gaisex, gaibirth, gaiqian;
    private LinearLayout mLayout2;
    private Button exit;
    private EditText mEdit2;
    private String picture1;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                setziliao();
                //picture.setImageResource(Integer.parseInt(mydata.get("picture")));

            }
            else if(msg.what == 0x456)
            {
                hideKeyboard();
                getDatasync(uid.getText().toString());
                Toast.makeText(ziliaoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziliao);
        Intent intent = getIntent();
        String id = Client.user_id;
        getDatasync(id);
        mLayout2 = (LinearLayout) findViewById(R.id.layout2);
        nickname = (TextView) findViewById(R.id.nickname);
        uid = (TextView)findViewById(R.id.uid);
        sexx = (TextView)findViewById(R.id.sexx);
        birthh = (TextView)findViewById(R.id.birthh);
        qianmingg = (TextView)findViewById(R.id.qianmingg);
        picture = (ImageView) findViewById(R.id.picture);
        tv_finish = (TextView)findViewById(R.id.tv_finish);

        ImageButton choose =(ImageButton)findViewById(R.id.gaitou);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        gaitou = (ImageButton)findViewById(R.id.gaitou);
        gainame = (ImageButton)findViewById(R.id.gainame);
       gaiid = (ImageButton)findViewById(R.id.gaiid);
       gaisex = (ImageButton)findViewById(R.id.gaisex);
       gaibirth = (ImageButton)findViewById(R.id.gaibirth);
       gaiqian = (ImageButton)findViewById(R.id.gaiqian);

        exit = (Button)findViewById(R.id.exit);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ziliaoActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(ziliaoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ziliaoActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    openAlbum();
                }
            }
        });
//        gaitou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               setTouXiang(Client.user_id,)
//            }
//        });
//        gainame.setOnClickListener( v-> ShowKeyboard("",nickname) );
        gainame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ShowKeyboard("",nickname);
            }
        });
        gaiid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowKeyboard("",uid);

            }
        });
        gaisex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowKeyboard("",sexx);

            }
        });
        gaibirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowKeyboard("",birthh);

            }
        });
        gaiqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowKeyboard("",qianmingg);
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postziliao();
            }
        });

    }


    public void ShowKeyboard(String msg,TextView mEdit){
        mEdit.setText(msg);
        mLayout2.setVisibility(View.VISIBLE);//显示布局
        mLayout2.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
    private void hideKeyboard(){
        mLayout2.setVisibility(View.GONE);//隐藏布局
//        mEdit2.setText("");//清空输入
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        hideKeyboard();
        return super.onKeyDown(keyCode, event);
    }

    public void getDatasync(String id){
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/information?user_id=" +id )//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("ndxq","response.code()=="+response.code());
                    Log.d("ndxq","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("ndxq","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);  //  处理对象的函数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }


    private void parseData(String resData){
        try{
            JSONObject jsonObject = new JSONObject(resData);
            Log.d("ndxq", "jsonObject==" + jsonObject);
            JSONObject dataObject =jsonObject.getJSONObject("content");
            Log.d("ndxq", "jsonObject2==" + dataObject);

            mydata.clear();
            mydata.put("user_name", dataObject.optString("user_name"));
            mydata.put("user_id", dataObject.optString("user_id"));
            mydata.put("birthday", dataObject.optString("birthday"));
            mydata.put("sex", dataObject.optString("sex"));
            mydata.put("introduce", dataObject.optString("introduce"));
            picture1 = dataObject.optString("picture");
//            mydata.put("picture", dataObject.optString("picture"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void postziliao(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/ThePlanet/user/update";
                FormBody.Builder formBody = new FormBody.Builder();

                    formBody.add("user_id", uid.getText().toString())
                            .add("user_name", nickname.getText().toString())
                            .add("introduce", qianmingg.getText().toString())
                            .add("sex",sexx.getText().toString())
                            .add("birthday", birthh.getText().toString());


                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Response response = Client.client.newCall(request).execute();
                if(response.isSuccessful()) {
                    Log.d("postziliao", "response.code()==" + response.code());
                    Log.d("postziliao", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("postziliao", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x456);
        }).start();
    }


    private void openAlbum()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
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
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap =BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }


            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+"id";
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.andriod.providers.downloads.document".equals(uri.getAuthority())){
                Uri contentUri=ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    private void setziliao()
    {
        nickname.setText(mydata.get("user_name"));
        uid.setText(mydata.get("user_id"));
        if(mydata.get("sex").equals("1"))
        {
            sexx.setText("男");
        }
        else if(mydata.get("sex").equals("2"))
        {
            sexx.setText("女");
        }
        birthh.setText(mydata.get("birthday"));
        qianmingg.setText(mydata.get("introduce"));
        init();
    }
    private  void init()
    {

        Glide.with(this).load(picture1).into(picture);
    }
}
