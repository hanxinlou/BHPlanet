package com.example.a12525.bhplanet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12525.bhplanet.R;

import java.util.List;
import java.util.Objects;


public class guanAdapter extends ArrayAdapter<guanzhu> {

    private int resourceId;
    public guanAdapter(Context context, int textViewResourceId, List<guanzhu> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        guanzhu guanzhu = getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView Gtou=(ImageView)view.findViewById(R.id.gtou);
        TextView Gname=(TextView) view.findViewById(R.id.gname);

        Glide.with(getContext()).load(guanzhu.getGtou()).into(Gtou);
        Gname.setText(guanzhu.getGname());
        return view;
    }
}
