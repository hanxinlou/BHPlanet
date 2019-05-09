package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ZuireFragment extends Fragment {
    private List<Posts> postsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_tiezi_liebiao, container, false);
        initPosts();
        TieziAdapter adapter = new TieziAdapter(getActivity(), R.layout.shequ_tiezi, postsList);
        ListView listView = (ListView)view.findViewById(R.id.posts_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Posts posts = postsList.get(position);
                Toast.makeText(getActivity(), posts.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), TieziNeirongActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initPosts(){
        Posts post1 = new Posts("国漫", "狐妖小红娘名场面", "天生头发黑",
                10, 3, 7, R.drawable.com_board1_img3);
        Posts post2 = new Posts("国漫", "《狐妖小红娘》竹业篇", "鸡腿是干大事的人",
                11, 4, 8, R.drawable.com_board1_img3);
        postsList.add(post1);
        postsList.add(post2);
    }
}
