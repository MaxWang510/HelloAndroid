package com.max.helloandroid.ui.view;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.max.helloandroid.R;
import com.max.helloandroid.adapter.MyFragmentPagerAdapter;
import com.max.helloandroid.databinding.ActivityMainBinding;
import com.max.helloandroid.ui.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mainBinding;
    private String[] fragmentTitles;
    private ArrayList<Fragment> fragmentList;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreateBinding() {
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }

    @Override
    protected void initData() {
        fragmentTitles = new String[]{"窗外", "悦读", "旋律", "眼界"};
        initFragments();
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager(), fragmentTitles, fragmentList);
    }

    @Override
    protected void initView() {
        initToolbar((Toolbar) mainBinding.includeToolbar.findViewById(R.id.toolbar), "主页");
        initViewPager();
    }

    private void initViewPager() {
        mainBinding.viewPager.setAdapter(myFragmentPagerAdapter);
        mainBinding.tabLayout.setupWithViewPager(mainBinding.viewPager);

        mainBinding.tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        mainBinding.tabLayout.getTabAt(1).setIcon(R.mipmap.ic_launcher);
        mainBinding.tabLayout.getTabAt(2).setIcon(R.mipmap.ic_launcher);
        mainBinding.tabLayout.getTabAt(3).setIcon(R.mipmap.ic_launcher);
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        NewsFragment newsFragment = new NewsFragment();
        ReadingFragment readingFragment = new ReadingFragment();
        MusicFragment musicFragment = new MusicFragment();
        VideoFragment videoFragment = new VideoFragment();

        fragmentList.add(newsFragment);
        fragmentList.add(readingFragment);
        fragmentList.add(musicFragment);
        fragmentList.add(videoFragment);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }

    private void initToolbar(Toolbar toolbar, String toolbarName) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back);
            actionBar.setTitle(toolbarName);
        }
    }
}
