package com.example.a12525.bhplanet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShequTieziActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private Button button;
    private ImageButton fanhui;
    private RadioGroup radioGroup;
    private RadioButton latest_post, popular_post;
    private ViewPager bankuai_pager;
    private ShequTieziViewpagerAdapter mAdapter;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_bankuai_neirong);
        initView();
        button = (Button) findViewById(R.id.new_post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShequTieziActivity.this, FatieActivity.class);
                startActivity(intent);
            }
        });
        fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.bankuai_tab_bar);
        latest_post = (RadioButton) findViewById(R.id.latest_post);
        popular_post = (RadioButton) findViewById(R.id.popular_post);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.bankuai_tab_bar);
        bankuai_pager = (ViewPager) findViewById(R.id.bankuai_pager);
        mAdapter = new ShequTieziViewpagerAdapter(getSupportFragmentManager());
        bankuai_pager.setAdapter(mAdapter);
        bankuai_pager.setCurrentItem(0);
        bankuai_pager.addOnPageChangeListener(this);
        bankuai_pager.setOffscreenPageLimit(2); //预加载
        latest_post.setChecked(true);
        latest_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
        popular_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.latest_post:
                bankuai_pager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.popular_post:
                bankuai_pager.setCurrentItem(PAGE_TWO);
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
            switch (bankuai_pager.getCurrentItem()) {
                case PAGE_ONE:
                    latest_post.setChecked(true);
                    latest_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    popular_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
                    break;
                case PAGE_TWO:
                    popular_post.setChecked(true);
                    popular_post.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    latest_post.setBackgroundColor(Color.parseColor("#C8C8C8"));
                    break;
            }
        }
    }
}
