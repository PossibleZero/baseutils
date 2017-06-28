package com.example.luckyzhang.baseutils.ui.activity;

import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CanvasGroup1Activity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CanvasGroup2Activity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CanvasTextActivity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CavasCodeActivity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CavasLineActivity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CavasPathActivity;
import com.example.luckyzhang.baseutils.ui.activity.canvasActivity.CavasRectActivity;

public class CanvasActivity extends DemoBaseActivity {

    String[] datas = new String[]{"line绘制", "path绘制", "rect绘制-采用双缓存", "text绘制", "code绘制",
            "group绘制", "group高级绘制"};
    Class<?>[] classDatas = new Class<?>[]{
            CavasLineActivity.class, CavasPathActivity.class, CavasRectActivity.class, CanvasTextActivity.class,
            CavasCodeActivity.class, CanvasGroup1Activity.class, CanvasGroup2Activity.class};

    @Override
    protected String[] initDatas() {
        return datas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > datas.length) {
            return;
        }
        goIntentActivity(classDatas[position]);


    }


}
