package com.example.luckyzhang.baseutils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by luckyzhang on 2017/6/19.
 */

public class LinearScrollView extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker obtain;


    public LinearScrollView(Context context) {
        super(context);
        initView(context);
    }

    public LinearScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        scroller = new Scroller(context);
        obtain = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = measureWidth(widthMeasureSpec);
        int measureHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
        /*int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        } else {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
*/
    }

    private int measureHeight(int widthMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        View chidleView = getChildAt(0);
        if (mode == MeasureSpec.AT_MOST) {
            height = chidleView.getMeasuredHeight();
        } else {
            height = size;
        }
        return height;
    }

    private int measureWidth(int heightMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        View childView = getChildAt(0);
        int childCount = getChildCount();
        if (mode == MeasureSpec.AT_MOST) {
            width = childView.getMeasuredWidth() * childCount;
        } else {
            width = size;
        }
        return width;
    }

    int mChildWidth = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        mChildrenSize = getChildCount();
        for (int i = 0; i < mChildrenSize; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                int childViewMeasuredWidth = childView.getMeasuredWidth();
                int childViewMeasuredHeight = childView.getMeasuredHeight();
                mChildWidth = childViewMeasuredWidth;
                childView.layout(childLeft, 0, childLeft + childViewMeasuredWidth, childViewMeasuredHeight);
                childLeft += childViewMeasuredWidth;
            }
        }

    }


    int mLastXInercept;
    int mLastYInercept;
    int mChildrenSize;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;

        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastXInercept;
                float deltY = y - mLastYInercept;
                if (Math.abs(deltaX) > Math.abs(deltY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }

        mLastX = (int) x;
        mLastY = (int) y;
        mLastXInercept = (int) x;
        mLastYInercept = (int) y;
        return intercepted;

    }

    int mLastX = 0;
    int mLastY = 0;
    int mChildIndex;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        obtain.addMovement(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastX;
                float deltaY = y - mLastY;
                scrollBy((int) -deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollChildIndex = scrollX / mChildWidth;
                obtain.computeCurrentVelocity(1000);
                float xVelocity = obtain.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                obtain.clear();
                break;
        }
        mLastX = (int) x;
        mLastY = (int) y;
        return true;

    }

    private void smoothScrollBy(int dx, int dy) {
        scroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

}
