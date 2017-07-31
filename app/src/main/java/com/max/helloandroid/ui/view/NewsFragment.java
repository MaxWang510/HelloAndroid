package com.max.helloandroid.ui.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.max.helloandroid.R;
import com.max.helloandroid.adapter.NewsListAdapter;
import com.max.helloandroid.bean.NewsBean;
import com.max.helloandroid.databinding.BannerBinding;
import com.max.helloandroid.databinding.FragmentNewsBinding;
import com.max.helloandroid.http.RequestCallBack;
import com.max.helloandroid.ui.base.BaseFragment;
import com.max.helloandroid.utils.LogUtil;
import com.max.helloandroid.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import rx.Subscription;

/**
 * Created by WangHuaGui on 2017/7/4 13:56
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class NewsFragment extends BaseFragment<FragmentNewsBinding> implements BGABanner.Adapter<ImageView, String>, BGABanner.Delegate<ImageView, String>, NewsListAdapter.OnItemClickListener, XRecyclerView.LoadingListener {
    private BannerBinding mHeaderBinding;
    private View mHeaderView;
    private boolean mIsPrepared = false;
    private NewsViewModel mNewsViewModel;
    private List<NewsBean.HeadLineBean> mHeadLineList;
    private List<NewsBean.HeadLineBean> mLoadMoreHeadLineList;
    private List<NewsBean.HeadLineBean> mBannerList;
    private List<String> bannerImages;
    private List<String> bannerTitles;
    private NewsListAdapter mNewsListAdapter;
    private int mStartIndex = 0;    // 请求数据的起始参数
    private Boolean isFreshOrLoadError = false;


    @Override
    public int setContent() {
        return R.layout.fragment_news;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsViewModel = new NewsViewModel();
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.banner, null, false);
        initRecyclerView();
        mIsPrepared = true;
        addCallbacks();
        /**
         * 因为启动时先走loadData()再走onActivityCreated，
         * 所以此处要额外调用load(),不然最初不会加载内容
         */
        loadData();
    }

    private void addCallbacks() {
        mNewsViewModel.showToast.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                showToast(mNewsViewModel.toastContent.get());
            }
        });

        mNewsViewModel.hideLoadingView.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                showContent();
            }
        });

        mNewsViewModel.showError.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                showLoadingError();
            }
        });
    }

    private void initRecyclerView() {
        if (mHeaderView == null) {
            mHeaderView = mHeaderBinding.getRoot();
        }
        bindingView.newsRecyclerView.addHeaderView(mHeaderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingView.newsRecyclerView.setLayoutManager(layoutManager);
        //上拉刷新，下拉加载
        bindingView.newsRecyclerView.setLoadingListener(this);
        bindingView.newsRecyclerView.setPullRefreshEnabled(true);
        bindingView.newsRecyclerView.setLoadingMoreEnabled(true);
        // 需加，不然滑动不流畅
        bindingView.newsRecyclerView.setNestedScrollingEnabled(false);
        bindingView.newsRecyclerView.setHasFixedSize(false);
        bindingView.newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bindingView.newsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
        bindingView.newsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (!mIsVisible || !mIsPrepared) {
            return;
        }

        if (null != mHeaderBinding && null != mHeaderBinding.headerBanner) {
            mHeaderBinding.headerBanner.setAdapter(this);
            mHeaderBinding.headerBanner.setDelegate(this);
        }

        loadNews();
    }

    private void loadNews() {
        mNewsViewModel.loadNews(0, new RequestCallBack() {
            @Override
            public void loadSuccess(Object object) {
                if (isFreshOrLoadError) {
                    bindingView.newsRecyclerView.refreshComplete();
                    bindingView.newsRecyclerView.loadMoreComplete();
                }
                showContent();
                NewsBean mNewsBean = (NewsBean) object;
                if (null != mNewsBean) {
                    setNewsList(mNewsBean);
                    setAdapter();
                    initBannerImage();
                }
            }

            @Override
            public void loadFailed() {
                showLoadingError();
            }

            @Override
            public void addSubscription(Subscription subscription) {
                NewsFragment.this.addSubscription(subscription);
            }
        });
    }

    private void setNewsList(NewsBean mNewsBean) {
        int size = mNewsBean.getHeadLineBean().size();
        if (null == mHeadLineList) {
            mHeadLineList = new ArrayList<NewsBean.HeadLineBean>();
        } else {
            mHeadLineList.clear();
        }

        for (int i = 0; i < size; i++) {
            NewsBean.HeadLineBean headLineBean = mNewsBean.getHeadLineBean().get(i);
            if (null != headLineBean) {
                mHeadLineList.add(headLineBean);
            }
        }

        if (null != mHeadLineList && mHeadLineList.size() > 0) {
            for (NewsBean.HeadLineBean headLineBean : mHeadLineList) {
                if (null != headLineBean && null != headLineBean.getAds() && headLineBean.getAds().size() > 0) {
                    if (null == mBannerList) {
                        mBannerList = new ArrayList<NewsBean.HeadLineBean>();
                    } else {
                        mBannerList.clear();
                    }
                    mBannerList.add(headLineBean);
                }
            }
        }

    }

    private void setAdapter() {
        mNewsListAdapter = new NewsListAdapter(getActivity(), mHeadLineList);
        mNewsListAdapter.setOnItemClickListener(this);
        bindingView.newsRecyclerView.setAdapter(mNewsListAdapter);
        bindingView.newsRecyclerView.refresh();
    }

    private void initBannerImage() {
        bannerImages = new ArrayList<>();
        bannerTitles = new ArrayList<>();
        bannerImages.clear();
        bannerTitles.clear();

        for (int i = 0; i < mBannerList.size(); i++) {
            NewsBean.HeadLineBean headLine = mBannerList.get(i);
            if (null != headLine && null != headLine.getAds() && headLine.getAds().size() > 0) {
                for (int j = 0; j < headLine.getAds().size(); j++) {
                    bannerImages.add(headLine.getAds().get(j).getImgsrc());
                    bannerTitles.add(headLine.getAds().get(j).getTitle());
                }
            }
        }
        mHeaderBinding.headerBanner.setData(bannerImages, bannerTitles);
        mHeaderBinding.headerBanner.startAutoPlay();
    }

    @Override
    protected void onRefreshInError() {
        super.onRefreshInError();
        loadData();
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(this)
                .load(model)
                .placeholder(R.mipmap.im_default_bg)
                .error(R.mipmap.im_default_bg)
                .dontAnimate()
                .centerCrop()
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        LogUtil.print("+++++++++++++banner:" + position);
    }

    /**
     * 每条新闻的点击事件
     *
     * @param v
     * @param position
     */
    @Override
    public void onItemClick(View v, int position) {
        LogUtil.print("+++++++++++++" + position);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != mHeaderBinding.headerBanner) {
            mHeaderBinding.headerBanner.stopAutoPlay();
        }
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        if (null != mHeaderBinding && null != mHeaderBinding.headerBanner) {
            mHeaderBinding.headerBanner.stopAutoPlay();
        }
    }

    /**
     * 下来刷新
     */
    @Override
    public void onRefresh() {
        mNewsViewModel.loadNews(0, new RequestCallBack() {
            @Override
            public void loadSuccess(Object object) {
                showContent();
                bindingView.newsRecyclerView.refreshComplete();
                NewsBean mNewsBean = (NewsBean) object;
                if (null != mNewsBean) {
                    setNewsList(mNewsBean);
                    mNewsListAdapter.notifyDataSetChanged();
                    mHeaderBinding.headerBanner.setData(bannerImages, bannerTitles);
                    mHeaderBinding.headerBanner.startAutoPlay();
                }
            }

            @Override
            public void loadFailed() {
                isFreshOrLoadError = true;
                showLoadingError();
            }

            @Override
            public void addSubscription(Subscription subscription) {
                NewsFragment.this.addSubscription(subscription);
            }
        });
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        mStartIndex += 20;
        mNewsViewModel.loadNews(mStartIndex, new RequestCallBack() {
            @Override
            public void loadSuccess(Object object) {
                showContent();
                bindingView.newsRecyclerView.loadMoreComplete();
                NewsBean mNewsBean = (NewsBean) object;

                if (null != mNewsBean) {
                    setLoadMoreNewsList(mNewsBean);
                    if (null != mHeadLineList && mHeadLineList.size() > 0) {
                        mHeadLineList.addAll(mLoadMoreHeadLineList);
                        mNewsListAdapter.notifyDataSetChanged();
                        mHeaderBinding.headerBanner.setData(bannerImages, bannerTitles);
                        mHeaderBinding.headerBanner.startAutoPlay();
                    }
                }
            }

            @Override
            public void loadFailed() {
                isFreshOrLoadError = true;
                showLoadingError();

            }

            @Override
            public void addSubscription(Subscription subscription) {
                NewsFragment.this.addSubscription(subscription);
            }
        });

    }

    private void setLoadMoreNewsList(NewsBean mNewsBean) {
        int size = mNewsBean.getHeadLineBean().size();
        if (null == mLoadMoreHeadLineList) {
            mLoadMoreHeadLineList = new ArrayList<NewsBean.HeadLineBean>();
        } else {
            mLoadMoreHeadLineList.clear();
        }

        for (int i = 0; i < size; i++) {
            NewsBean.HeadLineBean headLineBean = mNewsBean.getHeadLineBean().get(i);
            if (null != headLineBean) {
                mLoadMoreHeadLineList.add(headLineBean);
            }
        }

        if (null != mLoadMoreHeadLineList && mLoadMoreHeadLineList.size() > 0) {
            for (NewsBean.HeadLineBean headLineBean : mLoadMoreHeadLineList) {
                if (null != headLineBean && null != headLineBean.getAds() && headLineBean.getAds().size() > 0) {
                    if (null == mBannerList) {
                        mBannerList = new ArrayList<NewsBean.HeadLineBean>();
                    } else {
                        mBannerList.clear();
                    }
                    mBannerList.add(headLineBean);
                }
            }
        }
    }
}
