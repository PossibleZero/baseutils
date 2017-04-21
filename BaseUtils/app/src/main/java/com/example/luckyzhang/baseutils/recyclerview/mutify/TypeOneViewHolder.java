package com.example.luckyzhang.baseutils.recyclerview.mutify;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public class TypeOneViewHolder extends TypeAbstractViewHolder<ItemTypeOne> {

    private ImageView avater;
    private TextView name;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    @Override
    public void bindView(ItemTypeOne item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
    }
}
