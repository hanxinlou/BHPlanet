package com.example.a12525.bhplanet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



//    public class DongAdapter extends ArrayAdapter<MyFragment3.dongtai> {
//
//
//        private int resourceId;
//        public DongAdapter(Context context, int textViewResourceId, List<MyFragment3.dongtai> objects){
//            super(context,textViewResourceId,objects);
//            resourceId=textViewResourceId;
//        }
//
//
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent){
//            MyFragment3.dongtai dongtai=getItem(position);           //获取当前项的实例
//
//            View view;
//            DongAdapter.ViewHolder viewHolder;
//            if (convertView == null) {
//                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//                viewHolder = new DongAdapter.ViewHolder();
//
//                viewHolder.tou1=(ImageView)view.findViewById(R.id.tou1);
//                viewHolder.dongname1=(TextView) view.findViewById(R.id.dongname1);
//                viewHolder.dongdate1=(TextView) view.findViewById(R.id.dongdate1);
//                viewHolder.dongneirong1=(TextView) view.findViewById(R.id.dongneirong1);
//                viewHolder.zuozhetou=(ImageView) view.findViewById(R.id.zuozhetou);
//                viewHolder.zuozheid=(TextView) view.findViewById(R.id.zuozheid);
//                viewHolder.dongimage1=(ImageView) view.findViewById(R.id.dongimage1);
//                viewHolder.zuopinming=(TextView) view.findViewById(R.id.zuopinming);
//                viewHolder.zhuan1=(ImageView) view.findViewById(R.id.zhuan1);
//                viewHolder.nzhuan1=(TextView) view.findViewById(R.id.nzhuan1);
//                viewHolder.ping1=(ImageView) view.findViewById(R.id.ping1);
//                viewHolder.nping1=(TextView) view.findViewById(R.id.nping1);
//                viewHolder.zan1=(ImageView) view.findViewById(R.id.zan1);
//                viewHolder.nzan1=(TextView) view.findViewById(R.id.nzan1);
//                view.setTag(viewHolder);
//            } else {
//                view = convertView;
//                viewHolder = (DongAdapter.ViewHolder) view.getTag();
//            }
//
//
//            Glide.with(getContext()).load(dongtai.getTou1()).into(viewHolder.tou1);
//            viewHolder.dongname1.setText(dongtai.getDongname1());
//            viewHolder.dongdate1.setText(dongtai.getDongdate1());
//            viewHolder.dongneirong1.setText(dongtai.getDongneirong1());
//            Glide.with(getContext()).load(dongtai.getZuozhetou()).into(viewHolder.zuozhetou);
//            viewHolder.zuozheid.setText(dongtai.getZuozheid());
//            Glide.with(getContext()).load(dongtai.getDongimage1()).into(viewHolder.dongimage1);
//            viewHolder.zuopinming.setText(dongtai.getZuopinming());
//            viewHolder.zhuan1.setImageResource(dongtai.getZhuan1());
//            viewHolder.nzhuan1.setText(String.valueOf(dongtai.getNzhuan1()));
//            viewHolder.ping1.setImageResource(dongtai.getPing1());
//            viewHolder.nping1.setText(String.valueOf(dongtai.getNping1()));
//            viewHolder.zan1.setImageResource(dongtai.getZan1());
//            viewHolder.nzan1.setText(String.valueOf(dongtai.getNzan1()));
//            viewHolder.zhuan1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemZhuanListener.onZhuanClick(position);
//                }
//            });
//            viewHolder.tou1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemtouListener.ontouClick(position);
//                }
//            });
//
//            return view;
//
//        }
//        class ViewHolder {
//            ImageView tou1;
//            TextView dongname1;
//            TextView dongdate1;
//            TextView dongneirong1;
//            ImageView zuozhetou;
//            TextView zuozheid;
//            ImageView dongimage1;
//            TextView zuopinming;
//            ImageView zhuan1;
//            TextView nzhuan1;
//            ImageView ping1;
//            TextView nping1;
//            ImageView zan1;
//            TextView nzan1;
//        }
//
//
//    public interface onItemZhuanListener {
//        void onZhuanClick(int position);
//    }
//    public interface onItemtouListener {
//        void ontouClick(int position);
//    }
//    private onItemZhuanListener mOnItemZhuanListener;
//    private onItemtouListener mOnItemtouListener;
//
//    public void setOnItemZhuanClickListener(onItemZhuanListener mOnItemZhuanListener) {
//        this.mOnItemZhuanListener = mOnItemZhuanListener;
//    }
//    public void setOnItemtouClickListener(onItemtouListener mOnItemtouListener) {
//        this.mOnItemtouListener = mOnItemtouListener;
//    }
//
//}
public class DongAdapter extends RecyclerView.Adapter<DongAdapter.ViewHolder>{

    private List<MyFragment3.dongtai> dongtaiList;
    private MyFragment3 context;

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

    public DongAdapter(List<MyFragment3.dongtai> dongtaiList, MyFragment3 context){
        this.context = context;
        this.dongtaiList = dongtaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dongtai_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        holder.dongimage.setOnClickListener( v -> {
            int position = holder.getAdapterPosition();
            MyFragment3.dongtai dongtai = dongtaiList.get(position);
            Intent intent = new Intent("com.example.a12525.bhplanet.ACTION_PING");
            intent.putExtra("home_opus_id", dongtai.getLink_id());
            context.startActivity(intent);

        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyFragment3.dongtai dongtai = dongtaiList.get(i);
        Glide.with(context).load(dongtai.getTou1()).into(viewHolder.tou1);
        viewHolder.dongname1.setText(dongtai.getDongname1());
        viewHolder.dongdate1.setText(dongtai.getDongdate1());
        viewHolder.dongneirong1.setText(dongtai.getDongneirong1());
        Glide.with(context).load(dongtai.getZuozhetou()).into(viewHolder.zuozhetou);
        viewHolder.zuozheid.setText(dongtai.getZuozheid());
        Glide.with(context).load(dongtai.getDongimage1()).into(viewHolder.dongimage1);
        viewHolder.zuopinming.setText(dongtai.getZuopinming());
        viewHolder.zhuan1.setImageResource(dongtai.getZhuan1());
        viewHolder.nzhuan1.setText(String.valueOf(dongtai.getNzhuan1()));
        viewHolder.ping1.setImageResource(dongtai.getPing1());
        viewHolder.nping1.setText(String.valueOf(dongtai.getNping1()));
        viewHolder.zan1.setImageResource(dongtai.getZan1());
        viewHolder.nzan1.setText(String.valueOf(dongtai.getNzan1()));
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return dongtaiList.size();
    }
}


