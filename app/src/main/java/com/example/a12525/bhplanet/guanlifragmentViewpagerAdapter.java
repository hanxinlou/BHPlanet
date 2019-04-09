package com.example.a12525.bhplanet;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class guanlifragmentViewpagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    public guanlifragmentViewpagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
