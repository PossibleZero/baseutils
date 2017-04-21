package com.example.luckyzhang.baseutils.recyclerview.mutify;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;

/**
 * Created by luckyzhang on 2017/4/20.
 */

public class TypeTwoViewHolder extends TypeAbstractViewHolder<ItemTypeTwo> {

    private ImageView avater;
    private TextView name;
    private TextView content;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void bindView(ItemTypeTwo item) {
        avater.setBackgroundResource(item.avaterColor);
        name.setText(item.name);
        content.setText(item.content);
    }
}
