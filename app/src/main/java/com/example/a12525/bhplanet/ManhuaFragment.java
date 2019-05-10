package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManhuaFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1, gridView2, gridView3;
    private List<Map<String, Object>> dataList1, dataList2, dataList3;
    public int img1[] = { R.drawable.com_board1_img1 },
                img2[] = { R.drawable.com_board1_img2, R.drawable.com_board1_img3, R.drawable.com_board1_img4 },
                img3[] = { R.drawable.com_board1_img5 };
    public String  name1[]={"漫画总榜"},
                    name2[]={"日漫", "国漫", "美漫"},
                    name3[]={"海贼王"};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_manhua, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView1 = (GridView)view.findViewById(R.id.board_item1);
        gridView2 = (GridView)view.findViewById(R.id.board_item2);
        gridView3 = (GridView)view.findViewById(R.id.board_item3);

        dataList1 = new ArrayList<>();
        dataList2 = new ArrayList<>();
        dataList3 = new ArrayList<>();

        getData(img1, name1, dataList1, gridView1);
        getData(img2, name2, dataList2, gridView2);
        getData(img3, name3, dataList3, gridView3);
    }

    private void getData(int[] img, String[] name, List<Map<String, Object>> dataList, GridView gridView) {
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", img[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.bankuai_tupian, from, to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        intent.putExtra("bankuai", "manhua");
        startActivity(intent);
    }
}
