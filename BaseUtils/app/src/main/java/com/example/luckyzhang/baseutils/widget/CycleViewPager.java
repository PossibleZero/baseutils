package com.example.luckyzhang.baseutils.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luckyzhang on 2017/10/23.
 * 无线循环的ViewPager
 */

public class CycleViewPager extends ViewPager {


    private InnerPagerAdaper mAdaper;

    public CycleViewPager(Context context) {
        super(context);
    }

    public CycleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        this.mAdaper = new InnerPagerAdaper(adapter);
        super.setAdapter(mAdaper);
        setCurrentItem(1);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        super.setOnPageChangeListener(new InnerPageChangeListener(listener));
    }

    private class InnerPageChangeListener implements OnPageChangeListener {
        private OnPageChangeListener listener;
        private int pos;

        public InnerPageChangeListener(OnPageChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (listener != null) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

        }

        @Override
        public void onPageSelected(int position) {
            this.pos = position;
            if (listener != null) {
                listener.onPageSelected(position);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (listener != null) {
                listener.onPageScrollStateChanged(state);
            }
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (pos == mAdaper.getCount() - 1) { //最后一个
                    setCurrentItem(1, false);
                } else if (pos == 0) { //第一个
                    setCurrentItem(mAdaper.getCount() - 2, false);
                }
            }

        }
    }

    private class InnerPagerAdaper extends PagerAdapter {

        private PagerAdapter adapter;

        public InnerPagerAdaper(PagerAdapter adapter) {
            this.adapter = adapter;
            adapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getCount() {
            return adapter.getCount() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return adapter.isViewFromObject(view, object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) { //第一个位置
                position = adapter.getCount() - 1;
            } else if (position == adapter.getCount() + 1) { //最后一个位置
                position = 0;
            } else { //其他显示的位置
                position -= 1;
            }
            return adapter.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            adapter.destroyItem(container, position, object);
        }
    }
}
