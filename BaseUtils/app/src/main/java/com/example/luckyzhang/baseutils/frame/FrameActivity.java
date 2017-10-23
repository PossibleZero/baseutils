package com.example.luckyzhang.baseutils.frame;

import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;
import com.example.luckyzhang.baseutils.base.tools.IntentUtils;
import com.example.luckyzhang.baseutils.frame.tabactivity.TabActivity;

public class FrameActivity extends DemoBaseActivity {


    String[] datas = {"tab底部导航"};
    Class[] clazzs = {TabActivity.class};

    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                IntentUtils.goIntent(mContext, clazzs[position]);
                break;
        }

    }

    @Override
    public Class<?>[] getClazzes() {
        return clazzs;
    }


}
