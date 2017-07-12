package com.max.helloandroid.http;

import com.max.helloandroid.bean.NewsListBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by WangHuaGui on 2017/7/12 16:06
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class HttpMethods {
    private static HttpMethods httpMethods;

    public static HttpMethods getInstance() {
        if (httpMethods == null) {
            httpMethods = new HttpMethods();
        }
        return httpMethods;
    }

    public void loadNewsList(Subscriber<NewsListBean> subscriber, int startIndex) {
        Observable observable = RetrofitClient.createService(ApiService.class).getNewsList(startIndex)
                .map(new Func1<NewsListBean, NewsListBean>() {

                    @Override
                    public NewsListBean call(NewsListBean newsListBean) {
                        return newsListBean;
                    }
                });

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
