package com.example.luckyzhang.baseutils.event;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;

public class EventActivity extends DemoBaseActivity {

    String[] datas = {"内部拦截", "外部拦截"};

    Object[] clazzes = {OutterInterActivity.class};


    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (clazzes.length <= position) {
            return;
        }

        goIntent((Class<?>) clazzes[position]);

    }


    private void goIntent(Class<?> clazz) {
        Intent intent = new Intent(EventActivity.this, clazz);
        startActivity(intent);
    }
}
