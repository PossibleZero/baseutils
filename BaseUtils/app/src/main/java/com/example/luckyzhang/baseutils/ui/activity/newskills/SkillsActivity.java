package com.example.luckyzhang.baseutils.ui.activity.newskills;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;

public class SkillsActivity extends DemoBaseActivity {

    String[] datas = new String[]{"仿淘宝的一个查看公司详情的ViewPager", "自动滚动和手动的TimerViewPager"};
    Class<?>[] classDatas = new Class[]{PagerActivity.class, TimerPagerActivity.class};

    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public Class<?>[] getClazzes() {
        return classDatas;
    }
}
