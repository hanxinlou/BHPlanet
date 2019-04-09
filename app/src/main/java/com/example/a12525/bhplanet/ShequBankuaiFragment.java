package com.example.a12525.bhplanet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShequBankuaiFragment extends Fragment implements View.OnClickListener {
    private ShequBankuaiFragment shequBankuaiFragment;
    private ShequTuijianFragment shequTuijianFragment;
    private Fragment[] fragments;
    private int lastfragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shequ_bankuai, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button)getActivity().findViewById(R.id.button);
        Button button2 = (Button)getActivity().findViewById(R.id.button2);
        Button button3 = (Button)getActivity().findViewById(R.id.button3);
        Button button4 = (Button)getActivity().findViewById(R.id.button4);
        Button button5 = (Button)getActivity().findViewById(R.id.button5);
        Button button6 = (Button)getActivity().findViewById(R.id.button6);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        replaceBoardFragment(new WodeBankuaiFragment());

        fragments = new Fragment[]{ shequBankuaiFragment, shequTuijianFragment };
        lastfragment = 0;
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.button:
                replaceBoardFragment(new WodeBankuaiFragment());
                break;
            case R.id.button2:
                replaceBoardFragment(new ManhuaFragment());
                break;
            case R.id.button3:
                replaceBoardFragment(new DoutuFragment());
                break;
            case R.id.button4:
                replaceBoardFragment(new YuanchuangFragment());
                break;
            case R.id.button5:
                replaceBoardFragment(new YingshiFragment());
                break;
            case R.id.button6:
                replaceBoardFragment(new ZizhiFragment());
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
//    private WodeBankuaiFragment wodeBankuaiFragment;
//    private ManhuaFragment manhuaFragment;
//    private DoutuFragment doutuFragment;
//    private YuanchuangFragment yuanchuangFragment;
//    private YingshiFragment yingshiFragment;
//    private ZizhiFragment zizhiFragment;
//    private Fragment[] fragments;
//    private int lastfragment;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.shequ_bankuai, container, false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        init();
//
//    }
//
//    private void init(){
//        Button button = (Button)getActivity().findViewById(R.id.button);
//        Button button2 = (Button)getActivity().findViewById(R.id.button2);
//        Button button3 = (Button)getActivity().findViewById(R.id.button3);
//        Button button4 = (Button)getActivity().findViewById(R.id.button4);
//        Button button5 = (Button)getActivity().findViewById(R.id.button5);
//        Button button6 = (Button)getActivity().findViewById(R.id.button6);
//        button.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
//        button5.setOnClickListener(this);
//        button6.setOnClickListener(this);
//
//        wodeBankuaiFragment = new WodeBankuaiFragment();
//        manhuaFragment = new ManhuaFragment();
//        doutuFragment = new DoutuFragment();
//        yuanchuangFragment = new YuanchuangFragment();
//        yingshiFragment = new YingshiFragment();
//        zizhiFragment = new ZizhiFragment();
//        fragments = new Fragment[]{ wodeBankuaiFragment, manhuaFragment, doutuFragment,
//                yuanchuangFragment, yingshiFragment, zizhiFragment};
//
//        lastfragment = 0;
//        getChildFragmentManager().beginTransaction().replace(R.id.community_board_content, wodeBankuaiFragment).show(wodeBankuaiFragment).commit();
//    }
//
//    @Override
//    public void onClick(View view){
//        int id = view.getId();
//        switch (id){
//            case R.id.button:
//                if(lastfragment != 0) {
//                    switchFragment(lastfragment, 0);
//                    lastfragment = 0;
//                }
//                break;
//            case R.id.button2:
//                if(lastfragment != 1) {
//                    switchFragment(lastfragment, 1);
//                    lastfragment = 1;
//                }
//                break;
//            case R.id.button3:
//                if(lastfragment != 2) {
//                    switchFragment(lastfragment, 2);
//                    lastfragment = 2;
//                }
//                break;
//            case R.id.button4:
//                if(lastfragment != 3) {
//                    switchFragment(lastfragment, 3);
//                    lastfragment = 3;
//                }
//                break;
//            case R.id.button5:
//                if(lastfragment != 4) {
//                    switchFragment(lastfragment, 4);
//                    lastfragment = 4;
//                }
//                break;
//            case R.id.button6:
//                if(lastfragment != 5) {
//                    switchFragment(lastfragment, 5);
//                    lastfragment = 5;
//                }
//                break;
//            default:
//                break;
//        }
//    }
//    private void switchFragment(int lastfragment, int index) {
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.hide(fragments[lastfragment]);
//        if(fragments[index].isAdded() == false) {
//            transaction.add(R.id.community_board_content,fragments[index]);
//        }
//        transaction.show(fragments[index]).commit();
//    }
}
