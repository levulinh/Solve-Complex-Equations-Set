package com.mandevices.complexEquationsSet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by levul on 2/5/2017.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<MyFragment> fragments = new ArrayList<>();
    private MyFragment currentFragment;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(MyFragment.newInstance(0));
        fragments.add(MyFragment.newInstance(1));
        fragments.add(MyFragment.newInstance(2));
        fragments.add(MyFragment.newInstance(3));
        fragments.add(MyFragment.newInstance(4));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((MyFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public MyFragment getCurrentFragment() {
        return currentFragment;
    }
}
