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
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class tupianActivity extends AppCompatActivity implements View.OnClickListener{
    //图片组件
    private ImageView imageView;
    //位于图片中的文本组件
    private TextView tvInImage;
    //图片和文本的父组件
    private View containerView;
    //父组件的尺寸
    private float imageWidth, imageHeight, imagePositionX, imagePositionY;

    public static final int TAKE_PHOTO=1;

    private Uri imageUri;
    public static final int CHOOSE_PHOTO=2;
    private View inflate;
    private TextView sharedongtai;
    private TextView shareQQ;
    private TextView sharewechat;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tupian);

        Button choose = (Button) findViewById(R.id.chuantu);
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Button wancheng = (Button) findViewById(R.id.wancheng);
//        wancheng.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(tupianActivity.this, tupianwActivity.class);
//                startActivity(intent);
//            }
//        });
        Button quxiao=(Button) findViewById(R.id.quxiao1);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Button fenxiang=(Button)findViewById(R.id.btn_fenxiang);
//      fenxiang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 showdailog(v);
//                Intent intent = new Intent(tupianActivity.this,fenxiang2Activity.class);
//                startActivity(intent);
//            }
//        });
        Button baocun=(Button)findViewById(R.id.btn_baocun);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long startTime = System.currentTimeMillis();
//                Log.i("DOWNLOAD","startTime="+startTime);
                Toast.makeText(tupianActivity.this, "保存中……", Toast.LENGTH_SHORT).show();
                confirm(v);
//                Toast.makeText(tupianActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        Button fabu=(Button)findViewById(R.id.btn_fabu);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tupianActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
            }
        });
        imageView = (ImageView) findViewById(R.id.writeText_img);
        EditText editText = (EditText) findViewById(R.id.writeText_et);
        tvInImage = (TextView) findViewById(R.id.writeText_image_tv);
        containerView = findViewById(R.id.writeText_img_rl);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                imagePositionX = imageView.getX();
                imagePositionY = imageView.getY();
                imageWidth = imageView.getWidth();
                imageHeight = imageView.getHeight();
                //设置文本大小
                tvInImage.setMaxWidth((int) imageWidth);
            }
        });
        imageView.setImageBitmap(getScaledBitmap(R.drawable.xiongmaotou));
        //输入框
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    tvInImage.setVisibility(View.INVISIBLE);
                } else {
                    tvInImage.setVisibility(View.VISIBLE);
                    tvInImage.setText(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        final GestureDetector gestureDetector = new GestureDetector(this, new SimpleGestureListenerImpl());
        //移动
        tvInImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        /*take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri=FileProvider.getUriForFile(tupianActivity.this,"com.example.yy.dongtai.fileprovider",outputImage);
                }else{
                    imageUri=Uri.fromFile(outputImage);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });*/
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(tupianActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(tupianActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    openAlbum();
                }
            }
        });
    }



    public void confirm(View view) {
        Bitmap bm = loadBitmapFromView(containerView);
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "image_with_text.jpg";

        try {
            bm.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
            Toast.makeText(this, "图片已保存至：SD卡根目录/image_with_text.jpg", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //以图片形式获取View显示的内容（类似于截图）
    public static Bitmap loadBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    private int count = 0;
    //tvInImage的x方向和y方向移动量
    private float mDx, mDy;
    //移动
    private class SimpleGestureListenerImpl extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //向右移动时，distanceX为负；向左移动时，distanceX为正
            //向下移动时，distanceY为负；向上移动时，distanceY为正
            count++;
            mDx -= distanceX;
            mDy -= distanceY;
            //边界检查
            mDx = calPosition(imagePositionX - tvInImage.getX(), imagePositionX + imageWidth - (tvInImage.getX() + tvInImage.getWidth()), mDx);
            mDy = calPosition(imagePositionY - tvInImage.getY(), imagePositionY + imageHeight - (tvInImage.getY() + tvInImage.getHeight()), mDy);
            //控制刷新频率
            if (count % 5 == 0) {
                tvInImage.setX(tvInImage.getX() + mDx);
                tvInImage.setY(tvInImage.getY() + mDy);
            }
            return true;
        }
    }
    //计算正确的显示位置（不能超出边界）
    private float calPosition(float min, float max, float current) {
        if (current < min) {
            return min;
        }
        if (current > max) {
            return max;
        }
        return current;
    }
    //获取压缩后的bitmap
    private Bitmap getScaledBitmap(int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, opt);
        opt.inSampleSize = Utility.calculateInSampleSize(opt, 600, 800);
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resId, opt);
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
                        imageView.setImageBitmap(bitmap);
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
            imageView.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    public void show(View view){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_share, null);
        //初始化控件
        sharedongtai = (TextView) inflate.findViewById(R.id.sharedongtai);
        shareQQ = (TextView) inflate.findViewById(R.id.shareQQ);
        sharewechat = (TextView) inflate.findViewById(R.id.sharewechat);
        sharedongtai.setOnClickListener(this);

        shareQQ.setOnClickListener(this);
        sharewechat.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sharedongtai:
                Toast.makeText(this,"已分享到动态", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shareQQ:
                Toast.makeText(this,"已分享到QQ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sharewechat:
                Toast.makeText(this,"已分享到微信",Toast.LENGTH_SHORT).show();
                break;
        }
        dialog.dismiss();
    }

}
