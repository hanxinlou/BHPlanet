package com.example.a12525.bhplanet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ShequBankuaiFragment extends Fragment implements View.OnClickListener {
    private List<Button>buttonList = new ArrayList<>();
    private Button button1, button2, button3, button4, button5, button6;
    private WodeBankuaiFragment fragment1;
    private ManhuaFragment fragment2;
    private DoutuFragment fragment3;
    private YuanchuangFragment fragment4;
    private YingshiFragment fragment5;
    private ZizhiFragment fragment6;
    private Fragment[] fragments;
    private int lastfragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_bankuai, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        button1 = (Button)view.findViewById(R.id.button);
        button2 = (Button)view.findViewById(R.id.button2);
        button3 = (Button)view.findViewById(R.id.button3);
        button4 = (Button)view.findViewById(R.id.button4);
        button5 = (Button)view.findViewById(R.id.button5);
        button6 = (Button)view.findViewById(R.id.button6);

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);

        for(Button button : buttonList){
            button.setOnClickListener(this);
        }

        fragment1 = new WodeBankuaiFragment();
        fragment2 = new ManhuaFragment();
        fragment3 = new DoutuFragment();
        fragment4 = new YuanchuangFragment();
        fragment5 = new YingshiFragment();
        fragment6 = new ZizhiFragment();
        fragments = new Fragment[]{ fragment1, fragment2, fragment3, fragment4, fragment5, fragment6 };
        lastfragment = 0;
        getChildFragmentManager().beginTransaction().replace(R.id.community_board_content, fragment1).show(fragment1).commitAllowingStateLoss();
        buttonList.get(0).setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.button:
                if(lastfragment != 0) {
                    switchFragment(lastfragment, 0);
                    buttonList.get(0).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 0;
                }

                break;
            case R.id.button2:
                if(lastfragment != 1) {
                    switchFragment(lastfragment,1);
                    buttonList.get(1).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 1;
                }
                break;
            case R.id.button3:
                if(lastfragment != 2) {
                    switchFragment(lastfragment,2);
                    buttonList.get(2).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 2;
                }
                break;
            case R.id.button4:
                if(lastfragment != 3) {
                    switchFragment(lastfragment,3);
                    buttonList.get(3).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 3;
                }
                break;
            case R.id.button5:
                if(lastfragment != 4) {
                    switchFragment(lastfragment,4);
                    buttonList.get(4).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 4;
                }
                break;
            case R.id.button6:
                if(lastfragment != 5) {
                    switchFragment(lastfragment,5);
                    buttonList.get(5).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    buttonList.get(lastfragment).setBackgroundColor(Color.parseColor("#e5e5e5"));
                    lastfragment = 5;
                }
                break;
            default:
                break;
        }
    }


    private void replaceBoardFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.community_board_content, fragment);
        transaction.commit();
    }

    private void switchFragment(int lastfragment, int index) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragments[lastfragment]);
        if(fragments[index].isAdded() == false) {
            transaction.add(R.id.community_board_content,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}
