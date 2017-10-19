package com.example.luckyzhang.baseutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.luckyzhang.baseutils.database.DBActivity;
import com.example.luckyzhang.baseutils.ui.activity.event.EventActivity;
import com.example.luckyzhang.baseutils.frame.FrameActivity;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.ui.MvpTestActivity;
import com.example.luckyzhang.baseutils.retrofit.ui.RetrofitActivity;
import com.example.luckyzhang.baseutils.ui.activity.CanvasActivity;
import com.example.luckyzhang.baseutils.ui.activity.MutifyRecyclerActivity;
import com.example.luckyzhang.baseutils.ui.activity.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String, Object>> datas;
    private Map<String, Object> maps;

    private Object[] strs = {RecyclerViewActivity.class, MutifyRecyclerActivity.class, MvpTestActivity.class, RetrofitActivity.class,
            FrameActivity.class, DBActivity.class, EventActivity.class, CanvasActivity.class};
    private String[] titleNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        gridView = (GridView) findViewById(R.id.grid_view);
        SimpleAdapter arrayAdapter = new SimpleAdapter(this, datas, R.layout.item_grid,
                new String[]{"title"}, new int[]{R.id.item_text});
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(this);
    }

    private void initData() {
        datas = new ArrayList<>();
        titleNames = MainActivity.this.getResources().getStringArray(R.array.main_titile_name);
        for (int i = 0; i < titleNames.length; i++) {
            maps = new HashMap<>();
            String str = "第" + i + "个";
            maps.put("title", titleNames[i]);
            datas.add(maps);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < strs.length) {
            goIntent((Class<?>) strs[position]);
        }
    }

    private void goIntent(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
