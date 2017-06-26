package com.max.helloandroid.ui.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
        initCancelButton();
        initImageView();
    }

    private void initImageView() {
        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[0], startBinding.ivPicOne);
        Log.i("maxwang", "initImageView: "+AppConstants.TRANSITION_URLS[0]);
        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[1], startBinding.ivPicTwo);
        hideImageView(startBinding.ivPicTwo);
        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[2], startBinding.ivPicThree);
        hideImageView(startBinding.ivPicThree);
        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[3], startBinding.ivPicFour);
        hideImageView(startBinding.ivPicFour);
    }

    private void hideImageView(final ImageView imageview) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageview.setVisibility(View.GONE);
            }
        }, 1500);
    }

    private void initCancelButton() {
        if (null != mTimer) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            int curSecond = 10;

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
                Log.i("maxwang", "onClick: ++++++++++");
                enterMainActivity();
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
