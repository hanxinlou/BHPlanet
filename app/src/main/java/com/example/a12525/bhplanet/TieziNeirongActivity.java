package com.example.a12525.bhplanet;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TieziNeirongActivity extends Activity {
    private ListView listView1;
    private ListView listView2;
    private List<pinglun> pinglunList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_tiezi_neirong);
        initPing();
        PingAdapter adapter=new PingAdapter(TieziNeirongActivity.this,R.layout.pinglun_item, pinglunList);
        listView1 = (ListView)findViewById(R.id.light_comment_list);
        listView2 = (ListView)findViewById(R.id.all_comment_list);
        ImageView content_img = (ImageView)findViewById(R.id.content_img);
        ImageView user_img = (ImageView)findViewById(R.id.user_img);
        content_img.setImageResource(R.drawable.img1);
        user_img.setImageResource(R.drawable.img2);
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
    }

    private void initPing(){
            pinglun apple=new pinglun(R.drawable.head,"你麻痹","1小时前","太好笑了", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(apple);
            pinglun cao=new pinglun(R.drawable.head1,"小狐狸","2小时前","咦我的头呢", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(cao);
            pinglun hehe=new pinglun(R.drawable.head2,"小蜜蜂","2小时前","鬼才", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(hehe);
            pinglun nima=new pinglun(R.drawable.head3,"嗡嗡嗡","2小时前","牛逼", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(nima);

    }
}