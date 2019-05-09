package com.example.a12525.bhplanet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

public class ShequFragmentViewpagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 2;
    private ShequBankuaiFragment  shequBankuaiFragment;
    private ShequTuijianFragment  shequTuijianFragment;



    public ShequFragmentViewpagerAdapter(FragmentManager fm) {
        super(fm);
        shequBankuaiFragment = new ShequBankuaiFragment();
        shequTuijianFragment = new ShequTuijianFragment();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("ndxq","position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case ShequFragment.PAGE_ONE:
                fragment = shequBankuaiFragment;
                break;
            case ShequFragment.PAGE_TWO:
                fragment = shequTuijianFragment;
                break;
        }
        return fragment;
    }

}