package com.example.a12525.bhplanet;



import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class jianpan extends AppCompatActivity {

    private LinearLayout mLayout;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);

        mLayout = (LinearLayout) findViewById(R.id.layout);
        mEdit = (EditText) findViewById(R.id.et_discuss);
    }

    public void onButtonClick(View view){
        switch (view.getId()){
            case R.id.pinglun:
                ShowKeyboard();
                break;
            case R.id.tv_confirm:
                hideKeyboard();
                Toast.makeText(jianpan.this, "评论成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayout:
               hideKeyboard();
                break;
        }
    }

    //显示布局与键盘
    private void ShowKeyboard(){
        mLayout.setVisibility(View.VISIBLE);//显示布局
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
    //隐藏键盘与布局
    private void hideKeyboard(){
        mLayout.setVisibility(View.GONE);//隐藏布局
        mEdit.setText("");//清空输入
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 捕获返回键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //隐藏键盘与布局
        hideKeyboard();
        return true;
    }

}

