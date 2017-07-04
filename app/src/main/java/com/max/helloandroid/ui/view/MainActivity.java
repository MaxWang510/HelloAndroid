package com.max.helloandroid.ui.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.max.helloandroid.R;
import com.max.helloandroid.databinding.ActivityMainBinding;
import com.max.helloandroid.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreateBinding() {
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }

    @Override
    protected void initView() {
        initToolbar((Toolbar) mainBinding.includeToolbar.findViewById(R.id.toolbar),"主页");
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
