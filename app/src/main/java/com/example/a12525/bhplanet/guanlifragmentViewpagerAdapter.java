package com.example.a12525.bhplanet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
public class guanlifragmentViewpagerAdapter  extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 2;
    private guanliFragment  guanliFragment= null;
    private guanliFragment2  guanliFragment2 = null;



    public guanlifragmentViewpagerAdapter(FragmentManager fm) {
        super(fm);
        guanliFragment = new guanliFragment();
        guanliFragment2 = new guanliFragment2();
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
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case guanliActivity.PAGE_ONE:
                fragment = guanliFragment;
                break;
            case guanliActivity.PAGE_TWO:
                fragment = guanliFragment2;
                break;
        }
        return fragment;
    }

}