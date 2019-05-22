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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a12525.bhplanet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class tongzhiActivity extends AppCompatActivity {
    private List<tongzhi> tongzhiList=new ArrayList<>();

    private String type_id;
    private Number type;
    private String user_id;
    private String create_time;
    private String to_userid;
    private String user_name;
    private ArrayList<String> type_id_list=new ArrayList<>();
    private ArrayList<Number> type_list=new ArrayList<>();
    private ArrayList<String> user_id_list=new ArrayList<>();
    private ArrayList<String> create_time_list=new ArrayList<>();
    private ArrayList<String> to_userid_list=new ArrayList<>();
    private ArrayList<String> user_name_list=new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initTong();                 //初始化水果数据
                tongzhiActivity.TongAdapter adapter = new tongzhiActivity.TongAdapter(tongzhiActivity.this, R.layout.lishi_item, tongzhiList);

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
        setContentView(R.layout.activity_tongzhi);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTong();                 //初始化水果数据
        TongAdapter adapter=new TongAdapter(tongzhiActivity.this,R.layout.tongzhi_item,tongzhiList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initTong(){
        for(int i=0;i<user_id_list.size();i++){
            tongzhi apple=new tongzhi(R.drawable.song,user_id_list.get(i),create_time_list.get(i),create_time_list.get(i),"赞了你");
            tongzhiList.add(apple);

        }
    }
    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/user/collect?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
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
                type_id=object.optString("type_id");
                type=object.optDouble("type");
                user_id = object.optString("user_id");
                create_time=object.optString("create_time");
                to_userid=object.optString("to_user_id");
                user_name=object.optString("user_name");
                type_id_list.add(type_id);
                type_list.add(type);
                user_id_list.add(user_id);
                create_time_list.add(create_time);
                to_userid_list.add(to_userid);
                user_name_list.add(user_name);
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


    public class TongAdapter extends ArrayAdapter<tongzhi> {

        private int resourceId;
        public TongAdapter(Context context, int textViewResourceId, List<tongzhi> objects){
            super(context,textViewResourceId,objects);
            resourceId=textViewResourceId;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            tongzhi tongzhi=getItem(position);           //获取当前项的实例
            View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            ImageView tongtou=(ImageView)view.findViewById(R.id.tongtou);
            TextView tongid=(TextView) view.findViewById(R.id.tongid);
            TextView tongday=(TextView) view.findViewById(R.id.tongday);
            TextView tongtime=(TextView) view.findViewById(R.id.tongtime);
            TextView tongneirong=(TextView) view.findViewById(R.id.tongneirong);
            tongtou.setImageResource(tongzhi.getTongtou());
            tongid.setText(tongzhi.getTongid());
            tongday.setText(tongzhi.getTongday());
            tongtime.setText(tongzhi.getTongtime());
            tongneirong.setText(tongzhi.getTongneirong());
            return view;
        }
    }
}

