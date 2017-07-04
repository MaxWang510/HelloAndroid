package com.max.helloandroid.ui.view;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.max.helloandroid.R;
import com.max.helloandroid.adapter.MyFragmentPagerAdapter;
import com.max.helloandroid.databinding.ActivityMainBinding;
import com.max.helloandroid.ui.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar
        .OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private ActivityMainBinding mainBinding;
    private ArrayList<Fragment> fragmentList;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreateBinding() {
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
    }

    @Override
    protected void initView() {
        initToolbar((Toolbar) mainBinding.includeToolbar.findViewById(R.id.toolbar), "主页");
        initViewPager();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        mainBinding.bottomNavigationBar.setTabSelectedListener(this);
        mainBinding.bottomNavigationBar.clearAll();
        mainBinding.bottomNavigationBar.setActiveColor(R.color.colorNavigationBar);
        mainBinding.bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mainBinding.bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mainBinding.bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mainBinding.bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ico_news_normal, getString(R.string.news)))
                .addItem(new BottomNavigationItem(R.mipmap.ico_reading_normal, getString(R.string.reading)))
                .addItem(new BottomNavigationItem(R.mipmap.ico_music_normal, getString(R.string.music)))
                .addItem(new BottomNavigationItem(R.mipmap.ico_video_normal, getString(R.string.video)))
                .initialise();
    }

    private void initViewPager() {
        initFragments();
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        mainBinding.viewPager.setAdapter(myFragmentPagerAdapter);
        mainBinding.viewPager.addOnPageChangeListener(this);
        mainBinding.viewPager.setCurrentItem(0);
    }


    private void initFragments() {
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mainBinding.bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(int position) {
        mainBinding.viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
