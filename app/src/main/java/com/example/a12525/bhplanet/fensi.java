package com.example.a12525.bhplanet;

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
import com.example.a12525.bhplanet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class fensi extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    private List<fensi> fensiList = new ArrayList<>();
    private ImageView ftouxiang;
    private TextView fnicheng;
    private String fan_name;
    private String picture;
    private ArrayList<String> fan_name_list = new ArrayList<>();
    private ArrayList<String> picture_list = new ArrayList<>();
    String id =Client.user_id;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x123) {
                initFruits();                 //初始化水果数据
//                guanzhuActivity.guanAdapter adapter = new fensi.FruitAdapter(fensi.this, R.layout.fensi_item, fensiList);
                FruitAdapter adapter = new FruitAdapter(fensi.this, R.layout.fensi_item, fruitList);

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
        setContentView(R.layout.activity_fensi);
        ftouxiang=(ImageView)findViewById(R.id.fruit_image) ;
        fnicheng=(TextView)findViewById(R.id.fruit_name);
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDatasync(id);
    }

    private void initFruits() {
        for (int i = 0; i < fan_name_list.size(); i++) {
            Fruit apple = new Fruit(fan_name_list.get(i), picture_list.get(i));
            fruitList.add(apple);
        }
    }

    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/user/fan?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
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

            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                fan_name = object.optString("fan_name");
                picture = object.optString("picture");

                fan_name_list.add(fan_name);
                picture_list.add(picture);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class FruitAdapter extends ArrayAdapter<Fruit> {

        private int resourceId;

        public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Fruit fruit = getItem(position);           //获取当前项的实例
            View view;
            fensi.FruitAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new fensi.FruitAdapter.ViewHolder();
                viewHolder.fnicheng = (TextView) view.findViewById(R.id.fruit_name);
                viewHolder.ftouxiang = (ImageView) view.findViewById(R.id.fruit_image);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (fensi.FruitAdapter.ViewHolder) view.getTag();
            }
            viewHolder.fnicheng.setText(fruit.getName());
            Glide.with(getContext()).load(fruit.getImageId()).into(viewHolder.ftouxiang);
            return view;
        }

        class ViewHolder {
            TextView fnicheng;
            ImageView ftouxiang;
        }
    }
}
