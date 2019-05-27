package com.example.a12525.bhplanet;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends Fragment {
    private List<dongtai> dongtaiList = new ArrayList<>();
    private RecyclerView recyclerView;

    private String dynamic_id;
    private String user_id;
    private String user_name;
    private String user_picture;
    private String content;
    private String link_id;
    private String author_id;
    private String author_name;
    private String author_picture;
    private String opus_content;
    private String opus_picture;
    private Number zan_num;
    private Number comment_num;
    private Number transmit_num;
    private String create_time;
    private Number zan;

    private ArrayList<String> dynamic_id_list = new ArrayList<>();
    private ArrayList<String> user_id_list = new ArrayList<>();
    private ArrayList<String> user_name_list = new ArrayList<>();
    private ArrayList<String> user_picture_list = new ArrayList<>();
    private ArrayList<String> content_list = new ArrayList<>();
    private ArrayList<String> link_id_list = new ArrayList<>();
    private ArrayList<String> author_id_list = new ArrayList<>();
    private ArrayList<String> author_name_list = new ArrayList<>();
    private ArrayList<String> author_picture_list = new ArrayList<>();
    private ArrayList<String> opus_content_list = new ArrayList<>();
    private ArrayList<String> opus_picture_list = new ArrayList<>();
    private ArrayList<Number> zan_num_list = new ArrayList<>();
    private ArrayList<Number> comment_num_list = new ArrayList<>();
    private ArrayList<Number> transmit_num_list = new ArrayList<>();
    private ArrayList<String> create_time_list = new ArrayList<>();
    private ArrayList<Number> zan_list = new ArrayList<>();

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dongtai, container, false);
        String id = Client.user_id;
        getDatasync(id);
        //initDong();                 //初始化水果数据
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;

    }

    private void initDong() {
        for (int i = 0; i < user_id_list.size(); i++) {
            dongtai apple = new dongtai(user_picture_list.get(i), user_name_list.get(i), create_time_list.get(i),
                    content_list.get(i), author_picture_list.get(i), author_name_list.get(i), opus_picture_list.get(i),
                    opus_content_list.get(i), R.drawable.zhuan, transmit_num_list.get(i), R.drawable.ping, comment_num_list.get(i),
                    R.drawable.unzan, zan_num_list.get(i),link_id_list.get(i));
            dongtaiList.add(apple);
            recyclerView = (RecyclerView)getActivity().findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            ViewCompat.setNestedScrollingEnabled(recyclerView, false);
            DongAdapter adapter = new DongAdapter(dongtaiList, this);
            recyclerView.setAdapter(adapter);
        }
    }

    public void getDatasync(String id) {
        new Thread(() -> {
            try {
                OkHttpClient client = Client.client;//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url("http://129.211.5.66:8080/ThePlanet/dynamic/list?user_id=" + id + "&currpage=1")//请求接口。如果需要传参拼接到接 口后面。
                        .build();//创建Request 对象
                Call call = client.newCall(request);
                Response response = call.execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("dongtai", "response.code()==" + response.code());
                    Log.d("dongtai", "response.message()==" + response.message());
                    String resData = response.body().string();
                    Log.d("dongtai", "res==" + resData);
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
            dynamic_id_list.clear();
            user_id_list.clear();
            user_name_list.clear();
            user_picture_list.clear();
            content_list.clear();
            link_id_list.clear();
            author_id_list.clear();
            author_name_list.clear();
            author_picture_list.clear();
            opus_picture_list.clear();
            opus_content_list.clear();
            zan_num_list.clear();
            comment_num_list.clear();
            transmit_num_list.clear();
            create_time_list.clear();
            zan_list.clear();

            for (int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                dynamic_id = object.optString("dynamic_id");
                user_id = object.optString("user_id");
                user_name = object.optString("user_name");
                user_picture = object.optString("user_picture");
                content = object.optString("content");
                link_id = object.optString("link_id");
                author_id = object.optString("author_id");
                author_name = object.optString("author_name");
                author_picture = object.optString("author_picture");
                opus_content = object.optString("opus_content");
                opus_picture = object.optString("opus_picture");
                zan_num = object.optDouble("zan_num");
                comment_num = object.optDouble("comment_num");
                transmit_num = object.optDouble("transmit_num");
                create_time = object.optString("create_time");
                zan = object.optDouble("zan");

                dynamic_id_list.add(dynamic_id);
                user_id_list.add(user_id);
                user_name_list.add(user_name);
                user_picture_list.add(user_picture);
                content_list.add(content);
                link_id_list.add(link_id);
                author_id_list.add(author_id);
                author_name_list.add(author_name);
                author_picture_list.add(author_picture);
                opus_picture_list.add(opus_picture);
                opus_content_list.add(opus_content);
                zan_num_list.add(zan_num);
                comment_num_list.add(comment_num);
                transmit_num_list.add(transmit_num);
                create_time_list.add(create_time);
                zan_list.add(zan);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class dongtai {
        private String tou1;
        private String dongname1;
        private String dongdate1;
        private String dongneirong1;
        private String zuozhetou;
        private String zuozheid;
        private String dongimage1;
        private String zuopinming;
        private int zhuan1;
        private Number nzhuan1;
        private int ping1;
        private Number nping1;
        private int zan1;
        private Number nzan1;
        private String link_id;

        public dongtai(String tou1, String dongname1, String dongdate1, String dongneirong1, String zuozhetou, String zuozheid, String dongimage1, String zuopinming, int zhuan1, Number nzhuan1, int ping1, Number nping1, int zan1, Number nzan1,String link_id) {
            this.tou1 = tou1;
            this.dongname1 = dongname1;
            this.dongdate1 = dongdate1;
            this.dongneirong1 = dongneirong1;
            this.zuozhetou = zuozhetou;
            this.zuozheid = zuozheid;
            this.dongimage1 = dongimage1;
            this.zuopinming = zuopinming;
            this.zhuan1 = zhuan1;
            this.nzhuan1 = nzhuan1;
            this.ping1 = ping1;
            this.nping1 = nping1;
            this.zan1 = zan1;
            this.nzan1 = nzan1;
            this.link_id=link_id;
        }

        public String getTou1() {
            return tou1;
        }

        public String getDongname1() {
            return dongname1;
        }

        public String getDongdate1() {
            return dongdate1;
        }

        public String getDongneirong1() {
            return dongneirong1;
        }

        public String getZuozhetou() {
            return zuozhetou;
        }

        public String getZuozheid() {
            return zuozheid;
        }

        public String getDongimage1() {
            return dongimage1;
        }

        public String getZuopinming() {
            return zuopinming;
        }

        public int getZhuan1() {
            return zhuan1;
        }

        public int getPing1() {
            return ping1;
        }

        public int getZan1() {
            return zan1;
        }

        public Number getNzhuan1() {
            return nzhuan1;
        }

        public Number getNping1() {
            return nping1;
        }

        public Number getNzan1() {
            return nzan1;
        }

        public String getLink_id() {
            return link_id;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                initDong();
            }

        }
    };
}