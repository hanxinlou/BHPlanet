package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

public class ZuireFragment extends Fragment {
    private List<Posts> postsList = new ArrayList<>();
    private ArrayList<String> post_id_list = new ArrayList<>();
    private ArrayList<String> post_title_list = new ArrayList<>();
    private ArrayList<String> picture_list = new ArrayList<>();
    private ArrayList<String> community_name_list = new ArrayList<>();
    private ArrayList<String> user_name_list = new ArrayList<>();
    private ArrayList<String> user_id_list = new ArrayList<>();
    private ArrayList<Integer> zan_num_list = new ArrayList<>();
    private ArrayList<Integer> comment_num_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_tiezi_liebiao, container, false);

        getDatasync();
        return view;
    }

    private void initPosts(){
        for (int i = 0; i < post_title_list.size(); i++){
            Posts post = new Posts(community_name_list.get(i), post_title_list.get(i), user_name_list.get(i),
                    zan_num_list.get(i), comment_num_list.get(i), picture_list.get(i));
            postsList.add(post);
        }
    }

    public void getDatasync(){
        new Thread(() -> {
            try {
                String community_id = ShequTieziActivity.community_id;
                String url = "http://129.211.5.66:8080/community/posts?community_id=" + community_id + "&status=1&page=1";
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = Client.client.newCall(request).execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("ShequTuijian","response.code()=="+response.code());
                    Log.d("ShequTuijian","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("ShequTuijian","res=="+resData);
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
            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray Info = content.getJSONArray("info");

            for(int i = 0; i < Info.length(); i++) {
                JSONObject object = Info.getJSONObject(i);
                String post_id = object.optString("post_id");
                String post_title = object.optString("post_title");
                String community_name = object.optString("community_name");
                String user_name = object.optString("user_name");
                String user_id = object.optString("user_id");
                String picture = object.optString("community_name");
                int zan_num = object.optInt("zan_num");
                int comment_num = object.optInt("comment_num");

                post_id_list.add(post_id);
                post_title_list.add(post_title);
                community_name_list.add(community_name);
                user_name_list.add(user_name);
                user_id_list.add(user_id);
                picture_list.add(picture);
                zan_num_list.add(zan_num);
                comment_num_list.add(comment_num);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            if(msg.what==0x123) {
                initPosts();
                TieziAdapter adapter = new TieziAdapter(getActivity(), R.layout.shequ_tiezi, postsList);
                ListView listView = (ListView)getView().findViewById(R.id.posts_list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Posts posts = postsList.get(position);
                        Toast.makeText(getActivity(), posts.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), TieziNeirongActivity.class);
                        intent.putExtra("post_id", post_id_list.get(position));
                        startActivity(intent);
                    }
                });
            }
        }
    };
}
