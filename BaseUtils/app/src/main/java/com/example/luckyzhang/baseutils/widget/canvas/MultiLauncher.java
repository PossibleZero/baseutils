package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by luckyzhang on 2017/6/29.
 */

public class MultiLauncher extends ViewGroup {
    public MultiLauncher(Context context) {
        this(context, null);
    }

    public MultiLauncher(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    private Scroller scroller;
    int scaledTouchSlop;

    private void initView(Context context) {
        scroller = new Scroller(context);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("不允许wrap_content");
        } else if (mode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        return width * getChildCount();
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("不允许wrap_content");
        } else if (mode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        return height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = (r - l) / count; //屏幕宽度
        int height = b - t; //屏幕高度
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            int left = width * i;
            int right = width * (i + 1);
            int top = 0;
            int bottom = height;
            view.layout(left, top, right, bottom);
        }

    }

    private static final int FLY_STATUS = 0x001;
    private static final int STOP_STATUS = 0x002;
    private int touchStatus = STOP_STATUS;
    private int lastX;

    //状态有两种一个是停止、一个是滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        if (action == MotionEvent.ACTION_MOVE && touchStatus == STOP_STATUS) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                touchStatus = scroller.isFinished() ? STOP_STATUS : FLY_STATUS;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - lastX);
                if (dx > scaledTouchSlop) {
                    touchStatus = FLY_STATUS;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touchStatus = STOP_STATUS;
                break;
        }

        return touchStatus != STOP_STATUS;
    }


    int mCurrentScreen = 0;

    public void moveToScreen(int whichScreen) {
        mCurrentScreen = whichScreen;
        if (mCurrentScreen > getChildCount() - 1) {
            mCurrentScreen = getChildCount() - 1;
        }
        if (mCurrentScreen < 0) {
            mCurrentScreen = 0;
        }

        int scrollX = getScrollX();
        //每屏幕宽度
        int splitWidth = getWidth() / getChildCount();
        //要移动的距离
        int dx = mCurrentScreen * splitWidth - scrollX;
        scroller.startScroll(scrollX, 0, dx, 0, Math.abs(dx));
        postInvalidate();

    }

    /**
     * 应该移动的位置
     */
    public void moveToDestination() {
        //每一屏幕宽度
        int splitWidth = getWidth() / getChildCount();
        //判断是进入下一屏幕还是回滚
        int toScreen = (getScrollX() + splitWidth / 2) / splitWidth;
        //移动到目标分屏幕
        moveToScreen(toScreen);
    }

    /**
     * 向下一个移动
     */
    public void moveToNext() {
        moveToScreen(mCurrentScreen + 1);
    }

    /**
     * 向上一个移动
     */
    public void moveToPre() {
        moveToScreen(mCurrentScreen - 1);
    }

    VelocityTracker tracker;
    private static final int SNAP_VELOCITY = 600;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (tracker == null) {
            tracker = VelocityTracker.obtain();
        }
        tracker.addMovement(event);
        super.onTouchEvent(event);

        int action = event.getAction();

        int x = (int) event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //手指按压下时,如果正在滚动,则停止
                if (scroller != null && !scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                lastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                //随手指滚动
                int dx = lastX - x;
                scrollBy(dx, 0);
                lastX = x;
                break;
            case MotionEvent.ACTION_UP:
                VelocityTracker velocityTracker = this.tracker;
                velocityTracker.computeCurrentVelocity(1000);
                int xVelocity = (int) velocityTracker.getXVelocity();
                if (xVelocity > SNAP_VELOCITY && mCurrentScreen > 0) {
                    moveToPre();
                } else if (xVelocity < -SNAP_VELOCITY && mCurrentScreen < (getChildCount() - 1)) {
                    moveToNext();
                } else {
                    moveToDestination();
                }

                if (tracker != null) {
                    tracker.clear();
                    tracker.recycle();
                    tracker = null;
                }
                touchStatus = STOP_STATUS;

                break;
            case MotionEvent.ACTION_CANCEL:
                touchStatus = STOP_STATUS;
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }


}
