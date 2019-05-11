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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YingshiFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1, gridView2, gridView3;
    private List<Map<String, Object>> dataList1, dataList2, dataList3;
    public static int [][]img =
            {{ R.drawable.com_board4_img1 },
             { R.drawable.com_board4_img2, R.drawable.com_board4_img3, R.drawable.com_board4_img4 },
             { R.drawable.com_board4_img5 }};
    public static String [][]name = {{"影视总榜"}, {"国产影视", "欧美影视", "亚洲影视"}, {"漫威"}};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_yingshi, container, false);
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

        getData(img[0], name[0], dataList1, gridView1);
        getData(img[1], name[1], dataList2, gridView2);
        getData(img[2], name[2], dataList3, gridView3);
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
        TextView textView= (TextView) view.findViewById(R.id.img_text);
        String text = (String)textView.getText();
        int []index = new int[2];
        switch (text) {
            case "影视总榜":
                index[0] = 0;
                index[1] = 0;
                break;
            case  "国产影视":
                index[0] = 1;
                index[1] = 0;
                break;
            case  "欧美影视":
                index[0] = 1;
                index[1] = 1;
                break;
            case  "亚洲影视":
                index[0] = 1;
                index[1] = 2;
                break;
            case  "漫威":
                index[0] = 2;
                index[1] = 0;
                break;
        }
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        intent.putExtra("bankuai", "yingshi");
        intent.putExtra("yingshi", index);
        startActivity(intent);
    }
}
