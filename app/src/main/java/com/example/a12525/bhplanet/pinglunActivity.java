package com.example.a12525.bhplanet;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import android.widget.AdapterView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public class pinglunActivity extends AppCompatActivity {
    private List<pinglun> pinglunList=new ArrayList<>();
    private boolean flag=false;
    private boolean flag1=false;
    private ImageView dianzan;
    private ImageView shoucang;
    private ImageButton fanhui;
    private ImageButton zhuanfa;
    private ImageView zuotou;
    private Button pinglun;
    private LinearLayout relativeLayout;
    private TextView tv_confirm;
    private LinearLayout mLayout;
    private EditText mEdit;
    private String opus_id,opus_title,opus_content,opus_picture,user_id,user_name,user_picture;
    private Number type,zan_num,comment_num,transmit_num,collect_num,zan;

    private ArrayList<String> opus_picture_list=new ArrayList<>();
    private ArrayList<String> user_picture_list=new ArrayList<>();
    private ArrayList<String> opus_content_list=new ArrayList<>();
    private ArrayList<String> user_name_list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);
        Intent intent = getIntent();
        String home_img_id = intent.getStringExtra("home_img_id");

        mLayout = (LinearLayout) findViewById(R.id.layout);
        mEdit = (EditText) findViewById(R.id.et_discuss);

        zuotou=(ImageView)findViewById(R.id.user_picture);
        zuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(pinglunActivity.this,tdongtaiActivity.class);
                startActivity(intent);
            }
        });
        fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        zhuanfa = (ImageButton)findViewById(R.id.zhuanfa);
        zhuanfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(pinglunActivity.this,ZhuanActivity.class);
                startActivity(intent);
            }
        });
        dianzan = (ImageView) findViewById(R.id.dianzan);
        dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag1){
                    dianzan.setImageDrawable(getResources().getDrawable(R.drawable.zan));
                    flag1=true;
                }
                else
                {
                    dianzan.setImageDrawable(getResources().getDrawable(R.drawable.unzan));
                    flag1=false;
                }
            }
        });

        shoucang = (ImageView) findViewById(R.id.shoucang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                {
                    shoucang.setImageDrawable(getResources().getDrawable(R.drawable.shoucang));
                    flag=true;
                }
                else
                {
                    shoucang.setImageDrawable(getResources().getDrawable(R.drawable.unshoucang));
                    flag=false;

                }

            }
        });

        pinglun=(Button)findViewById(R.id.pinglun);
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowKeyboard();

            }
        });
        tv_confirm=(TextView)findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                Toast.makeText(pinglunActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayout=(LinearLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        initPing();                 //初始化水果数据
        PingAdapter adapter=new PingAdapter(pinglunActivity.this,R.layout.pinglun_item,pinglunList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pinglun pinglun=pinglunList.get(position);
                Intent intent1=new Intent(pinglunActivity.this,chakanActivity.class);
                startActivity(intent1);
            }
        });
        adapter.setOnItemDeleteClickListener(new PingAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick() {
                //mList.remove(i);
                //adapter.notifyDataSetChanged();
                ShowKeyboard();
            }
        });

    }

    //显示布局与键盘
    private void ShowKeyboard(){
        mLayout.setVisibility(View.VISIBLE);//显示布局
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
    //隐藏键盘与布局
    private void hideKeyboard(){
        mLayout.setVisibility(View.GONE);//隐藏布局
        mEdit.setText("");//清空输入
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 捕获返回键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //隐藏键盘与布局
        hideKeyboard();
        return true;
    }
    private void initPing(){
        for(int i=0;i<3;i++){
//            pinglun apple=new pinglun(R.drawable.head,"不要吃一点肥肉的圣诞老人","04/02","你看 ，你们从风起稻香，终于走到了世外蓬莱，依旧没情缘\n", R.drawable.undianliang,"点亮","回复","查看回复");
//            pinglunList.add(apple);
//            pinglun cao=new pinglun(R.drawable.head1,"小狐狸","04/01","楼上你是魔鬼吗？？？！！", R.drawable.undianliang,"点亮","回复","查看回复");
//            pinglunList.add(cao);
//            pinglun hehe=new pinglun(R.drawable.head2,"清和不浊","03/31","片头曲很好听啊", R.drawable.undianliang,"点亮","回复","查看回复");
//            pinglunList.add(hehe);
//            pinglun nima=new pinglun(R.drawable.head3,"阿豹是海豹的豹","03/31","我到现在才明白关门弟子→GM弟子啊\n", R.drawable.undianliang,"点亮","回复","查看回复");
//            pinglunList.add(nima);
        }
    }






}

