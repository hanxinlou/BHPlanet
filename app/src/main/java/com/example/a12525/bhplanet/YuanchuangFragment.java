package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YuanchuangFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1;
    private List<Map<String, Object>> dataList1;
    public int img1[] = { R.drawable.com_board3_img1 };
    public String  name1[]={"原创总榜"};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_yuanchuang, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView1 = (GridView)view.findViewById(R.id.board_item1);
        dataList1 = new ArrayList<>();
        getData(img1, name1, dataList1, gridView1);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        intent.putExtra("bankuai", "yuanchuang");
        startActivity(intent);
    }
}
