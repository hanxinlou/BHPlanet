package com.example.a12525.bhplanet;



import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View.OnClickListener;
import android.widget.Button;

public class guanliActivity extends FragmentActivity implements OnClickListener,
        OnPageChangeListener {

    // 布局文件中自己的myvirwpager
    private ViewPager myvirwpager;
    // 选项卡中的三个Button
    private Button one, two;
    // 指示标签的ImageView
    private ImageView huadong1;
    // 指示标签的横坐标
    private float cursorX = 0;
    // 定义获取所有按钮的宽度数组
    private int[] WidrhArgs;
    // 定义所有标题按钮的数组
    private Button[] ButtonArgs;
    // fragment的集合
    private ArrayList<Fragment> list;
    // viewpage适配器
    private guanlifragmentViewpagerAdapter adapter;
    FragmentManager fm=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuopinguanli);
        Init();
    }

    private void Init() {
        // 获取控件
        myvirwpager = (ViewPager) findViewById(R.id.myviewpager);
        one = (Button) findViewById(R.id.zuopin);
        two = (Button) findViewById(R.id.tiezi );
        // 初始化按钮数组
        ButtonArgs = new Button[] { one, two };
        // 设置指示标签颜色为红色
        // 按钮单机事件
        one.setOnClickListener(this);
        two.setOnClickListener(this);

        // 将fragment放进集合，并初始化适配器
        list = new ArrayList<Fragment>();
        list.add(new guanliFragment());
        list.add(new guanliFragment2());
        adapter = new guanlifragmentViewpagerAdapter(fm, list);
        myvirwpager.setAdapter(adapter);
        // viewpage监听事件，重写onPageSelected()方法，实现左右滑动页面
        myvirwpager.setOnPageChangeListener(this);
        // 初始按钮颜色
        resetButtonColor();
        // 默认第一页
        one.setTextColor(Color.parseColor("#000000"));
    }

    // 设置按钮颜色
    public void resetButtonColor() {
        one.setBackgroundColor(Color.parseColor("#e5e5e5"));
        two.setBackgroundColor(Color.parseColor("#e5e5e5"));
        one.setTextColor(Color.BLACK);
        two.setTextColor(Color.BLACK);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zuopin:
                myvirwpager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.tiezi:
                myvirwpager.setCurrentItem(1);
                cursorAnim(1);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {

        if (WidrhArgs == null) {
            WidrhArgs = new int[] { one.getWidth(), two.getWidth()};
        }

        // 根据每次选中的按钮，重置颜色
        resetButtonColor();
        // 将滑动到当前的标签下，改动标签颜色
        ButtonArgs[arg0].setBackgroundColor(Color.parseColor("#FFFFFF"));
        cursorAnim(arg0);

    }

    // 指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        // 每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        // 再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) huadong1
                .getLayoutParams();
        // 减去边距*2，以对齐标题栏文字
        lp.width = WidrhArgs[curItem] - ButtonArgs[0].getPaddingLeft() * 2;
        huadong1.setLayoutParams(lp);
        // 循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + ButtonArgs[i].getWidth();
        }
        // 再加上当前页面的左边距，即为指示器当前应处的位置
        huadong1.setX(cursorX + ButtonArgs[curItem].getPaddingLeft());
    }
}
