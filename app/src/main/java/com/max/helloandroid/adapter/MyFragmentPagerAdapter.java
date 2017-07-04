package com.max.helloandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by WangHuaGui on 2017/7/4 13:46
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] fragmentTitles;
    private ArrayList<Fragment> fragmentList;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] fragmentTitles, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentTitles = fragmentTitles;
        this.fragmentList = fragmentList;
    }

    public void updateData(String[] fragmentTitles, ArrayList<Fragment> fragmentList) {
        this.fragmentTitles = fragmentTitles;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles[position];
    }
}
