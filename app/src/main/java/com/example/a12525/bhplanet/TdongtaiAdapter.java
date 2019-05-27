package com.example.a12525.bhplanet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TdongtaiAdapter extends RecyclerView.Adapter<TdongtaiAdapter.ViewHolder>{

    private List<tdongtai> tdongtaiList;
    private tdongtaiActivity context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tou1;
        TextView dongname1;
        TextView dongdate1;
        TextView dongneirong1;
        ImageView zuozhetou;
        TextView zuozheid;
        ImageView dongimage1;
        TextView zuopinming;
        ImageView zhuan1;
        TextView nzhuan1;
        ImageView ping1;
        TextView nping1;
        ImageView zan1;
        TextView nzan1;
        View dongimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dongimage=itemView;
            tou1=(ImageView)itemView.findViewById(R.id.tou1);
            dongname1=(TextView) itemView.findViewById(R.id.dongname1);
            dongdate1=(TextView) itemView.findViewById(R.id.dongdate1);
            dongneirong1=(TextView) itemView.findViewById(R.id.dongneirong1);
            zuozhetou=(ImageView) itemView.findViewById(R.id.zuozhetou);
            zuozheid=(TextView) itemView.findViewById(R.id.zuozheid);
            dongimage1=(ImageView) itemView.findViewById(R.id.dongimage1);
            zuopinming=(TextView) itemView.findViewById(R.id.zuopinming);
            zhuan1=(ImageView) itemView.findViewById(R.id.zhuan1);
            nzhuan1=(TextView) itemView.findViewById(R.id.nzhuan1);
            ping1=(ImageView) itemView.findViewById(R.id.ping1);
            nping1=(TextView) itemView.findViewById(R.id.nping1);
            zan1=(ImageView) itemView.findViewById(R.id.zan1);
            nzan1=(TextView) itemView.findViewById(R.id.nzan1);
        }
    }

    public TdongtaiAdapter(List<tdongtai> tdongtaiList, tdongtaiActivity context){
        this.context = context;
        this.tdongtaiList = tdongtaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ztdongtai_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        holder.dongimage.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            tdongtai tdongtai = tdongtaiList.get(position);
            Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_PING");
            intent.putExtra("home_opus_id", tdongtai.getLink_id());
            context.startActivity(intent);

        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        tdongtai tdongtai = tdongtaiList.get(i);
        Glide.with(context).load(tdongtai.getTou1()).into(viewHolder.tou1);
        viewHolder.dongname1.setText(tdongtai.getDongname1());
        viewHolder.dongdate1.setText(tdongtai.getDongdate1());
        viewHolder.dongneirong1.setText(tdongtai.getDongneirong1());
        Glide.with(context).load(tdongtai.getZuozhetou()).into(viewHolder.zuozhetou);
        viewHolder.zuozheid.setText(tdongtai.getZuozheid());
        Glide.with(context).load(tdongtai.getDongimage1()).into(viewHolder.dongimage1);
        viewHolder.zuopinming.setText(tdongtai.getZuopinming());
        viewHolder.zhuan1.setImageResource(tdongtai.getZhuan1());
        viewHolder.nzhuan1.setText(String.valueOf(tdongtai.getNzhuan1()));
        viewHolder.ping1.setImageResource(tdongtai.getPing1());
        viewHolder.nping1.setText(String.valueOf(tdongtai.getNping1()));
        viewHolder.zan1.setImageResource(tdongtai.getZan1());
        viewHolder.nzan1.setText(String.valueOf(tdongtai.getNzan1()));
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return tdongtaiList.size();
    }
}

