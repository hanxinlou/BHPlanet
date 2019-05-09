package com.example.a12525.bhplanet;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;


/**
 * Created by Coder-pig on 2015/8/28 0028.
 */
public class guanliActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener{
    private Button btn_botton_dialog;
    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton zuopinguan;
    private RadioButton tiezi;
    private ViewPager vpager;
    private ViewPager vp;
    private LinearLayout ll;
    private ImageView iv;
    //每次圆点移动的位移
    private int move;
    //图片资源
    private int[] image =new int[]{R.drawable.iv1,R.drawable.iv2,
            R.drawable.iv3};
    private List<ImageView> imagelist;
    private boolean running = true;
    //实现自动播放
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
    private guanlifragmentViewpagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;


    @Override
    protected void onDestroy() {
        running = false;
        super.onDestroy();
    };

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuopinguanli);
        initView();
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //handler.sendEmptyMessage(1);
        for(int i = 0; i < image.length; i ++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ScaleType.FIT_XY);
            imagelist.add(imageView);
            //根据图片的个数去放置相应数量的圆点3
            ImageView imageView2 = new ImageView(this);
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
        iv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                //内部子空间的距离获取
                move = ll.getChildAt(1).getLeft()-ll.getChildAt(0).getLeft();
            }
        });
        //添加监听时间
        vp.setOnPageChangeListener(new OnPageChangeListener() {


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
        mAdapter = new guanlifragmentViewpagerAdapter(getSupportFragmentManager());
        bindViews();
        zuopinguan.setChecked(true);
    }



    private void bindViews() {
        //txt_topbar = (TextView) findViewById(R.id.txt_to pbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.shequ_tab_bar);
        zuopinguan = (RadioButton) findViewById(R.id.zuopinguan);
        tiezi = (RadioButton) findViewById(R.id.tiezi);
        rg_tab_bar.setOnCheckedChangeListener(this);


        rg_tab_bar.check(R.id.shequ_tab_bar);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.zuopinguan:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.tiezi:
                vpager.setCurrentItem(PAGE_TWO);
                break;
        }
    }




    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    zuopinguan.setChecked(true);
                    break;
                case PAGE_TWO:
                    tiezi.setChecked(true);
                    break;
            }
        }
    }
    private void initView() {
        // TODO Auto-generated method stub
        ll = (LinearLayout)findViewById(R.id.ll);
        vp = (ViewPager)findViewById(R.id.vp);
        iv = (ImageView)findViewById(R.id.iv);
        imagelist = new ArrayList<>();
    }
}