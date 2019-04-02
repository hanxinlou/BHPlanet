package com.example.a12525.bhplanet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12525.bhplanet.R;

import java.util.List;



public class PingAdapter extends ArrayAdapter<pinglun> {

    private int resourceId;
    public PingAdapter(Context context, int textViewResourceId, List<pinglun> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        pinglun pinglun=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView Tou=(ImageView)view.findViewById(R.id.tou);
        TextView Name=(TextView) view.findViewById(R.id.name);
        TextView Time=(TextView) view.findViewById(R.id.time);
        TextView Pneirong=(TextView) view.findViewById(R.id.pneirong);
        ImageView Zan=(ImageView) view.findViewById(R.id.zan);
        TextView Zann=(TextView) view.findViewById(R.id.zann);
        TextView Huifu=(TextView) view.findViewById(R.id.huifu);
        TextView Chakan=(TextView) view.findViewById(R.id.chakan);
        Tou.setImageResource(pinglun.getTou());
        Name.setText(pinglun.getName());
        Time.setText(pinglun.getTime());
        Pneirong.setText(pinglun.getPneirong());
        Zan.setImageResource(pinglun.getZan());
        Zann.setText(pinglun.getZann());
        Huifu.setText(pinglun.getHuifu());
        Chakan.setText(pinglun.getChakan());
        return view;
    }
}
