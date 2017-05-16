package com.example.luckyzhang.baseutils.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.DemoRvAdapter;
import com.example.utils.recylerview.recyler.recyler.DefaultFootItem;
import com.example.utils.recylerview.recyler.recyler.OnLoadMoreListener;
import com.example.utils.recylerview.recyler.recyler.RecyclerViewWithFooter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Integer> mDatas;
    private RecyclerViewWithFooter mRecyclerViewWithFooter;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mRecyclerViewWithFooter = (RecyclerViewWithFooter) findViewById(R.id.recyclerView);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        initData();

        mRecyclerViewWithFooter.setAdapter(new DemoRvAdapter(this, mDatas));
//        mRecyclerViewWithFooter.setStaggeredGridLayoutManager(2);
        mRecyclerViewWithFooter.setFootItem(new DefaultFootItem());//默认是这种
//        mRecyclerViewWithFooter.setFootItem(new CustomFootItem());//自定义
        mRecyclerViewWithFooter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerViewWithFooter.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();
                    }
                }, 2000);
            }
        });

    }

    protected void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(R.mipmap.cat1);
        mDatas.add(R.mipmap.cat2);
//        mDatas.add(R.mipmap.cat3);
//        mDatas.add(R.mipmap.cjj);
//        mDatas.add(R.mipmap.cat1);
//        mDatas.add(R.mipmap.cat2);
    }

    protected void addData() {
        mDatas.add(R.mipmap.cat1);
        mDatas.add(R.mipmap.cat2);
        mDatas.add(R.mipmap.cat3);
        mDatas.add(R.mipmap.cjj);
        mDatas.add(R.mipmap.cat1);
        mDatas.add(R.mipmap.cat2);
        mRecyclerViewWithFooter.getAdapter().notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addMoreAction:
                mRecyclerViewWithFooter.setLoading();
                addData();
                return true;
            case R.id.endAction:
                mRecyclerViewWithFooter.setEnd("没有更多数据了");
                return true;
            case R.id.emptyAction:
                mDatas.clear();
                mRecyclerViewWithFooter.setEmpty("没有数据", R.mipmap.ic_launcher);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
