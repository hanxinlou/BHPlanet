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

public class DoutuFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1, gridView2;
    private List<Map<String, Object>> dataList1, dataList2;
    private int img1[] = { R.drawable.img1 },
                img2[] = { R.drawable.img2, R.drawable.img2, R.drawable.img2 };
    private String  name1[]={"斗图总榜"},
                    name2[]={"超人回来了", "小刘鸭", "蘑菇头"};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_doutu, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showGridview();
    }

    private void showGridview() {
        gridView1 = (GridView)getActivity().findViewById(R.id.board_item1);
        gridView2 = (GridView)getActivity().findViewById(R.id.board_item2);

        dataList1 = new ArrayList<>();
        dataList2 = new ArrayList<>();

        getData(img1, name1, dataList1, gridView1);
        getData(img2, name2, dataList2, gridView2);
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
