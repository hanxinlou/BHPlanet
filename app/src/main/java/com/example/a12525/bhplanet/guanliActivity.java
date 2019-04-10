package com.example.a12525.bhplanet;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


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

    private guanlifragmentViewpagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuopinguanli);


        mAdapter = new guanlifragmentViewpagerAdapter(getSupportFragmentManager());
        bindViews();
        zuopinguan.setChecked(true);
    }



    private void bindViews() {
        //txt_topbar = (TextView) findViewById(R.id.txt_to pbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        zuopinguan = (RadioButton) findViewById(R.id.zuopinguan);
        tiezi = (RadioButton) findViewById(R.id.tiezi);
        rg_tab_bar.setOnCheckedChangeListener(this);


        rg_tab_bar.check(R.id.rg_tab_bar);
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
}