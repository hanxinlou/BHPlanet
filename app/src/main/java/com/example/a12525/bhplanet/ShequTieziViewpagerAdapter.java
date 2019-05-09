package com.example.a12525.bhplanet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

public class ShequTieziViewpagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 2;
    private ZuixinFragment zuixinFragment;
    private ZuireFragment  zuireFragment;

    public ShequTieziViewpagerAdapter(FragmentManager fm) {
        super(fm);
        zuixinFragment = new ZuixinFragment();
        zuireFragment = new ZuireFragment();

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
            case ShequTieziActivity.PAGE_ONE:
                fragment = zuixinFragment;
                break;
            case ShequTieziActivity.PAGE_TWO:
                fragment = zuireFragment;
                break;
        }
        return fragment;
    }

}