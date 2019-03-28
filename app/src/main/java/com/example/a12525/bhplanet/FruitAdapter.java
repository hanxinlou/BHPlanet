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



public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
      public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
                 super(context,textViewResourceId,objects);
                 resourceId=textViewResourceId;
             }
      @Override
      public View getView(int position,View convertView,ViewGroup parent){
                 Fruit fruit=getItem(position);           //获取当前项的实例
                 View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
                 ImageView fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
                 TextView fruitName=(TextView) view.findViewById(R.id.fruit_name);
                 fruitImage.setImageResource(fruit.getImageId());
                 fruitName.setText(fruit.getName());
                 return view;
             }
}
