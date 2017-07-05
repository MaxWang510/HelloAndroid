package com.max.helloandroid.utils;

import android.util.Log;

/**
 * Created by WangHuaGui on 2017/7/5 11:09
 * E-Mail Address：wanghuagui@vtotem.com
 * 用法：
 * LogUtil.print("");
 * LogUtil.print("你好");
 * LogUtil.print("net", "baidu.com");
 *
 * LogUtil.print(LEVEL, "你好啊");==>LEVEL = {Log.VERBOSE,Log.DEBUG,Log.INFO,Log.WARN,Log.ERROR,Log.ASSERT}
 */

public class LogUtil {
    private static int LEVEL = Log.DEBUG;//默认level
    private static String TAG = "MaxWang";//默认tag
    private static boolean IS_DEBUG = true;//默认显示log

    //设置默认的Level
    public static void setDefaultLevel(int level) {
        LogUtil.LEVEL = level;
    }

    //设置默认的TAG
    public static void setDefaultTag(String tag) {
        LogUtil.TAG = tag;
    }

    //设置是否是Debug模式
    public static void setIsDebug(boolean isDebug) {
        LogUtil.IS_DEBUG = isDebug;
    }

    //打印
    public static void print(String msg) {
        performPrint(LEVEL, TAG, msg);
    }

    //打印-自定义Tag
    public static void print(String tag, String msg) {
        performPrint(LEVEL, tag, msg);
    }

    //打印-自定义Level
    public static void print(int level, String msg) {
        performPrint(level, TAG, msg);
    }

    //打印-自定义Tag,自定义Level
    public static void print(int level, String tag, String msg) {
        performPrint(level, tag, msg);
    }

    //执行打印
    private static void performPrint(int level, String tag, String msg) {
        //非Debug版本，则不打印日志
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(level, tag, threadName + " " + lineIndicator + " " + msg);
    }

    //获取行所在的方法指示
    private static String getLineIndicator() {
        //3代表方法的调用深度：0-getLineIndicator，1-performPrint，2-print，3-调用该工具类的方法位置
        StackTraceElement element = ((new Exception()).getStackTrace())[3];
        StringBuffer sb = new StringBuffer("(")
                .append(element.getFileName()).append(":")
                .append(element.getLineNumber()).append(").")
                .append(element.getMethodName()).append(":");
        return sb.toString();
    }
}
