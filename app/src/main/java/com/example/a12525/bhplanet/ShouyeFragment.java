package com.example.a12525.bhplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Request;
import okhttp3.Response;


public class ShouyeFragment extends Fragment {
    private Map<String, ArrayList<String>>map = new HashMap<>();
    private ImageButton sousuo;
    private List<HomeImage>dataList;
    private HomeImage homeImage;
    private RecyclerView recyclerView;
    private int[] image =new int[]{R.drawable.home_pager_img1,R.drawable.home_pager_img2,
            R.drawable.home_pager_img3};
    private List<ImageView> imagelist;
    private ViewPager vp;
    private LinearLayout ll;
    private ImageView iv;
    private int move;
    private boolean running = true;

    private ArrayList<String> opus_title_list = new ArrayList<>();
    private ArrayList<String> opus_id_list = new ArrayList<>();
    private ArrayList<String> type_list = new ArrayList<>();
    private ArrayList<String> picture_list = new ArrayList<>();


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            vp.setCurrentItem(vp.getCurrentItem()+1);
            System.out.println(vp.getCurrentItem());
            if(msg.what == 0x123) {
                showGridview();
            }
            else if(running){
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    };

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shouye, container, false);
        getDatasync();
        ll = (LinearLayout)view.findViewById(R.id.ll2);
        vp = (ViewPager)view.findViewById(R.id.pager);
        iv = (ImageView)view.findViewById(R.id.iv2);
        imagelist = new ArrayList<>();
        showPager(view);
        sousuo = (ImageButton)view.findViewById(R.id.sousuo);
        sousuo.setOnClickListener( v -> {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("source", "shouye");
                startActivity(intent);
        });
        return view;
    }


    void showPager(View view){
        for(int i = 0; i < image.length; i ++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imagelist.add(imageView);
            //根据图片的个数去放置相应数量的圆点3
            ImageView imageView2 = new ImageView(getActivity());
            imageView2.setImageResource(R.drawable.circle);
            //因为没有直接改变margin的方法，所以这里使用的LayoutParams来改变leftMargin
            LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i > 0){
                layoutParams.leftMargin = 20;
            }
            imageView2.setLayoutParams(layoutParams);
            ll.addView(imageView2);

        }
        //布局加载完成节后运行
        iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                //内部子空间的距离获取f
                move = ll.getChildAt(1).getLeft()-ll.getChildAt(0).getLeft();
            }
        });
        //添加监听时间
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

            }


            //滑动界面触发，这里控制标记点的改变就可以了
            //position第几幅图，offset滑动百分比
            @Override
            public void onPageScrolled(int position, float offset, int arg2) {
                // TODO Auto-generated method stub
                System.out.println(position);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)iv.getLayoutParams();
                if((position+1)%image.length != 0)
                    layoutParams.leftMargin = (int)((position%image.length)*move+offset*move);
                iv.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        //添加适配器
        //vp.setPageTransformer(true, arg1);
        vp.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub

                container.addView(imagelist.get((position+image.length)%(image.length)));
                //返回滑动到的图片
                return imagelist.get((position+image.length)%(image.length));
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // TODO Auto-generated method stub
                //移除一张前图片
                container.removeView(imagelist.get((position+image.length)%(image.length)));
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }
            //使得ViewPager可以无限移动，设置边界为Integer.MAX_VALUE
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return Integer.MAX_VALUE;
            }
        });

        //插入切换样式，这个随便一个地方拷贝一份即可，可以到官方拷贝
        vp.setPageTransformer(true, new DepthPageTransformer());
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    void showGridview() {
        Log.d("shouye", String.valueOf(opus_title_list.size()));

        dataList = new ArrayList<>();
        for (int i = 0; i< opus_title_list.size(); i++){
            homeImage = new HomeImage(picture_list.get(i), type_list.get(i), opus_title_list.get(i), opus_id_list.get(i));
            dataList.add(homeImage);
        }
        HomeImageAdapter adapter = new HomeImageAdapter(dataList);

        recyclerView = (RecyclerView)getActivity().findViewById(R.id.home_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
     }

    public class HomeImageAdapter extends RecyclerView.Adapter<HomeImageAdapter.ViewHolder> {
        private List<HomeImage>data;

        class ViewHolder extends RecyclerView.ViewHolder {
            View homeImageView;
            ImageView home_img;
            TextView home_img_classification;
            TextView home_img_text;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                homeImageView = itemView;
                home_img = (ImageView)itemView.findViewById(R.id.home_item_img);
                home_img_classification = (TextView)itemView.findViewById(R.id.home_img_classification);
                home_img_text = (TextView)itemView.findViewById(R.id.home_img_text);
            }
        }

        public HomeImageAdapter(List<HomeImage> dataList){
            data = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shouye_tupian, viewGroup, false);;
            final ViewHolder holder = new ViewHolder(view);
            holder.homeImageView.setOnClickListener( v -> {
                    int position = holder.getAdapterPosition();
                    HomeImage homeImage = data.get(position);
                    Intent intent = new Intent(getActivity(), pinglunActivity.class);
                    intent.putExtra("home_opus_id", homeImage.getItemId());
                    startActivity(intent);
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            HomeImage homeImage = data.get(i);
            Glide.with(Objects.requireNonNull(getActivity())).load(homeImage.getImageId()).into(viewHolder.home_img);
            viewHolder.home_img_classification.setText(homeImage.getHome_img_classification());
            viewHolder.home_img_text.setText(homeImage.getHome_img_text());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

    }

    public class HomeImage{
        private String imageId;
        private String home_img_classification;
        private String home_img_text;
        private String itemId;

        HomeImage(String imageId, String home_img_classification, String home_img_text, String itemId){
            this.imageId = imageId;
            this.home_img_classification = home_img_classification;
            this.home_img_text = home_img_text;
            this.itemId = itemId;
        }

        public String getHome_img_classification() {
            return home_img_classification;
        }

        public String getHome_img_text() {
            return home_img_text;
        }

        public String getImageId() {
            return imageId;
        }

        public String getItemId() {
            return itemId;
        }
    }

    public void getDatasync(){
        new Thread(() -> {
            try {
                String url = "http://129.211.5.66:8080/home/list?user_id=" + Client.user_id + "&currpage=0";
                Request request = new Request.Builder()
                            .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                response = Client.client.newCall(request).execute();//得到Response 对象
                if (response.isSuccessful()) {
                    Log.d("shouye","response.code()=="+response.code());
                    Log.d("shouye","response.message()=="+response.message());
                    String resData = response.body().string();
                    Log.d("shouye","res=="+resData);
                    //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    parseData(resData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }).start();
    }

    private void parseData(String resData){
        try{
            JSONObject jsonObject = new JSONObject(resData);
            JSONObject opus_data = jsonObject.getJSONObject("opuscontent");
            JSONArray opus_info = opus_data.getJSONArray("opusinfos");

            opus_title_list.clear();
            opus_id_list.clear();
            picture_list.clear();
            type_list.clear();
            for(int i = 0; i < opus_info.length(); i++) {
                JSONObject object = opus_info.getJSONObject(i);
                String opus_title = object.optString("opus_title");
                String opus_id = object.optString("opus_id");
                String picture = object.optString("picture");
                String type = String.valueOf(object.optInt("type"));

                opus_title_list.add(opus_title);
                opus_id_list.add(opus_id);
                picture_list.add(picture);
                type_list.add(type);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
