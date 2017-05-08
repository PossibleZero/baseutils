package com.example.luckyzhang.baseutils;

import android.app.Application;

import com.example.luckyzhang.baseutils.retrofit.utils.RetrofitUtils;

/**
 * Created by luckyzhang on 2017/5/4.
 */

public class BaseApplication extends Application {

    private static BaseApplication application;

    public static BaseApplication getInstance() {
        if (application == null) {
            application = new BaseApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        RetrofitUtils.init();
    }

}
