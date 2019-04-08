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
public class DaohangActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
       ViewPager.OnPageChangeListener ,View.OnClickListener{
    private Button btn_botton_dialog;
    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton shouye;
    private RadioButton shequ;
    private RadioButton dongtai;
    private RadioButton wo;
    private Button chuangzuo;
    private ViewPager vpager;
    private Button chungzuo;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daohang);

        btn_botton_dialog = (Button) findViewById(R.id.chuangzuo);
        btn_botton_dialog.setOnClickListener(this);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        shouye.setChecked(true);
    }

    private void setDialog(){
        Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.button_dialog, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        root.findViewById(R.id.quxiao).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void bindViews() {
        //txt_topbar = (TextView) findViewById(R.id.txt_to pbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        shouye = (RadioButton) findViewById(R.id.shouye);
        shequ = (RadioButton) findViewById(R.id.shequ);
        dongtai = (RadioButton) findViewById(R.id.dongtai);
        wo = (RadioButton) findViewById(R.id.wo);
        chuangzuo =(Button) findViewById(R.id.chuangzuo);
        rg_tab_bar.setOnCheckedChangeListener(this);

        Drawable drawablechannel = getResources().getDrawable(R.drawable.tab_menu_channel);
        drawablechannel.setBounds(0, 0, 90, 90);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        shouye.setCompoundDrawables(null, drawablechannel, null, null);//只放上面

        Drawable drawablemessage = getResources().getDrawable(R.drawable.tab_menu_message);
        drawablemessage.setBounds(0, 0, 90, 90);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        shequ.setCompoundDrawables(null, drawablemessage, null, null);//只放上面

        Drawable drawablebetter = getResources().getDrawable(R.drawable.tab_menu_better);
        drawablebetter.setBounds(0, 0, 90, 90);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        dongtai.setCompoundDrawables(null, drawablebetter, null, null);//只放上面

        Drawable drawablesetting = getResources().getDrawable(R.drawable.tab_menu_setting);
        drawablesetting.setBounds(0, 0, 90, 90);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        wo.setCompoundDrawables(null, drawablesetting, null, null);//只放上面

        /*Drawable drawablechuangzuo = getResources().getDrawable(R.drawable.add);
        drawablesetting.setBounds(0, 0, 100, 100);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        chuangzuo.setCompoundDrawables(null, drawablechuangzuo, null, null);//只放上面*/

        rg_tab_bar.check(R.id.rg_tab_bar);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.shouye:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.shequ:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.dongtai:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.wo:
                vpager.setCurrentItem(PAGE_FOUR);
               // Intent intent = new Intent(DaohangActivity.this,MainActivity.class);
                //startActivity(intent);
                break;
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chuangzuo:
                setDialog();
                break;
            case R.id.btn_open_camera:
                Intent intent1=new Intent(DaohangActivity.this,tupianActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_choose_img:
                Intent intent=new Intent(DaohangActivity.this,dongtuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cancel:
                Intent intent2=new Intent(DaohangActivity.this,manhuaActivity.class);
                startActivity(intent2);
                break;
            case R.id.quxiao:
                finish();
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
                    shouye.setChecked(true);
                    break;
                case PAGE_TWO:
                    shequ.setChecked(true);
                    break;
                case PAGE_THREE:
                    dongtai.setChecked(true);
                    break;
                case PAGE_FOUR:
                    wo.setChecked(true);
                    break;
            }
        }
    }
}