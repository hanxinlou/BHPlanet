package com.example.a12525.bhplanet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12525.bhplanet.R;

import java.util.List;
import java.util.Objects;


public class PingAdapter extends RecyclerView.Adapter<PingAdapter.ViewHolder>{

    private List<pinglun> pingList;
    private pinglunActivity context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Tou, Zan;
        TextView Name, Time, Pneirong, Zann, Huifu, Zan_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Tou = (ImageView)itemView.findViewById(R.id.tou);
            Name = (TextView)itemView.findViewById(R.id.name);
            Time = (TextView)itemView.findViewById(R.id.time);
            Pneirong = (TextView)itemView.findViewById(R.id.pneirong);
            Zan = (ImageView)itemView.findViewById(R.id.zan);
            Zann = (TextView)itemView.findViewById(R.id.zann);
            Huifu = (TextView)itemView.findViewById(R.id.huifu);
            Zan_num = (TextView)itemView.findViewById(R.id.zan_num);
        }
    }

    public PingAdapter(List<pinglun> pingList, pinglunActivity context){
        this.context = context;
        this.pingList = pingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pinglun_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);

        holder.Zan.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            pinglun ping = pingList.get(position);

            if (ping.getIs_ok() == 0){
                context.setDianZan(ping.getCompose_id(),"2", "1", ping.getUser_id());
            }else if (ping.getIs_ok() == 1){
                context.setDianZan(ping.getCompose_id(),"2", "0", ping.getUser_id());
            }
        });

        holder.Zann.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            pinglun ping = pingList.get(position);

            if (ping.getIs_ok() == 0){
                holder.Zan.setImageResource(R.drawable.dianliang);
                context.setDianZan(ping.getCompose_id(),"2", "1", ping.getUser_id());
            }else if (ping.getIs_ok() == 1){
                holder.Zan.setImageResource(R.drawable.undianliang);
                context.setDianZan(ping.getCompose_id(),"2", "0", ping.getUser_id());
            }
        });


        holder.Huifu.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            pinglun ping = pingList.get(position);
            context.ShowKeyboard("@" + ping.getName() + " ");
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        pinglun ping = pingList.get(i);
        Glide.with(context).load(ping.getTou()).into(viewHolder.Tou);
        viewHolder.Name.setText(ping.getName());
        viewHolder.Time.setText(ping.getTime());
        viewHolder.Pneirong.setText(ping.getPneirong());
        viewHolder.Zan.setImageResource(ping.getZan());
        viewHolder.Zan_num.setText(String.valueOf(ping.getZan_num()));
    }

    @Override
    public int getItemCount() {
        return pingList.size();
    }
}
