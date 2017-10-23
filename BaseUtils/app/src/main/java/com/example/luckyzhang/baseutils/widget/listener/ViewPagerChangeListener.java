package com.example.luckyzhang.baseutils.widget.listener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.adapter.PageAdapter;

/**
 * Created by luckyzhang on 2017/10/23.
 */

public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {


    int currPosition = 0; // 当前滑动到了哪一页
    boolean canJump = false;
    boolean canLeft = true;

    boolean isObjAnmatitor = true;
    boolean isObjAnmatitor2 = false;
    private PageAdapter mPagerAdaper;
    private Context mContext;
    private ViewPager mViewPager;
    private ImageView[] mImages;

    public ViewPagerChangeListener(Context context, ViewPager viewPager, ImageView[] imageVies) {
        this.mContext = context;
        this.mViewPager = viewPager;
        this.mImages = imageVies;
        this.mPagerAdaper = (PageAdapter) mViewPager.getAdapter();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == (mPagerAdaper.getCount() - 2)) { //设置当前的位置
            if (positionOffset > 0.1) {
                canJump = true;
                if (mPagerAdaper.arrowImage != null && mPagerAdaper.slideText != null) {
                    if (isObjAnmatitor) {
                        isObjAnmatitor = false;
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mPagerAdaper.arrowImage, "rotation", 0f, 180f);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mPagerAdaper.slideText.setText("松开跳到详情");
                                isObjAnmatitor2 = true;
                            }
                        });
                        animator.setDuration(500).start();
                    }
                }
            } else if (positionOffset <= 0.1 && positionOffset > 0) {
                canJump = false;
                if (mPagerAdaper.arrowImage != null && mPagerAdaper.slideText != null) {
                    if (isObjAnmatitor2) {
                        isObjAnmatitor2 = false;
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mPagerAdaper.arrowImage, "rotation", 180f, 360f);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mPagerAdaper.slideText.setText("继续滑动跳到详情");
                                isObjAnmatitor = true;
                            }
                        });
                        animator.setDuration(500).start();
                    }
                }
            }
            canLeft = false;
        } else {
            canLeft = true;
        }
    }

    @Override
    public void onPageSelected(int position) {
        currPosition = position;
        for (int i = 0; i < mImages.length; i++) {
            mImages[i].setImageResource(R.drawable.circle_black);
            if (i == position) {
                mImages[i].setImageResource(R.drawable.circle_red);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (currPosition == (mPagerAdaper.getCount() - 2) && !canLeft) {
            if (state == ViewPager.SCROLL_STATE_SETTLING) {

                if (canJump) {
                    Toast.makeText(mContext, "跳转啦", Toast.LENGTH_SHORT).show();
                }

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        // 在handler里调用setCurrentItem才有效
                        mViewPager.setCurrentItem(mPagerAdaper.getCount() - 2);
                    }
                });

            }
        }
    }
}
