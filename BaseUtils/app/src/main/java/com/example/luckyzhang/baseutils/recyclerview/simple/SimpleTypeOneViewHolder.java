package com.example.luckyzhang.baseutils.recyclerview.simple;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.mutify.TypeAbstractViewHolder;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public class SimpleTypeOneViewHolder extends TypeAbstractViewHolder<DataTypeBase> {

    private ImageView avater;
    private TextView name;

    public SimpleTypeOneViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    @Override
    public void bindView(DataTypeBase item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
    }
}
