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



public class TongAdapter extends ArrayAdapter<tongzhi> {

    private int resourceId;
    public TongAdapter(Context context, int textViewResourceId, List<tongzhi> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        tongzhi tongzhi=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView tongtou=(ImageView)view.findViewById(R.id.tongtou);
        TextView tongid=(TextView) view.findViewById(R.id.tongid);
        TextView tongday=(TextView) view.findViewById(R.id.tongday);
        TextView tongtime=(TextView) view.findViewById(R.id.tongtime);
        TextView tongneirong=(TextView) view.findViewById(R.id.tongneirong);
        tongtou.setImageResource(tongzhi.getTongtou());
        tongid.setText(tongzhi.getTongid());
        tongday.setText(tongzhi.getTongday());
        tongtime.setText(tongzhi.getTongtime());
        tongneirong.setText(tongzhi.getTongneirong());
        return view;
    }
}