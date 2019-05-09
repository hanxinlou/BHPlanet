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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ShouyeFragment extends Fragment {
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
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        //每隔5秒自动实现vp的position加1
        public void handleMessage(Message msg) {
            vp.setCurrentItem(vp.getCurrentItem()+1);
            System.out.println(vp.getCurrentItem());
            if(running){
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        };
    };

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shouye, container, false);
        ll = (LinearLayout)view.findViewById(R.id.ll2);
        vp = (ViewPager)view.findViewById(R.id.pager);
        iv = (ImageView)view.findViewById(R.id.iv2);
        imagelist = new ArrayList<>();
        showPager(view);
        showGridview(view);
        sousuo = (ImageButton)view.findViewById(R.id.sousuo);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("source", "shouye");
                startActivity(intent);
            }
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
                //内部子空间的距离获取
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

    void showGridview(View view) {
        int img[] = { R.drawable.home_grid_img1, R.drawable.home_grid_img2, R.drawable.home_grid_img3,
                R.drawable.home_grid_img4, R.drawable.home_grid_img5, R.drawable.home_grid_img6, R.drawable.home_grid_img7};
        String home_img_classification[]={"萌宠", "动画", "影视", "漫画", "漫画", "漫画", "影视" };
        String home_img_text[]={"目不转睛", "搞笑片段", "苏大强系列", "996", "一代宗师", "野良神", "你好骚啊"};
        dataList = new ArrayList<>();
        for (int i = 0; i< img.length; i++){
            homeImage = new HomeImage(img[i], home_img_classification[i], home_img_text[i]);
            dataList.add(homeImage);
        }
        HomeImageAdapter adapter = new HomeImageAdapter(dataList);

        recyclerView = (RecyclerView)view.findViewById(R.id.home_view);
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
            holder.homeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    HomeImage homeImage = data.get(position);
                    Intent intent = new Intent(getActivity(), pinglunActivity.class);
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            HomeImage homeImage = data.get(i);
            viewHolder.home_img.setImageResource(homeImage.getImageId());
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
        private int imageId;
        private String home_img_classification;
        private String home_img_text;

        HomeImage(int imageId, String home_img_classification, String home_img_text){
            this.imageId = imageId;
            this.home_img_classification = home_img_classification;
            this.home_img_text = home_img_text;
        }

        public String getHome_img_classification() {
            return home_img_classification;
        }

        public void setHome_img_classification(String home_img_classification) {
            this.home_img_classification = home_img_classification;
        }

        public String getHome_img_text() {
            return home_img_text;
        }

        public void setHome_img_text(String home_img_text) {
            this.home_img_text = home_img_text;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }
}
