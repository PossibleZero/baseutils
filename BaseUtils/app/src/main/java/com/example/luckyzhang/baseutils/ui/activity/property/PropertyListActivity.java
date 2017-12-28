package com.example.luckyzhang.baseutils.ui.activity.property;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;

public class PropertyListActivity extends DemoBaseActivity {


    String[] datas = new String[]{"属性动画1", "属性动画demo2"};
    Class<?>[] classDatas = new Class[]{PropertyActivity.class, ViewFlipperActivity.class,
    };

    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public Class<?>[] getClazzes() {
        return classDatas;
    }
}
