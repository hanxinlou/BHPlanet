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

public class ZdongtaiAdapter extends RecyclerView.Adapter<ZdongtaiAdapter.ViewHolder>{

    private List<zdongtai> zdongtaiList;
    private zdongtaiActivity context;

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

    public ZdongtaiAdapter(List<zdongtai> zdongtaiList, zdongtaiActivity context){
        this.context = context;
        this.zdongtaiList = zdongtaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ztdongtai_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        holder.dongimage.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            zdongtai zdongtai = zdongtaiList.get(position);
            Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_PING");
            intent.putExtra("home_opus_id", zdongtai.getLink_id());
            context.startActivity(intent);

        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        zdongtai zdongtai = zdongtaiList.get(i);
        Glide.with(context).load(zdongtai.getTou1()).into(viewHolder.tou1);
        viewHolder.dongname1.setText(zdongtai.getDongname1());
        viewHolder.dongdate1.setText(zdongtai.getDongdate1());
        viewHolder.dongneirong1.setText(zdongtai.getDongneirong1());
        Glide.with(context).load(zdongtai.getZuozhetou()).into(viewHolder.zuozhetou);
        viewHolder.zuozheid.setText(zdongtai.getZuozheid());
        Glide.with(context).load(zdongtai.getDongimage1()).into(viewHolder.dongimage1);
        viewHolder.zuopinming.setText(zdongtai.getZuopinming());
        viewHolder.zhuan1.setImageResource(zdongtai.getZhuan1());
        viewHolder.nzhuan1.setText(String.valueOf(zdongtai.getNzhuan1()));
        viewHolder.ping1.setImageResource(zdongtai.getPing1());
        viewHolder.nping1.setText(String.valueOf(zdongtai.getNping1()));
        viewHolder.zan1.setImageResource(zdongtai.getZan1());
        viewHolder.nzan1.setText(String.valueOf(zdongtai.getNzan1()));
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return zdongtaiList.size();
    }
}

