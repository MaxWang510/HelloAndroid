package com.max.helloandroid.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHuaGui on 2017/6/23 14:06
 * E-Mail Address：maxwang510@163.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        onCreateBinding();
        initView();
        initEvent();
        loadData();
    }

    protected abstract void onCreateBinding();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void loadData();

    @Override
    protected void onPause() {
        super.onPause();
        removeActivity(this);
    }


    // 添加Activity到容器中
    private void addActivity(Activity activity) {
        if (activity != null && !activities.contains(activity)) {
            activities.add(activity);
        }
    }

    private void removeActivity(Activity activity) {
        if (activity != null && activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    // 退出整个APP
    public static void exit() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
