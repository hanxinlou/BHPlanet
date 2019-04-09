package com.example.a12525.bhplanet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class guanliFragment extends Fragment {

    private String flag; //判断是哪个页面过来的数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1=inflater.inflate(R.layout.guanlifragment1, container,false);
        return v1;
    }
}

