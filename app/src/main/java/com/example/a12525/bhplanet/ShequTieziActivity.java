package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShequTieziActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private Button fatie, follow;
    private ImageButton fanhui;
    private RadioGroup radioGroup;
    private RadioButton latest_post, popular_post;
    private ViewPager bankuai_pager;
    private ShequTieziViewpagerAdapter mAdapter;
    private ImageView board_icon;
    private TextView board_name;
    private String b_name;
    private int b_icon;
    private int code;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static String community_id;
    private Map<String, String>map = new HashMap<>();
    private boolean is_follow1;
    private boolean is_follow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_bankuai_neirong);

        initView();

        follow = (Button)findViewById(R.id.follow);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_follow2) {
                    cancleFollow();
                    is_follow2 = false;
                } else {
                    setFollow();
                    is_follow2 = true;
                }
            }
        });

        fatie = (Button)findViewById(R.id.new_post_button);
        fatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShequTieziActivity.this, FatieActivity.class);
                startActivity(intent);
            }
        });

        fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShequTieziActivity.this, WodeBankuaiFragment.class);
                if (is_follow1 && !is_follow2){
                    setResult(1111,intent);
                    intent.putExtra("board_name", b_name);
                    Log.d("qqqqq", "7777777777777");
                }else if(!is_follow1 && is_follow2){
                    setResult(2222,intent);
                    intent.putExtra("board_name", b_name);
                    intent.putExtra("board_icon", b_icon);
                    Log.d("qqqqq", "8888888888888");
                }
                else {
                    setResult(3333,intent);
                    Log.d("qqqqq", "999999999999");
                }
                finish();
            }
        });

        setBoardName();
        for(int i = 0; i < WodeBankuaiFragment.community_name_list.size(); i++){
            if (b_name.equals(WodeBankuaiFragment.community_name_list.get(i))){
                is_follow1 = true;
                is_follow2 = true;
                follow.setText("已关注");
                break;
            }
            is_follow1 = false;
            is_follow2 = false;
        }
    }


    private void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.bankuai_tab_bar);
        latest_post = (RadioButton) findViewById(R.id.latest_post);
        popular_post = (RadioButton) findViewById(R.id.popular_post);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.bankuai_tab_bar);
        bankuai_pager = (ViewPager) findViewById(R.id.bankuai_pager);
        mAdapter = new ShequTieziViewpagerAdapter(getSupportFragmentManager());
        bankuai_pager.setAdapter(mAdapter);
        bankuai_pager.setCurrentItem(0);
        bankuai_pager.addOnPageChangeListener(this);
        bankuai_pager.setOffscreenPageLimit(2); //预加载
        latest_post.setChecked(true);
        latest_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
        popular_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
        board_icon = (ImageView)findViewById(R.id.board_icon);
        board_name = (TextView) findViewById(R.id.board_name);
        map.put("漫画总榜", "1_1");
        map.put("日漫", "1_2");
        map.put("国漫", "1_3");
        map.put("美漫", "1_4");
        map.put("海贼王", "1_5");
        map.put("斗图总榜", "2_1");
        map.put("超人回来了", "2_2");
        map.put("小刘鸭", "2_3");
        map.put("蘑菇头", "2_4");
        map.put("原创总榜", "3_1");
        map.put("影视总榜", "4_1");
        map.put("国产影视", "4_2");
        map.put("欧美影视", "4_3");
        map.put("亚洲影视", "4_4");
        map.put("漫威", "4_5");
        map.put("自制总榜", "5_1");
    }

    private void setBoardName(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("bankuai");
        int []index = intent.getIntArrayExtra(name);
        int i = index[0];
        int j = index[1];
        switch (name){
            case "wode":
                Glide.with(ShequTieziActivity.this).load(WodeBankuaiFragment.picture_list.get(i)).into(board_icon);
                b_name = WodeBankuaiFragment.community_name_list.get(i);
                break;
            case "zizhi":
                b_name = ZizhiFragment.name[0];
                b_icon = ZizhiFragment.img[0];
                break;
            case "yingshi":
                b_name = YingshiFragment.name[i][j];
                b_icon = YingshiFragment.img[i][j];
                break;
            case "yuanchuang":
                b_name = YuanchuangFragment.name[0];
                b_icon = YuanchuangFragment.img[0];
                break;
            case "doutu":
                b_name = DoutuFragment.name[i][j];
                b_icon = DoutuFragment.img[i][j];
                break;
            case "manhua":
                b_name = ManhuaFragment.name[i][j];
                b_icon = ManhuaFragment.img[i][j];
                break;
        }
        board_name.setText(b_name);
        board_icon.setImageResource(b_icon);
        community_id = map.get(b_name);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.latest_post:
                bankuai_pager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.popular_post:
                bankuai_pager.setCurrentItem(PAGE_TWO);
                break;
        }
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (bankuai_pager.getCurrentItem()) {
                case PAGE_ONE:
                    latest_post.setChecked(true);
                    latest_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    popular_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
                    break;
                case PAGE_TWO:
                    popular_post.setChecked(true);
                    popular_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    latest_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
                    break;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                follow.setText("已关注");
            }else if(msg.what ==0x456){
                follow.setText("+关注");
            }
        }
    };

    private void setFollow(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/community/followcommunity";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("user_id", Client.user_id)
                        .add("community_id", community_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Call call = Client.client.newCall(request);
                call.enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        code = response.code();
                        Log.d("shequ","response.code()==" + code);
                        Log.d("shequ","response.message()=="+response.message());
                        String resData = response.body().string();
                        Log.d("shequ","res=="+resData);

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);

        }).start();
    }


    private void cancleFollow(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/community/cancelfollow";
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("user_id", Client.user_id)
                        .add("community_id", community_id);

                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .post(formBody.build())
                        .build();//创建Request 对象

                Call call = Client.client.newCall(request);
                call.enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        code = response.code();
                        Log.d("shequ","response.code()==" + code);
                        Log.d("shequ","response.message()=="+response.message());
                        String resData = response.body().string();
                        Log.d("shequ","res=="+resData);

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x456);
        }).start();
    }

}
