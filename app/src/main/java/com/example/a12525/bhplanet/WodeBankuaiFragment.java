package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class WodeBankuaiFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String, Object>> dataList = new ArrayList<>();
    private int img1[] = { R.drawable.com_board1_img3, R.drawable.com_board1_img2, R.drawable.com_board1_img1, R.drawable.com_board4_img5 };

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    private ArrayList<String> community_name_list, picture_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_wode, container, false);
        getDatasync("51519");
        gridView = (GridView)view.findViewById(R.id.board_item1);
        return view;
    }

    private void getData() {
        for (int i = 0; i < community_name_list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", img1[i]);
            map.put("text", community_name_list.get(i));
            dataList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.bankuai_tupian, from, to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        startActivity(intent);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                getData();
            }
        }
    };

    public void getDatasync(String user_id){
        new Thread(() -> {
            try {
                String url = "https://nei.netease.com/api/apimock/53f5f289ef8648edd032ecb28f5ac8da/community/mypart?user_id=" + user_id;
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = Client.client.newCall(request).execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("shequ","response.code()=="+response.code());
                    Log.d("shequ","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("shequ","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }

    private void parseData(String resData){
        try{
            JSONObject jsonObject = new JSONObject(resData);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray info = data.getJSONArray("info");


            community_name_list = new ArrayList<>();
            picture_list = new ArrayList<>();

            for(int i = 0; i < info.length(); i++) {
                JSONObject object = info.getJSONObject(i);
                String community_name = object.optString("community_name");
                String picture = object.optString("picture");

                community_name_list.add(community_name);
                picture_list.add(picture);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
