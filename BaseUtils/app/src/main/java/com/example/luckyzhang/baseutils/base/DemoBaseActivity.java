package com.example.luckyzhang.baseutils.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luckyzhang.baseutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class DemoBaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.activity_list)
    ListView activityList;

    public Context mContext;

    private String[] datas;

    private Class<?>[] classDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = DemoBaseActivity.this;
        setContentView(R.layout.activity_demo_base);
//        activityList = (ListView) findViewById(R.id.activity_list);
        ButterKnife.bind(this);
        datas = initDatas();
        classDatas = getClazzes();
        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, R.layout.item_listview, datas);
        activityList.setAdapter(arrayAdapter);
        activityList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > datas.length) {
            return;
        }
        goIntentActivity(classDatas[position]);

    }

    public abstract Class<?>[] getClazzes();

    protected abstract String[] initDatas();

    public void goIntentActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        mContext.startActivity(intent);
    }


}
