package com.example.luckyzhang.baseutils.recyclerview.mutify;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {


    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(T item);
}
