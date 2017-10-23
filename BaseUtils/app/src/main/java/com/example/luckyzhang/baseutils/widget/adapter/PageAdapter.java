package com.example.luckyzhang.baseutils.widget.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;

/**
 * Created by luckyzhang on 2017/10/23.
 */

public class PageAdapter extends PagerAdapter {


    public TextView slideText;
    public ImageView arrowImage;
    Context mContext;
    int[] mImages = new int[]{};

    public PageAdapter(Context context, int[] images) {
        this.mContext = context;
        this.mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.length + 1; // 这里要加1，是因为多了一个隐藏的view
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position < mImages.length) {
            ImageView imageView = new ImageView(mContext);

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(300));
            imageView.setLayoutParams(lp);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImages[position]);

            container.addView(imageView);
            return imageView;
        } else {
            View hintView = LayoutInflater.from(container.getContext()).inflate(R.layout.item_viewpager_more_view, container, false);

            slideText = (TextView) hintView.findViewById(R.id.tv);
            arrowImage = (ImageView) hintView.findViewById(R.id.iv);

            container.addView(hintView);
            return hintView;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private int dip2px(float dipValue) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }
}
