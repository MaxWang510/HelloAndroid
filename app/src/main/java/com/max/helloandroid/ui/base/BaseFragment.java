package com.max.helloandroid.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.max.helloandroid.R;
import com.max.helloandroid.ui.customview.CustomProgressBar;
import com.max.helloandroid.utils.CommonUtils;
import com.max.helloandroid.utils.NoRepeatClickListener;

public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {
    // 布局view
    protected SV bindingView;
    // 内容布局
    protected RelativeLayout mContainer;
    private CustomProgressBar mCustomProgressBar;
    private LinearLayout mErrorRefresh;
    // fragment是否显示了
    protected boolean mIsVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = (RelativeLayout) ll.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        return ll;
    }

    /**
     * 在这里实现Fragment数据的懒缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {

    }

    protected void onVisible() {
        loadData();
    }

    protected void loadData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCustomProgressBar = getView(R.id.loading_progress_bar);
        mErrorRefresh = getView(R.id.ll_layout_error_refresh);
        setOnErrorRefreshListener(mErrorRefresh);
        showLoading();
    }

    private void setOnErrorRefreshListener(LinearLayout errorRefresh) {
        errorRefresh.setOnClickListener(new NoRepeatClickListener() {
            @Override
            protected void onNoRepeatClick(View v) {
                showLoading();
                onRefresh();
            }
        });
    }

    /**
     * 加载失败后刷新
     */
    protected void onRefresh() {

    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 布局
     */
    public abstract int setContent();

    public void showToast(String string) {
        CommonUtils.showToast(string);
    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (mCustomProgressBar.getVisibility() != View.VISIBLE) {
            mCustomProgressBar.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (mErrorRefresh.getVisibility() != View.GONE) {
            mErrorRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 显示内容
     */
    protected void showContent() {
        if (mCustomProgressBar.getVisibility() != View.GONE) {
            mCustomProgressBar.setVisibility(View.GONE);
        }

        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
        if (mErrorRefresh.getVisibility() != View.GONE) {
            mErrorRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showLoadingError() {
        if (mCustomProgressBar.getVisibility() != View.GONE) {
            mCustomProgressBar.setVisibility(View.GONE);
        }
        if (mErrorRefresh.getVisibility() != View.VISIBLE) {
            mErrorRefresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }
}
