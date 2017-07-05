package com.max.helloandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.max.helloandroid.R;
import com.max.helloandroid.ui.base.BaseFragment;

/**
 * Created by WangHuaGui on 2017/7/4 13:56
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class NewsFragment extends BaseFragment {

    @Override
    public int setContent() {
        return R.layout.fragment_news;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
