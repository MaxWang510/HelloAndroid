package com.max.helloandroid;

import android.app.Application;

/**
 * Created by WangHuaGui on 2017/6/23 14:26
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class HelloAndroidApplication extends Application {

    private static HelloAndroidApplication helloAndroidApplication;

    /**
     * 获取Application实类
     *
     * @return
     */
    public static HelloAndroidApplication getInstance() {
        return helloAndroidApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        helloAndroidApplication = this;
    }
}
