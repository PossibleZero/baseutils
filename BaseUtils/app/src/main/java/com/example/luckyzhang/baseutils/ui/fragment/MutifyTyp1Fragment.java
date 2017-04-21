package com.example.luckyzhang.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.MutifyTypeAdapter;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeOne;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeThree;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeTwo;
import com.example.utils.recylerview.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MutifyTyp1Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    View inflate;
    private int colors[] = {android.R.color.holo_blue_bright, android.R.color.black, android.R.color.holo_red_dark};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.activity_recycler_view, container, false);
        return inflate;
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewWithFooter;
    private MutifyTypeAdapter mutifyTypeAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe);
        recyclerViewWithFooter = (RecyclerView) inflate.findViewById(R.id.recyclerView);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerViewWithFooter.setLayoutManager(new LinearLayoutManager(getActivity()));
        mutifyTypeAdapter = new MutifyTypeAdapter(getActivity());
        recyclerViewWithFooter.setAdapter(mutifyTypeAdapter);
        initData();

    }

    private void initData() {
        /*List<Item> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Item item = new Item();
            //产生1-3的随机数
            int type = (int) (Math.random() * 3 + 1);
            //Log.e(TAG, "initData: random---"+ (int) (Math.random() * 3 + 1));
            item.type = type;
            item.avaterColor = colors[type - 1];
            item.content = "content:" + type;
            item.contentColor = colors[type - 1];
            item.name = "name:" + type;
            items.add(item);
        }
        adpter.addList(items);
        //即使不要下面这句也能正常初始化recyclerview
        adpter.notifyDataSetChanged();*/

        List<ItemTypeOne> itemOnes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ItemTypeOne itemOne = new ItemTypeOne();

            itemOne.avaterColor = colors[0];
            itemOne.name = "name:" + 1;
            itemOnes.add(itemOne);
        }

        List<ItemTypeTwo> itemTwos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemTypeTwo itemTwo = new ItemTypeTwo();

            itemTwo.avaterColor = colors[1];
            itemTwo.name = "name:" + 2;
            itemTwo.content = "content:" + 2;
            itemTwos.add(itemTwo);
        }

        List<ItemTypeThree> itemThrees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemTypeThree itemThree = new ItemTypeThree();

            itemThree.avaterColor = colors[2];
            itemThree.name = "name:" + 3;
            itemThree.content = "content:" + 3;
            itemThree.contentColor = colors[2];
            itemThrees.add(itemThree);
        }

        mutifyTypeAdapter.addDatas(itemOnes, itemTwos, itemThrees);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
