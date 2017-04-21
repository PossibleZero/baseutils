package com.example.luckyzhang.baseutils.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeOne;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeThree;
import com.example.luckyzhang.baseutils.recyclerview.mutify.ItemTypeTwo;
import com.example.luckyzhang.baseutils.recyclerview.mutify.TypeOneViewHolder;
import com.example.luckyzhang.baseutils.recyclerview.mutify.TypeThreeViewHolder;
import com.example.luckyzhang.baseutils.recyclerview.mutify.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public class MutifyTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ONE = 0x01;
    private static final int TYPE_TWO = 0x02;
    private static final int TYPE_THREE = 0x03;

    LayoutInflater inflater;
    private Context mContext;

    private List<ItemTypeOne> itemTypeOnes = new ArrayList<>();
    private List<ItemTypeTwo> itemTypeTwos = new ArrayList<>();
    private List<ItemTypeThree> itemTypeThrees = new ArrayList<>();

    private List<Integer> typeLists = new ArrayList<>(); //所有的个数

    private Map<Integer, Integer> mPosions = new HashMap<>();//存放类型和个数

    public MutifyTypeAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }


    public void addDatas(List<ItemTypeOne> itemTypeOnes, List<ItemTypeTwo> itemTypeTwos,
                         List<ItemTypeThree> itemTypeThrees) {
        this.itemTypeOnes = itemTypeOnes;
        this.itemTypeTwos = itemTypeTwos;
        this.itemTypeThrees = itemTypeThrees;

        addItemByType(TYPE_ONE, itemTypeOnes);
        addItemByType(TYPE_TWO, itemTypeTwos);
        addItemByType(TYPE_THREE, itemTypeThrees);
        notifyDataSetChanged();


    }

    private void addItemByType(int type, List list) {
        mPosions.put(type, typeLists.size()); //存放的是上一个zhuang't
        for (int i = 0; i < list.size(); i++) {
            typeLists.add(type);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new TypeOneViewHolder(inflater.inflate(R.layout.item_type_one, parent, false));
            case TYPE_TWO:
                return new TypeTwoViewHolder(inflater.inflate(R.layout.item_type_two, parent, false));
            case TYPE_THREE:
                return new TypeThreeViewHolder(inflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    switch (itemViewType) {
                        case TYPE_ONE:
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        case TYPE_TWO:
                        case TYPE_THREE:
                            return 1;
                    }
                    return 0;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);//获取当前的类型
        int realPos = position - mPosions.get(itemViewType); //通过类型获取当前的真实位置(每个数据的位置-起始类型的位置)
        switch (itemViewType) {
            case TYPE_ONE:
                ((TypeOneViewHolder) holder).bindView(itemTypeOnes.get(realPos));
                break;
            case TYPE_TWO:
                ((TypeTwoViewHolder) holder).bindView(itemTypeTwos.get(realPos));
                break;
            case TYPE_THREE:
                ((TypeThreeViewHolder) holder).bindView(itemTypeThrees.get(realPos));
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return typeLists.get(position);
    }

    @Override
    public int getItemCount() {
        return typeLists.size();
    }
}
