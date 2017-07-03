package com.max.helloandroid.ui.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
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
    private ImageView imageView;
    private String thumbUrl = "http://att.191.cn/attachment/Mon_1012/63_5974_9775edfdf2ca75a.jpg";
    private Boolean isShowImages = false;

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
//        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[0], startBinding.ivPicOne);
//
//        new Handler().postDelayed(new Runnable() {//在当前线程（也即主线程中）开启一个消息处理器，并在3秒后在主线程中执行，从而来更新UI
//            @Override
//            public void run() {
//                //有关更新UI的代码
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(300);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        }, 1000);


//        hideImageView(startBinding.ivPicOne);
//        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[1], startBinding.ivPicTwo);
//        hideImageView(startBinding.ivPicTwo);
//        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[2], startBinding.ivPicThree);
//        showImageView(startBinding.ivPicThree);
//        hideImageView(startBinding.ivPicThree);
//        CommonUtils.glideSetImgByURL(this, AppConstants.TRANSITION_URLS[3], startBinding.ivPicFour);
//        showImageView(startBinding.ivPicFour);
//        hideImageView(startBinding.ivPicFour);
    }

    private void hideImageView(final ImageView imageview) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageview.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void showImageView(final ImageView imageview) {
        imageview.setVisibility(View.VISIBLE);
    }

    private void initCancelButton() {
        if (null != mTimer) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            int curSecond = 4;

            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        startBinding.tvJump.setText(curSecond + "秒后关闭");
                        CommonUtils.glideSetImgByURL(StartActivity.this, AppConstants.TRANSITION_URLS[curSecond - 1], startBinding.ivPicOne);
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
