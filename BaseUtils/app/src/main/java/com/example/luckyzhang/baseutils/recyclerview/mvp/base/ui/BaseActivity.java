package com.example.luckyzhang.baseutils.recyclerview.mvp.base.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by luckyzhang on 2017/4/26.
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        init();
    }

    protected abstract void init();

    protected abstract int getContentLayoutId();


}
