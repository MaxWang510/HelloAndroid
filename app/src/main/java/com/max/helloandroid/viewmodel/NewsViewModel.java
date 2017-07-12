package com.max.helloandroid.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.max.helloandroid.bean.NewsListBean;
import com.max.helloandroid.http.HttpMethods;
import com.max.helloandroid.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


/**
 * Created by WangHuaGui on 2017/7/10 18:18
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class NewsViewModel {
    private Subscriber newsSubscriber;
    public final ObservableField<String> toastContent = new ObservableField<>("");
    public final ObservableInt showToast = new ObservableInt();
    public final ObservableInt hideLoadingView = new ObservableInt();
    public final ObservableInt showError = new ObservableInt();
    public List<NewsListBean.HeadLineBean> headLineBeanList;
    public List<NewsListBean.HeadLineBean> bannerHeadLineList;

    public NewsViewModel() {
        headLineBeanList = new ArrayList<NewsListBean.HeadLineBean>();
        bannerHeadLineList = new ArrayList<NewsListBean.HeadLineBean>();
    }

    /**
     * 加载新闻列表
     *
     * @param startIndex
     */
    public void loadNewsList(int startIndex) {
        LogUtil.print("***************999999");
        newsSubscriber = new Subscriber<NewsListBean>() {
            @Override
            public void onCompleted() {
                hideLoadingView.notifyChange();
            }

            @Override
            public void onError(Throwable e) {
                showError.notifyChange();
            }

            @Override
            public void onNext(NewsListBean newsListBean) {
                bannerHeadLineList.clear();
                headLineBeanList.clear();
                int newsSize = newsListBean.getHeadLineBean().size();
                for (int i = 0; i < newsSize; i++) {
                    NewsListBean.HeadLineBean headlineBean = newsListBean.getHeadLineBean().get(i);
                    if (null != headlineBean) {
                        headLineBeanList.add(headlineBean);
                    }
                }

                for (NewsListBean.HeadLineBean headLineBean : headLineBeanList) {
                    if (null != headLineBean.getAds() && headLineBean.getAds().size() > 0) {
                        bannerHeadLineList.add(headLineBean);
                        headLineBeanList.remove(headLineBean);
                    }
                }

                LogUtil.print("+++++++++++bannerHeadLineList.size=" + bannerHeadLineList.size());
                LogUtil.print("+++++++++++headLineBeanList.size=" + headLineBeanList.size());
            }
        };

        HttpMethods.getInstance().loadNewsList(newsSubscriber, startIndex);
    }
}
