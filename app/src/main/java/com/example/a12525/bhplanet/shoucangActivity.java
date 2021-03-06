package com.example.a12525.bhplanet;

import android.app.Activity;
import android.content.Context;

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

    private ImageView shouzuopin;
    private TextView shouname;

    private String opus_title;//作品id
    private String opus_type;//作品类型
    private String picture;//作品图片
    private ArrayList<String> opus_title_list=new ArrayList<>();
    private ArrayList<String> picture_list=new ArrayList<>();
    private ArrayList<String> type_list=new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initShou();                 //初始化水果数据
                shoucangActivity.ShouAdapter adapter = new shoucangActivity.ShouAdapter(shoucangActivity.this, R.layout.shoucang_item, shoucangList);


                ListView listview = (ListView) findViewById(R.id.list_view);
                listview.setAdapter(adapter);



            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        String id = Client.user_id;
        shouzuopin=(ImageView)findViewById(R.id.shouzuopin);
        shouname=(TextView)findViewById(R.id.shouname);
        getDatasync(id);
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initShou(){
        for(int i = 0; i < opus_title_list.size();  i++){
            Log.d("picture",picture_list.get(i));
            Log.d("picture",opus_title_list.get(i));
            shoucang apple=new shoucang(picture_list.get(i),opus_title_list.get(i));
            shoucangList.add(apple);

        }
    }
    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/collect?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("shou", "response.code()==" + response.code());
                    Log.d("shou", "response.message()==" + response.message());
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


            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                opus_title = object.optString("opus_title");
                opus_type=object.optString("opus_type");
                picture = object.optString("picture");

                opus_title_list.add(opus_title);
                type_list.add(opus_type);
                picture_list.add(picture);
            }


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
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.shouname = (TextView) view.findViewById(R.id.shouname);
                viewHolder.shouzuopin = (ImageView) view.findViewById(R.id.shouzuopin);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
//            ImageView shouzuopin=(ImageView)view.findViewById(R.id.shouzuopin);
//            TextView shouname=(TextView) view.findViewById(R.id.shouname);
//            shouzuopin.setImageResource(shoucang.getShouzuopin());
            Log.d("shou",shoucang.getShouzuopin() );
            Log.d("shouname",shoucang.getShouname());
            viewHolder.shouname.setText(shoucang.getShouname());
            Glide.with(getContext()).load(shoucang.getShouzuopin()).into(viewHolder.shouzuopin);

            return view;
        }
        class ViewHolder {
            ImageView shouzuopin;
            TextView shouname;
        }
    }
}
