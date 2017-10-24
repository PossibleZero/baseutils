package com.example.luckyzhang.baseutils.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.luckyzhang.baseutils.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by luckyzhang on 2017/10/23.
 * 设置自定义ViewPager
 */

public class TimerViewPage extends LinearLayout {

    private CycleViewPager mCycleViewPager;
    private LinearLayout mViewGroup; //底部的滚动的圆点
    private ImageView[] mImageViews; //保存的图片
    private Context mContext;
    private int mImageIndex = 1; //图片滚动当前图片下标

    public TimerViewPage(Context context) {
        this(context, null);
    }

    public TimerViewPage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerViewPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.widget_timer_page_adapter, this);
        mCycleViewPager = (CycleViewPager) findViewById(R.id.pager_banner);
        mViewGroup = (LinearLayout) findViewById(R.id.viewGroup);
        mCycleViewPager.setOnPageChangeListener(new PageChangeListener());
        mCycleViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        startImageTimerTask();
                        break;
                    default:
                        stopImageTimerTask();
                        break;

                }
                return false;
            }
        });

    }

    private Handler mHandler = new Handler();

    /**
     * 移除定时事件
     */
    private void stopImageTimerTask() {
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 开启定时任务
     */
    private void startImageTimerTask() {
        stopImageTimerTask();
        mHandler.postDelayed(mRunnable, 3000);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mImageViews != null) {
                if (mImageIndex == mImageViews.length) {
                    mImageIndex = 1;
                    mCycleViewPager.setCurrentItem(mImageIndex, false);
                    startImageTimerTask();
                } else {
                    mImageIndex++;
                    mCycleViewPager.setCurrentItem(mImageIndex, true);
                }
            }

        }
    };


    /**
     * 设置图片资源
     *
     * @param lists
     * @param cycleViewListener
     */
    public void setImageResource(List<?> lists, ImageCycleViewListener cycleViewListener) {
        mViewGroup.removeAllViews();
        int size = lists.size();
        mImageViews = new ImageView[size];
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            mImageViews[i] = imageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.circle_red);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.circle_black);
            }
            mViewGroup.addView(imageView);
        }
        CyclePageAdapter adapter = new CyclePageAdapter(mContext, lists, cycleViewListener);
        mCycleViewPager.setAdapter(adapter);
        startImageTimerTask();
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0 || position == mImageViews.length + 1) {
                return;
            }
            mImageIndex = position;
            position -= 1;
            mImageViews[position].setBackgroundResource(R.drawable.circle_red);
            for (int i = 0; i < mImageViews.length; i++) {
                if (position != i) {
                    mImageViews[i].setBackgroundResource(R.drawable.circle_black);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                startImageTimerTask(); //开始下次计时
            }
        }
    }


    private class CyclePageAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<ImageView> cacheList; //缓存列表
        private List<?> mAdList = new ArrayList<>(); //数据
        private ImageCycleViewListener listener; //回调接口

        public CyclePageAdapter(Context context, List<?> datas, ImageCycleViewListener cycleViewListener) {
            this.context = context;
            this.mAdList = datas;
            this.listener = cycleViewListener;
            this.cacheList = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return mAdList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = null;
            if (cacheList.isEmpty()) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                imageView = cacheList.remove(0);
            }
            final Object o = mAdList.get(position);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onImageClick(o, position, v);
                    }
                }
            });
            imageView.setImageResource((Integer) o);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            container.removeView(view);
            cacheList.add(view);
        }
    }


    public interface ImageCycleViewListener {
        void onImageClick(Object o, int pos, View imageView);
    }


}
