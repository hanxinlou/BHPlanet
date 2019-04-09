package com.example.a12525.bhplanet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShequFragment extends Fragment implements View.OnClickListener {
    private ShequBankuaiFragment shequBankuaiFragment;
    private ShequTuijianFragment shequTuijianFragment;
    private Fragment[] fragments;
    private int lastfragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shequ, container, false);
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
        shequBankuaiFragment = new ShequBankuaiFragment();
        shequTuijianFragment = new ShequTuijianFragment();

        fragments = new Fragment[]{ shequBankuaiFragment, shequTuijianFragment };
        lastfragment = 0;

        bottomNavigationView = (BottomNavigationView)getActivity().findViewById(R.id.community_title_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceBoardFragment(new WodeBankuaiFragment());
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

    private void switchFragment(int lastfragment, int index) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragments[lastfragment]);
        if(fragments[index].isAdded() == false) {
            transaction.add(R.id.community_layout,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.navigation_title_board_list:
//                    if(lastfragment != 0) {
//                        switchFragment(lastfragment,0);
//                        lastfragment = 0;
//                    }
                    return true;
                case R.id.navigation_title_recommend:
                    if(lastfragment != 1) {
                        switchFragment(lastfragment,1);
                        lastfragment = 1;
                    }
                    return true;
            }
            return false;
        }
    };
}
