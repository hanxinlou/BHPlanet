package com.example.a12525.bhplanet;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12525.bhplanet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class guanzhuActivity extends AppCompatActivity {
    private List<guanzhu> guanzhuList = new ArrayList<>();
    private CircleImageView gtouxiang;
    private TextView gnicheng;
    private String author_name;
    private String picture;
    private ArrayList<String> author_name_list=new ArrayList<>();
    private ArrayList<String> picture_list=new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initguanzhu();                 //初始化水果数据
                guanAdapter adapter = new guanAdapter(guanzhuActivity.this, R.layout.guanzhu_item, guanzhuList);

                //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
                ListView listview = (ListView) findViewById(R.id.list_view);
                listview.setAdapter(adapter);

//                nickname.setText(mydata.get("user_name"));
//                uid.setText(mydata.get("user_id"));
//                sexx.setText(mydata.get("birthday"));
//                birthh.setText(mydata.get("sex"));
//                qianmingg.setText(mydata.get("introduce"));
                //picture.setImageResource(Integer.parseInt(mydata.get("picture")));

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanzhu);
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        Intent intent = getIntent();

        String id = Client.user_id;
        getDatasync(id);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initguanzhu() {
        for (int i = 0; i < author_name_list.size(); i++) {
                guanzhu apple = new guanzhu(R.drawable.head5, author_name_list.get(i));
                guanzhuList.add(apple);
//            guanzhu apple1 = new guanzhu(R.drawable.head12, "小狐狸");
//            guanzhuList.add(apple1);
//            guanzhu apple2 = new guanzhu(R.drawable.head11, "鹿小草");
//            guanzhuList.add(apple2);
//            guanzhu apple3 = new guanzhu(R.drawable.head3, "孙笑川");
//            guanzhuList.add(apple3);
//            guanzhu apple4 = new guanzhu(R.drawable.head6, "团子");
//            guanzhuList.add(apple4);
//            guanzhu apple5 = new guanzhu(R.drawable.head8, "团子");
//            guanzhuList.add(apple5);
//            guanzhu apple6 = new guanzhu(R.drawable.head4, "团子");
//            guanzhuList.add(apple6);
//            guanzhu apple7 = new guanzhu(R.drawable.head10, "团子");
//            guanzhuList.add(apple7);
//            guanzhu apple8 = new guanzhu(R.drawable.head10, "团子");
//            guanzhuList.add(apple8);
//            guanzhu apple9 = new guanzhu(R.drawable.head9, "团子");
//            guanzhuList.add(apple9);
//            guanzhu apple10 = new guanzhu(R.drawable.head8, "团子");
//            guanzhuList.add(apple10);
        }
    }

    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/user/concern?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("ndxq", "response.code()==" + response.code());
                    Log.d("ndxq", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("ndxq", "res==" + resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);  //  处理对象的函数
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }


    private void parseData(String resData) {
        try {
            JSONObject jsonObject = new JSONObject(resData);
            JSONObject data = jsonObject.getJSONObject("content");

            JSONArray info = data.getJSONArray("info");

//            author_name_list = new ArrayList<String>();
//            picture_list = new ArrayList<String>();
            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                author_name = object.optString("author_name");
                picture = object.optString("picture");

                author_name_list.add(author_name);
                picture_list.add(picture);
            }

//            for(int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                author_name = object.optString("author_name");
////                String picture = object.optString("picture");
//                Log.d("ndxq", "reply_id==" + reply_id);
            // getres = "compose_id==" + compose_id;

            //mydata.clear();
//            mydata.put("user_name", dataObject.optString("user_name"));
//            mydata.put("author_id",dataObject.optString("author_id"));
            //mydata.put("author_name",jsonObject2.optString("author_name"));
//            mydata.put("author_name",jsonObject2.optString("author_name"));
//            mydata.put("picture", jsonObject2.optString("picture"));
//            mydata.put("birthday", dataObject.optString("birthday"));
//            mydata.put("sex", dataObject.optString("sex"));
//            mydata.put("introduce", dataObject.optString("introduce"));
//            mydata.put("picture", dataObject.optString("picture"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class guanAdapter extends ArrayAdapter<guanzhu> {

        private int resourceId;

        public guanAdapter(Context context, int textViewResourceId, List<guanzhu> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            guanzhu guanzhu = getItem(position);           //获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            CircleImageView Gtou = (CircleImageView) view.findViewById(R.id.gtou);
            TextView Gname = (TextView) view.findViewById(R.id.gname);
            // Switch Guanfou =(Switch)view.findViewById(R.id.guanfou);

//            Gtou.setImageResource(guanzhu.getGtou());
                Glide.with(guanzhuActivity.this).load(guanzhu.getGtou()).into(Gtou);
                Gname.setText(guanzhu.getGname());
            return view;
        }
    }
}