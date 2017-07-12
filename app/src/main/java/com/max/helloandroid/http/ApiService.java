package com.max.helloandroid.http;

import com.max.helloandroid.bean.NewsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by WangHuaGui on 2017/7/11 18:39
 * 网络接口
 */

public interface ApiService {
    /**
     * 获取新闻列表
     */
    @GET("nc/article/headline/T1348647909107/{startIndex}-20.html")
    Observable<NewsListBean> getNewsList(@Path("startIndex") int startIndex);

}
