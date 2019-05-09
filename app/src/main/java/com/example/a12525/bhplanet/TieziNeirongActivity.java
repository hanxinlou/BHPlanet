package com.example.a12525.bhplanet;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TieziNeirongActivity extends Activity {
    private UnScrollListView listView1;
    private UnScrollListView listView2;
    private TextView content_text, post_title;
    private List<pinglun> pinglunList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_tiezi_neirong);
        initPing();
        PingAdapter adapter=new PingAdapter(TieziNeirongActivity.this,R.layout.pinglun_item, pinglunList);
        content_text = (TextView)findViewById(R.id.content_text);
        post_title = (TextView)findViewById(R.id.post_title);
        listView1 = (UnScrollListView)findViewById(R.id.light_comment_list);
        listView2 = (UnScrollListView)findViewById(R.id.all_comment_list);
        ImageView content_img = (ImageView)findViewById(R.id.content_img);
        ImageView user_img = (ImageView)findViewById(R.id.user_img);

        content_img.setImageResource(R.drawable.tiezi_img);
        user_img.setImageResource(R.drawable.img2);
        content_text.setText("你俩是每集必拉手手是吗？\n" +
                "\n" +
                "单身狗鸡腿腿我有没有事你俩心里没点数吗？\n" +
                "\n" +
                "王权霸业你特娘的到底啥时候摘面具啊喂！！！\n" +
                "\n" +
                "—— 《狐妖小红娘》竹业篇\n");
        post_title.setText("《狐妖小红娘》竹业篇");
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);
        listView1.setFocusable(false);
        listView2.setFocusable(false);
    }

    private void initPing(){
            pinglun apple=new pinglun(R.drawable.head,"不要吃一点肥肉的圣诞老人","04/02","呀，鸡腿也看番呀", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(apple);
            pinglun cao=new pinglun(R.drawable.head1,"小狐狸","04/01","所以东方月初他爹是谁啊", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(cao);
            pinglun hehe=new pinglun(R.drawable.head2,"清和不浊","03/31","片尾曲很好听啊", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(hehe);
            pinglun nima=new pinglun(R.drawable.head3,"阿豹是海豹的豹","03/31","马上就开始虐了", R.drawable.undianliang,"点亮","回复","查看回复");
            pinglunList.add(nima);

    }
}