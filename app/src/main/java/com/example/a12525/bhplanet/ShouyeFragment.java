package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShouyeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shouye, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showPager();
        showGridview();

    }

    void showPager(){
        ViewPager viewPager = (ViewPager)getActivity().findViewById(R.id.pager);
        List<View> views = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();

        View view1 = inflater.inflate(R.layout.activity_shouye_gundong1, null);
        View view2 = inflater.inflate(R.layout.activity_shouye_gundong2, null);
        View view3 = inflater.inflate(R.layout.activity_shouye_gundong3, null);
        View view4 = inflater.inflate(R.layout.activity_shouye_gundong4, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        GundongAdapter mAdapter = new GundongAdapter(views);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(4);
    }

    void showGridview() {
        GridView gridView = (GridView)getActivity().findViewById(R.id.home_gridview);
        int img[] = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
                R.drawable.img4, R.drawable.ic_launcher_background};
        String name[]={"text1","text2","text3","text4","text5"};
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i <img.length; i++) {
            Map<String, Object> map=new HashMap<>();
            map.put("img", img[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
        String[] from = {"img", "text"};
        int[] to = {R.id.img, R.id.img_text};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), dataList, R.layout.bankuai, from, to);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ndxq", "position=" + position + "  id = " + id);
                Intent intent = new Intent(getActivity(), pinglunActivity.class);
                startActivity(intent);
            }
        });
    }
}
