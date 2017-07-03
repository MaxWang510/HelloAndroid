package com.max.helloandroid.ui.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

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
        setWindowFlags();
        onCreateBinding();
        initView();
        initEvent();
        loadData();
        addActivity(this);
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

    /**
     * 设置窗体属性（如：无titlebar等）
     */
    public void setWindowFlags() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
