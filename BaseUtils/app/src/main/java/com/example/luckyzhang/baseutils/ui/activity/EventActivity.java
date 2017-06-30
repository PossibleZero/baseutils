package com.example.luckyzhang.baseutils.ui.activity;

import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;
import com.example.luckyzhang.baseutils.ui.activity.eventActivity.Scroller1Activity;

public class EventActivity extends DemoBaseActivity {

    private String[] datas = new String[]{"Scroller应用"};
    private Class<?>[] clazz = new Class<?>[]{Scroller1Activity.class,};

    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > datas.length) {
            return;
        }
        goIntentActivity(clazz[position]);

    }
}
