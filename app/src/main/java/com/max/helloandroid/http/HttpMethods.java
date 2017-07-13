package com.max.helloandroid.http;

import com.max.helloandroid.bean.NewsBean;

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

    public void loadNews(Subscriber<NewsBean> subscriber, int startIndex) {
        Observable observable = RetrofitClient.createService(ApiService.class).getNews(startIndex)
                .map(new Func1<NewsBean, NewsBean>() {

                    @Override
                    public NewsBean call(NewsBean newsBean) {
                        return newsBean;
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
