package com.max.helloandroid.http;

import rx.Subscription;

/**
 * Author: WangHuaGui
 * Date: 2017/7/13 10:40
 * Describe: 网络请求的回调
 */

public interface RequestCallBack {
    void loadSuccess(Object object);

    void loadFailed();

    void addSubscription(Subscription subscription);

}
