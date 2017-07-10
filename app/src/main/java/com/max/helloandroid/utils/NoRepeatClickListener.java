package com.max.helloandroid.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Created by WangHuaGui on 2017/7/10 15:10
 * E-Mail Address：wanghuagui@vtotem.com
 * 防止在一分钟内多次点击
 */

public abstract class NoRepeatClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = v.getId();
        if (id != mId) {
            id = mId;
            lastClickTime = currentTime;
            onNoRepeatClick(v);
            return;
        }
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoRepeatClick(v);
        }
    }

    protected abstract void onNoRepeatClick(View v);
}
