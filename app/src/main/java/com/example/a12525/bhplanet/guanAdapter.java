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

import com.example.a12525.bhplanet.R;

import java.util.List;


public class guanAdapter extends ArrayAdapter<guanzhu> {

    private int resourceId;
    public guanAdapter(Context context, int textViewResourceId, List<guanzhu> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        guanzhu guanzhu=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView Gtou=(ImageView)view.findViewById(R.id.gtou);
        TextView Gname=(TextView) view.findViewById(R.id.gname);
       // Switch Guanfou =(Switch)view.findViewById(R.id.guanfou);

        Gtou.setImageResource(guanzhu.getGtou());
//        for(int i = 0; i < guanzhuActivity.author_list.size(); i++){
//            Log.d("ndxq",  guanzhuActivity.author_list.get(0));
//            Gname.setText(guanzhuActivity.author_list.get(i));
//        }
        return view;
    }
}
