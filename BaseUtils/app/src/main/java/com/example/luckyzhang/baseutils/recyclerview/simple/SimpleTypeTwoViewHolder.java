package com.example.luckyzhang.baseutils.recyclerview.simple;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.mutify.TypeAbstractViewHolder;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public class SimpleTypeTwoViewHolder extends TypeAbstractViewHolder<DataTypeBase> {

    private ImageView avater;
    private TextView name;
    private TextView content;

    public SimpleTypeTwoViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void bindView(DataTypeBase item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
        content.setText(item.content);
    }
}
