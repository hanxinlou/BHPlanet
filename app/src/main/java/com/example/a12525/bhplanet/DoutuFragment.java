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

public class DoutuFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView1, gridView2;
    private List<Map<String, Object>> dataList1, dataList2;
    public static int [][]img = {{ R.drawable.com_board2_img1 },
                { R.drawable.com_board2_img2, R.drawable.com_board2_img3, R.drawable.com_board2_img4 }};
    public static String  [][]name = {{"斗图总榜"}, {"超人回来了", "小刘鸭", "蘑菇头"}};

    private String[] from = {"img", "text"};
    private int[] to = {R.id.img, R.id.img_text};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_doutu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView1 = (GridView)view.findViewById(R.id.board_item1);
        gridView2 = (GridView)view.findViewById(R.id.board_item2);

        dataList1 = new ArrayList<>();
        dataList2 = new ArrayList<>();

        getData(img[0], name[0], dataList1, gridView1);
        getData(img[1], name[1], dataList2, gridView2);
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
            case "斗图总榜":
                index[0] = 0;
                index[1] = 0;
                break;
            case  "超人回来了":
                index[0] = 1;
                index[1] = 0;
                break;
            case  "小刘鸭":
                index[0] = 1;
                index[1] = 1;
                break;
            case  "蘑菇头":
                index[0] = 1;
                index[1] = 2;
                break;
        }
        Intent intent = new Intent(getActivity(), ShequTieziActivity.class);
        intent.putExtra("bankuai", "doutu");
        intent.putExtra("doutu", index);
        startActivity(intent);
    }
}
