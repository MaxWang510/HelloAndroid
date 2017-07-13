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

public class NewsFragment extends BaseFragment<FragmentNewsBinding> implements BGABanner.Adapter<ImageView, String>, BGABanner.Delegate<ImageView, String>, NewsListAdapter.OnItemClickListener {
    private BannerBinding mHeaderBinding;
    private View mHeaderView;
    private boolean mIsPrepared = false;
    private NewsViewModel mNewsViewModel;
    private NewsBean mNewsBean;
    private List<NewsBean.HeadLineBean> mHeadLineList;
    private List<NewsBean.HeadLineBean> mBannerList;
    private List<String> bannerImages;
    private List<String> bannerTitles;
    private NewsListAdapter mNewsListAdapter;


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
        bindingView.newsRecyclerView.setPullRefreshEnabled(false);
        bindingView.newsRecyclerView.setLoadingMoreEnabled(false);
        if (mHeaderView == null) {
            mHeaderView = mHeaderBinding.getRoot();
            bindingView.newsRecyclerView.addHeaderView(mHeaderView);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingView.newsRecyclerView.setLayoutManager(layoutManager);
        // 需加，不然滑动不流畅
        bindingView.newsRecyclerView.setNestedScrollingEnabled(false);
        bindingView.newsRecyclerView.setHasFixedSize(false);
        bindingView.newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bindingView.newsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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
                showContent();
                mNewsBean = (NewsBean) object;

                int size = mNewsBean.getHeadLineBean().size();
                if (null == mHeadLineList) {
                    mHeadLineList = new ArrayList<NewsBean.HeadLineBean>();
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
                            }
                            mBannerList.add(headLineBean);
                        }
                    }
                }
                setAdapter();
                initBannerImage();
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
                    LogUtil.print("bannerImages:" + headLine.getAds().get(j).getImgsrc());
                    LogUtil.print("bannerTitles:" + headLine.getAds().get(j).getTitle());
                }
            }
        }
        mHeaderBinding.headerBanner.setData(bannerImages, bannerTitles);
        mHeaderBinding.headerBanner.startAutoPlay();
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
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
}
