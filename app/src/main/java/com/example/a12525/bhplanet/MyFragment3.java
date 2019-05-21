package com.example.a12525.bhplanet;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends Fragment{
    private List<dongtai>dongtaiList=new ArrayList<>();
    private ListView listView;
    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dongtai, container, false);
        initDong();                 //初始化水果数据
        DongAdapter adapter=new DongAdapter(MyFragment3.this.getActivity(),R.layout.dongtai_item,dongtaiList);

        //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;

    }
    private void initDong(){
        for(int i=0;i<10;i++){
            dongtai apple=new dongtai(R.drawable.song,"太好笑了","一小时前","你麻痹",R.drawable.head4,"傻帽",R.drawable.song1,"宋民国",R.drawable.zhuan,"2",R.drawable.ping,"3",R.drawable.unzan,"1");
            dongtaiList.add(apple);

        }
    }

    public class dongtai {
        private int tou1;
        private String dongname1;
        private String dongdate1;
        private String dongneirong1;
        private int zuozhetou;
        private String zuozheid;
        private int dongimage1;
        private String zuopinming;
        private int zhuan1;
        private String nzhuan1;
        private int ping1;
        private String nping1;
        private int zan1;
        private String nzan1;

        public dongtai(int tou1, String dongname1, String dongdate1, String dongneirong1, int zuozhetou, String zuozheid, int dongimage1, String zuopinming,int zhuan1, String nzhuan1, int ping1,String nping1,int zan1,String nzan1){
            this.tou1=tou1;
            this.dongname1=dongname1;
            this.dongdate1=dongdate1;
            this.dongneirong1=dongneirong1;
            this.zuozhetou=zuozhetou;
            this.zuozheid=zuozheid;
            this.dongimage1=dongimage1;
            this.zuopinming=zuopinming;
            this.zhuan1=zhuan1;
            this.nzhuan1=nzhuan1;
            this.ping1=ping1;
            this.nping1=nping1;
            this.zan1=zan1;
            this.nzan1=nzan1;
        }

        public int getTou1() {
            return tou1;
        }

        public String getDongname1() {
            return dongname1;
        }

        public String getDongdate1() {
            return dongdate1;
        }

        public String getDongneirong1() {
            return dongneirong1;
        }

        public int getZuozhetou() {
            return zuozhetou;
        }

        public String getZuozheid() {
            return zuozheid;
        }

        public int getDongimage1() {
            return dongimage1;
        }

        public String getZuopinming() {
            return zuopinming;
        }

        public int getZhuan1() {
            return zhuan1;
        }

        public int getPing1() {
            return ping1;
        }

        public int getZan1() {
            return zan1;
        }

        public String getNzhuan1() {
            return nzhuan1;
        }

        public String getNping1() {
            return nping1;
        }

        public String getNzan1() {
            return nzan1;
        }
    }

    public class DongAdapter extends ArrayAdapter<dongtai> {

        private int resourceId;
        public DongAdapter(Context context, int textViewResourceId, List<dongtai> objects){
            super(context,textViewResourceId,objects);
            resourceId=textViewResourceId;
        }
        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            dongtai dongtai=getItem(position);           //获取当前项的实例
            View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            ImageView tou1=(ImageView)view.findViewById(R.id.tou1);
            TextView dongname1=(TextView) view.findViewById(R.id.dongname1);
            TextView dongdate1=(TextView) view.findViewById(R.id.dongdate1);
            TextView dongneirong1=(TextView) view.findViewById(R.id.dongneirong1);
            ImageView zuozhetou=(ImageView) view.findViewById(R.id.zuozhetou);
            TextView zuozheid=(TextView) view.findViewById(R.id.zuozheid);
            ImageView dongimage1=(ImageView) view.findViewById(R.id.dongimage1);
            TextView zuopinming=(TextView) view.findViewById(R.id.zuopinming);
            ImageView zhuan1=(ImageView) view.findViewById(R.id.zhuan1);
            TextView nzhuan1=(TextView) view.findViewById(R.id.nzhuan1);
            ImageView ping1=(ImageView) view.findViewById(R.id.ping1);
            TextView nping1=(TextView) view.findViewById(R.id.nping1);
            ImageView zan1=(ImageView) view.findViewById(R.id.zan1);
            TextView nzan1=(TextView) view.findViewById(R.id.nzan1);

            tou1.setImageResource(dongtai.getTou1());
            dongname1.setText(dongtai.getDongname1());
            dongdate1.setText(dongtai.getDongdate1());
            dongneirong1.setText(dongtai.getDongneirong1());
            zuozhetou.setImageResource(dongtai.getZuozhetou());
            zuozheid.setText(dongtai.getZuozheid());
            dongimage1.setImageResource(dongtai.getDongimage1());
            zuopinming.setText(dongtai.getZuopinming());
            zhuan1.setImageResource(dongtai.getZhuan1());
            nzhuan1.setText(dongtai.getNzhuan1());
            ping1.setImageResource(dongtai.getPing1());
            nping1.setText(dongtai.getNping1());
            zan1.setImageResource(dongtai.getZan1());
            nzan1.setText(dongtai.getNzan1());
            return view;
        }
    }

}