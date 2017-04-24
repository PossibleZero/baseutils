package com.example.luckyzhang.baseutils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase;
import com.example.luckyzhang.baseutils.recyclerview.simple.SimpleTypeOneViewHolder;
import com.example.luckyzhang.baseutils.recyclerview.simple.SimpleTypeThreeViewHolder;
import com.example.luckyzhang.baseutils.recyclerview.simple.SimpleTypeTwoViewHolder;

import java.util.List;

import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_ONE;
import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_THREE;
import static com.example.luckyzhang.baseutils.recyclerview.simple.DataTypeBase.TYPE_TWO;

/**
 * Created by luckyzhang on 2017/4/24.
 * 单一的数据模型的设置
 */

public class SimpleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    LayoutInflater inflater;
    private List<DataTypeBase> mDataLists;


    public SimpleTypeAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<DataTypeBase> dataLists) {
        this.mDataLists = dataLists;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new SimpleTypeOneViewHolder(inflater.inflate(R.layout.item_type_one, parent, false));
            case TYPE_TWO:
                return new SimpleTypeTwoViewHolder(inflater.inflate(R.layout.item_type_two, parent, false));
            case TYPE_THREE:
                return new SimpleTypeThreeViewHolder(inflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_ONE:
                ((SimpleTypeOneViewHolder) holder).bindView(mDataLists.get(position));
                break;
            case TYPE_TWO:
                ((SimpleTypeTwoViewHolder) holder).bindView(mDataLists.get(position));
                break;
            case TYPE_THREE:
                ((SimpleTypeThreeViewHolder) holder).bindView(mDataLists.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDataLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataLists.get(position).type;
    }
}
