package com.max.helloandroid.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.max.helloandroid.bean.NewsBean;
import com.max.helloandroid.http.HttpMethods;
import com.max.helloandroid.http.RequestCallBack;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


/**
 * Created by WangHuaGui on 2017/7/10 18:18
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class NewsViewModel {
    private Subscriber<NewsBean> newsSubscriber;
    public final ObservableField<String> toastContent = new ObservableField<>("");
    public final ObservableInt showToast = new ObservableInt();
    public final ObservableInt hideLoadingView = new ObservableInt();
    public final ObservableInt showError = new ObservableInt();
    public List<NewsBean.HeadLineBean> headLineBeanList;
    public List<NewsBean.HeadLineBean> bannerHeadLineList;

    public NewsViewModel() {
        headLineBeanList = new ArrayList<NewsBean.HeadLineBean>();
        bannerHeadLineList = new ArrayList<NewsBean.HeadLineBean>();
    }

    /**
     * 加载新闻列表
     *
     * @param startIndex
     */
    public void loadNews(int startIndex, final RequestCallBack requestListener) {
        newsSubscriber = new Subscriber<NewsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                requestListener.loadFailed();
            }

            @Override
            public void onNext(NewsBean newsBean) {
                requestListener.loadSuccess(newsBean);
            }

        };
        requestListener.addSubscription(newsSubscriber);
        HttpMethods.getInstance().loadNews(newsSubscriber, startIndex);
    }
}
