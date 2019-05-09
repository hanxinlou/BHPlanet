package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class guanliFragment2 extends Fragment implements View.OnClickListener {

    private LinearLayout huyao;

    public guanliFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guanlifragment2, container, false);
        huyao=(LinearLayout)view .findViewById(R.id.huyao);
        huyao.setOnClickListener(this);
        return view;
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.huyao:
                Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_TIE");
                startActivity(intent);
                break;
        }
    }
}

