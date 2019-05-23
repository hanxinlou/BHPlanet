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
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class lishiActivity extends AppCompatActivity {
    private List<lishi> lishiList=new ArrayList<>();
//    private List<guanzhu> guanzhuList = new ArrayList<>();
//    private CircleImageView gtouxiang;
//    private TextView gnicheng;
    private String opus_id;
    private Number opus_type;
    private String author_name;
    private String create_time;
    private String opus_title;
    private String picture;
    private ArrayList<String> opus_id_list=new ArrayList<>();
    private ArrayList<Number> opus_type_list=new ArrayList<>();
    private ArrayList<String> author_name_list=new ArrayList<>();
    private ArrayList<String> create_time_list=new ArrayList<>();
    private ArrayList<String> opus_title_list=new ArrayList<>();
    private ArrayList<String> picture_list=new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initLi();                 //初始化水果数据
                lishiActivity.LiAdapter adapter = new lishiActivity.LiAdapter(lishiActivity.this, R.layout.lishi_item, lishiList);

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
        setContentView(R.layout.activity_lishi);
        String id =Client.user_id;
        getDatasync(id);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
            });

        initLi();                 //初始化水果数据
        LiAdapter adapter=new LiAdapter(lishiActivity.this,R.layout.lishi_item,lishiList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initLi(){
        for(int i=0;i<author_name_list.size();i++){
            lishi apple=new lishi(picture_list.get(i),opus_title_list.get(i),author_name_list.get(i),create_time_list.get(i));
            lishiList.add(apple);
//            lishi cao=new lishi(R.drawable.song1,"咦我的头呢","小狐狸","2小时前");
//            lishiList.add(cao);
//            lishi hehe=new lishi(R.drawable.lu,"鬼才","小蜜蜂","2小时前");
//            lishiList.add(hehe);
//            lishi nima=new lishi(R.drawable.xiongmao,"牛逼","嗡嗡嗡","2小时前");
//            lishiList.add(nima);
        }
    }
    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/history?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
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
                opus_id=object.optString("opus_id");
                opus_type=object.optDouble("opus_type");
                author_name = object.optString("author_name");
                create_time=object.optString("create_time");
                opus_title=object.optString("opus_title");
                picture = object.optString("picture");
                opus_id_list.add(opus_id);
                opus_type_list.add(opus_type);
                author_name_list.add(author_name);
                create_time_list.add(create_time);
                opus_title_list.add(opus_title);
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
    public class LiAdapter extends ArrayAdapter<lishi> {

        private int resourceId;
        public LiAdapter(Context context, int textViewResourceId, List<lishi> objects){
            super(context,textViewResourceId,objects);
            resourceId=textViewResourceId;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            lishi lishi=getItem(position);           //获取当前项的实例
            //Liuzuopin.setImageResource(lishi.getLiuzuopin());
            View view;
            lishiActivity.LiAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new lishiActivity.LiAdapter.ViewHolder();
                viewHolder.Liuzuopin=(ImageView)view.findViewById(R.id.liuzuopin);
               viewHolder.Liuming=(TextView) view.findViewById(R.id.liuming);
                viewHolder.Liuid=(TextView) view.findViewById(R.id.liuid);
                viewHolder.Liutime=(TextView) view.findViewById(R.id.liutime);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (lishiActivity.LiAdapter.ViewHolder) view.getTag();
            }
//            viewHolder.gnicheng.setText(lishi.());
            Glide.with(getContext()).load(lishi.getLiuzuopin()).into(viewHolder.Liuzuopin);
            viewHolder.Liuming.setText(lishi.getLiuming());//作品名称
            viewHolder.Liuid.setText(lishi.getLiuid());//作者ID
            viewHolder.Liutime.setText(lishi.getLiutime());//观看时间

            return view;

        }
        class ViewHolder {
            ImageView Liuzuopin;
            TextView Liuming;
            TextView Liuid;
            TextView Liutime;
        }

    }
}
