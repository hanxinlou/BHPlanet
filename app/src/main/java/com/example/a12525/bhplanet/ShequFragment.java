package com.example.a12525.bhplanet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShequFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{
    private RadioGroup radioGroup;
    private RadioButton bankuai, tuijian;
    private ViewPager shequPager;
    private ImageButton sousuo;
    private ShequFragmentViewpagerAdapter mAdapter;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shequ, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        radioGroup = (RadioGroup)view.findViewById(R.id.shequ_tab_bar);
        bankuai = (RadioButton)view.findViewById(R.id.bankuai);
        tuijian = (RadioButton)view.findViewById(R.id.tuijian);
        sousuo = (ImageButton)view.findViewById(R.id.shequ_search);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("source", "shequ");
                startActivity(intent);
            }
        });
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.shequ_tab_bar);
        shequPager = (ViewPager)view.findViewById(R.id.shequ_pager);
        mAdapter = new ShequFragmentViewpagerAdapter(getChildFragmentManager());
        shequPager.setAdapter(mAdapter);
        shequPager.setCurrentItem(0);
        shequPager.addOnPageChangeListener(this);
        bankuai.setChecked(true);
        bankuai.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tuijian.setBackgroundColor(Color.parseColor("#e5e5e5"));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bankuai:
                shequPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.tuijian:
                shequPager.setCurrentItem(PAGE_TWO);
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
            switch (shequPager.getCurrentItem()) {
                case PAGE_ONE:
                    bankuai.setChecked(true);
                    bankuai.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tuijian.setBackgroundColor(Color.parseColor("#e5e5e5"));
                    break;
                case PAGE_TWO:
                    tuijian.setChecked(true);
                    tuijian.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    bankuai.setBackgroundColor(Color.parseColor("#e5e5e5"));
                    break;
            }
        }
    }
}
