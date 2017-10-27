package com.example.luckyzhang.baseutils.ui.activity.newskills;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.CycleTimerViewPage;

import java.util.ArrayList;
import java.util.List;

public class TimerPagerActivity extends AppCompatActivity {

    private CycleTimerViewPage viewPage;
    List<Integer> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_pager);
        viewPage = (CycleTimerViewPage) findViewById(R.id.viewpage);
        datas.add(R.mipmap.i1);
        datas.add(R.mipmap.i2);
        datas.add(R.mipmap.i3);
        viewPage.setImageResource(datas, null);
    }
}
