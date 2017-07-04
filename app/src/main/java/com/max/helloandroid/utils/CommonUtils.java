package com.max.helloandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.max.helloandroid.R;

/**
 * Created by WangHuaGui on 2017/6/26 11:15
 * E-Mail Address：wanghuagui@vtotem.com
 * 获取原生资源
 */

public class CommonUtils {

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
