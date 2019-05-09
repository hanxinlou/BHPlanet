package com.example.a12525.bhplanet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12525.bhplanet.R;

import java.util.List;



public class ChaAdapter extends ArrayAdapter<chakan> {

    private int resourceId;
    public ChaAdapter(Context context, int textViewResourceId, List<chakan> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        chakan chakan=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView tou=(ImageView)view.findViewById(R.id.tou);
        TextView name=(TextView) view.findViewById(R.id.name);
        TextView time=(TextView)view.findViewById(R.id.time);
        ImageView dianliang=(ImageView)view.findViewById(R.id.dianliang);
        TextView pneirong=(TextView)view.findViewById(R.id.pneirong);

        tou.setImageResource(chakan.getTou());
        name.setText(chakan.getName());
        time.setText(chakan.getTime());
        dianliang.setImageResource(chakan.getDianliang());
        pneirong.setText(chakan.getPneirong());
        tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText()
            }
        });
        return view;
    }
}

