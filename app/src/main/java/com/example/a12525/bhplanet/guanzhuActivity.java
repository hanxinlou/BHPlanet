package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
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
import java.util.Objects;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class guanzhuActivity extends AppCompatActivity {
    private ArrayList<guanzhu> guanzhuList = new ArrayList<>();
    private ImageView gtouxiang;
    private TextView gnicheng;
    private String author_name;
    private String picture;
    private ArrayList<String> author_name_list=new ArrayList<>();
    private ArrayList<String> picture_list=new ArrayList<>();

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initguanzhu();                 //初始化水果数据
                guanAdapter adapter = new guanAdapter(guanzhuActivity.this, R.layout.guanzhu_item, guanzhuList);
                ListView listview = (ListView) findViewById(R.id.list_view);
                listview.setAdapter(adapter);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanzhu);
        ImageButton fanhui = (ImageButton)findViewById(R.id.fanhui);
        gtouxiang = (ImageView)findViewById(R.id.gtou);
        gnicheng = (TextView)findViewById(R.id.gname);
        getDatasync();

        fanhui.setOnClickListener(v -> finish());
    }

    private void initguanzhu() {
        for (int i = 0; i < author_name_list.size(); i++) {
                guanzhu apple = new guanzhu(picture_list.get(i), author_name_list.get(i));
                guanzhuList.add(apple);
        }
    }

    public void getDatasync() {
        new Thread(() -> {
            try {
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/user/concern?user_id=" + Client.user_id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = Client.client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("guanzhu", "response.code()==" + response.code());
                    Log.d("guanzhu", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("guanzhu", "res==" + resData);
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
                author_name = object.optString("author_name");
                picture = object.optString("picture");

                author_name_list.add(author_name);
                picture_list.add(picture);
            }


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
            guanzhu g = getItem(position);           //获取当前项的实例
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.gnicheng = (TextView) view.findViewById(R.id.gname);
                viewHolder.gtouxiang = (ImageView) view.findViewById(R.id.gtou);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.gnicheng.setText(g.getGname());
            Glide.with(getContext()).load(g.getGtou()).into(viewHolder.gtouxiang);
            return view;
        }

        class ViewHolder {
            TextView gnicheng;
            ImageView gtouxiang;
        }
    }
}