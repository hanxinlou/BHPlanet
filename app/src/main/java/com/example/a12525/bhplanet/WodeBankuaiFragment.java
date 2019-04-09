package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class WodeBankuaiFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1;
    private List<Map<String, Object>> dataList1;
    private int img1[] = { R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4 };
    private String  name1[]={"国漫", "日漫", "漫画总榜", "漫威"};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_wode, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showGridview();
    }

    private void showGridview() {
        gridView1 = (GridView)getActivity().findViewById(R.id.board_item1);
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
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.bankuai, from, to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        startActivity(intent);
    }
}
