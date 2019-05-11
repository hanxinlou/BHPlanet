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

public class shoucangActivity extends AppCompatActivity {
    private List<shoucang> shoucangList=new ArrayList<>();
//    private List<guanzhu> guanzhuList = new ArrayList<>();
    private ImageView shoucang_picture;
    private TextView shouname;

    private String opus_id;//作品id
    private String opus_type;//作品类型
    private String picture;//作品图片
    private ArrayList<String> shou_name_list=new ArrayList<>();
    private ArrayList<String> picture_list=new ArrayList<>();
    private ArrayList<String> type_list=new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initShou();                 //初始化水果数据
                shoucangActivity.ShouAdapter adapter = new shoucangActivity.ShouAdapter(shoucangActivity.this, R.layout.guanzhu_item, shoucangList);

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
        setContentView(R.layout.activity_shoucang);
        String id = Client.user_id;
        getDatasync(id);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initShou();                 //初始化水果数据
        ShouAdapter adapter=new ShouAdapter(shoucangActivity.this,R.layout.shoucang_item,shoucangList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }
    private void initShou(){
        for(int i=0;i<shou_name_list.size();i++){
            shoucang apple=new shoucang(R.drawable.song,shou_name_list.get(i));
            shoucangList.add(apple);
//            shoucang cao=new shoucang(R.drawable.song1,"收藏夹1");
//            shoucangList.add(cao);
//            shoucang hehe=new shoucang(R.drawable.xiongmao,"收藏夹2");
//            shoucangList.add(hehe);
//            shoucang nima=new shoucang(R.drawable.head3,"收藏夹3");
//            shoucangList.add(nima);
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
                opus_id = object.optString("opus_id");
                opus_type=object.optString("opus_type");
                picture = object.optString("picture");

                shou_name_list.add(opus_id);
                type_list.add(opus_type);
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
    public class ShouAdapter extends ArrayAdapter<shoucang> {

        private int resourceId;
        public ShouAdapter(Context context, int textViewResourceId, List<shoucang> objects){
            super(context,textViewResourceId,objects);
            resourceId=textViewResourceId;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            shoucang shoucang=getItem(position);           //获取当前项的实例
            View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            ImageView shouzuopin=(ImageView)view.findViewById(R.id.shouzuopin);
            TextView shouname=(TextView) view.findViewById(R.id.shouname);
            shouzuopin.setImageResource(shoucang.getShouzuopin());
            shouname.setText(shoucang.getShouname());
            return view;
        }
    }


}
