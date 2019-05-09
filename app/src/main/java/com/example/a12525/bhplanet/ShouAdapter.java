package com.example.a12525.bhplanet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12525.bhplanet.R;

import java.util.List;



public class ShouAdapter extends ArrayAdapter<shoucang> {

    private int resourceId;
    public ShouAdapter(Context context, int textViewResourceId, List<shoucang> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        shoucang shoucang=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView shouzuopin=(ImageView)view.findViewById(R.id.shouzuopin);
        TextView shouname=(TextView) view.findViewById(R.id.shouname);
        shouzuopin.setImageResource(shoucang.getShouzuopin());
        shouname.setText(shoucang.getShouname());
        return view;
    }
}

