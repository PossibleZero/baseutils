package com.example.luckyzhang.baseutils.ui.activity.annotation;

import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;

public class AnnotationActivity extends DemoBaseActivity {


    String[] datas = {"@IntDef页面"};
    Class<?>[] clazzes = {};

    @Override
    protected String[] initDatas() {
        return new String[0];
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (clazzes.length <= position) {
            return;
        }
        goIntentActivity(clazzes[position]);

    }

    @Override
    public Class<?>[] getClazzes() {
        return clazzes;
    }
}
