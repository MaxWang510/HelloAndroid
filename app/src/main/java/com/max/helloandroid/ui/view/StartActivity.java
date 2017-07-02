package com.max.helloandroid.ui.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.max.helloandroid.R;
import com.max.helloandroid.databinding.ActivityStartBinding;
import com.max.helloandroid.ui.base.BaseActivity;
import com.max.helloandroid.utils.AppConstants;
import com.max.helloandroid.utils.CommonUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WangHuaGui on 2017/6/23 14:20
 * E-Mail Address：wanghuagui@vtotem.com
 */

public class StartActivity extends BaseActivity {
    private ActivityStartBinding startBinding;
    private Timer mTimer;
    private boolean isIn;

    @Override
    protected void onCreateBinding() {
        startBinding = DataBindingUtil.setContentView(this, R.layout.activity_start);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    protected void initView() {
        initCancelButton(mTimer);
        initImageView(mTimer);
    }

    private void initImageView(Timer timer) {
        if (null != timer) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            int tempSecond = 3;

            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        CommonUtils.glideSetImgByURL(StartActivity.this, AppConstants.TRANSITION_URLS[tempSecond-1], startBinding.ivPicOne);
                        tempSecond--;
                        if (tempSecond == 0) {
                            return;
                        }
                    }
                });
            }
        }, 0, 1900);
    }

    private void initCancelButton(Timer timer) {
        if (null != timer) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            int curSecond = 5;

            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        startBinding.tvJump.setText(curSecond + "秒后关闭");
                        curSecond--;
                        if (curSecond == 0) {
                            enterMainActivity();
                        }
                    }
                });

            }
        }, 0, 1000);
    }

    private void enterMainActivity() {
        if (isIn) {
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        isIn = true;
        finish();
    }

    @Override
    protected void initEvent() {
        startBinding.tvJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
