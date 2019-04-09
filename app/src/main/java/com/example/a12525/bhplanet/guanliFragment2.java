package com.example.a12525.bhplanet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class guanliFragment2 extends Fragment {

    private String flag; //判断是哪个页面过来的数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v2=inflater.inflate(R.layout.guanlifragment2, container,false);
        return v2;
    }
}

