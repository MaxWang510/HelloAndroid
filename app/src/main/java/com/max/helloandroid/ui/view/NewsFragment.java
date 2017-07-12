package com.max.helloandroid.ui.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.max.helloandroid.R;
import com.max.helloandroid.databinding.BannerBinding;
import com.max.helloandroid.databinding.FragmentNewsBinding;
import com.max.helloandroid.ui.base.BaseFragment;
import com.max.helloandroid.utils.LogUtil;
import com.max.helloandroid.viewmodel.NewsViewModel;

/**
 * Created by WangHuaGui on 2017/7/4 13:56
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class NewsFragment extends BaseFragment<FragmentNewsBinding> {
    private BannerBinding mHeaderBinding;
    private View mHeaderView;
    private boolean mIsPrepared = false;
    private NewsViewModel mNewsViewModel;

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
//        bindingView.newsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        bindingView.newsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (!mIsVisible || !mIsPrepared) {
            return;
        }
        loadNewsList();
    }

    private void loadNewsList() {
        mNewsViewModel.loadNewsList(0);
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        mNewsViewModel.loadNewsList(0);
    }
}
