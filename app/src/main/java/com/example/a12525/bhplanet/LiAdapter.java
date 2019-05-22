package com.example.a12525.bhplanet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12525.bhplanet.R;

import java.util.List;



public class LiAdapter extends ArrayAdapter<lishi> {

    private int resourceId;
    public LiAdapter(Context context, int textViewResourceId, List<lishi> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        lishi lishi=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView Liuzuopin=(ImageView)view.findViewById(R.id.liuzuopin);
        TextView Liuming=(TextView) view.findViewById(R.id.liuming);
        TextView Liuid=(TextView) view.findViewById(R.id.liuid);
        TextView Liutime=(TextView) view.findViewById(R.id.liutime);

        //Liuzuopin.setImageResource(lishi.getLiuzuopin());
        Liuming.setText(lishi.getLiuming());
        Liuid.setText(lishi.getLiuid());
        Liutime.setText(lishi.getLiutime());

        return view;
    }
}