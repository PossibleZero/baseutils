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
import com.example.luckyzhang.baseutils.recyclerview.SimpleTypeAdapter;
import com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_ONE;
import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_THREE;
import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_TWO;

public class SimpleTyp2Fragment extends Fragment {

    private int colors[] = {android.R.color.holo_blue_bright, android.R.color.black, android.R.color.holo_red_dark};


    View inflate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_mutify_typ2, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDatas();
    }

    private void initDatas() {
        Random random = new Random();
        List<DataTypeBase> dataTypeBases = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            DataTypeBase dataTypeBase = new DataTypeBase();
            setType(random.nextInt(3), dataTypeBase);
            dataTypeBases.add(dataTypeBase);

        }
        adapter.addDatas(dataTypeBases);
    }

    private void setType(int type, DataTypeBase dataTypeBase) {
        switch (type) {
            case 0:
                dataTypeBase.type = TYPE_ONE;
                dataTypeBase.avaterColor = colors[0];
                dataTypeBase.content = "type1";
                dataTypeBase.name = "type1";
                dataTypeBase.contentColor = colors[0];
                break;
            case 1:
                dataTypeBase.type = TYPE_TWO;
                dataTypeBase.avaterColor = colors[1];
                dataTypeBase.content = "type2";
                dataTypeBase.name = "type2";
                dataTypeBase.contentColor = colors[1];
                break;
            case 2:
                dataTypeBase.type = TYPE_THREE;
                dataTypeBase.avaterColor = colors[2];
                dataTypeBase.content = "type3";
                dataTypeBase.name = "type3";
                dataTypeBase.contentColor = colors[2];
                break;
        }
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private SimpleTypeAdapter adapter;

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        adapter = new SimpleTypeAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
