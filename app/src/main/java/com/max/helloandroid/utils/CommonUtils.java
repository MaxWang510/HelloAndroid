package com.max.helloandroid.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.max.helloandroid.HelloAndroidApplication;
import com.max.helloandroid.R;

/**
 * Created by WangHuaGui on 2017/6/26 11:15
 * E-Mail Address：wanghuagui@vtotem.com
 * 获取原生资源
 */

public class CommonUtils {

    /**
     * 显示Toast,防止多次创建
     *
     * @param string
     */
    public static void showToast(String string) {
        Toast toast = null;
        if (toast == null) {
            toast = Toast.makeText(HelloAndroidApplication.getInstance(), string, Toast.LENGTH_SHORT);
        } else {
            toast.setText(string);
        }
        toast.show();
    }

    /**
     * Glide显示图片
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void glideSetImgByURL(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.img_bg_default)
                .error(R.mipmap.img_bg_default)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
