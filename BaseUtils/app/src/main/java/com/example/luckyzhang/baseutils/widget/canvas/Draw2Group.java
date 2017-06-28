package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luckyzhang on 2017/6/28.
 */

public class Draw2Group extends ViewGroup {
    public Draw2Group(Context context) {
        this(context, null);
    }

    public Draw2Group(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int realWidth = getRealWidth(widthMeasureSpec);
        int realHeight = getRealHeight(heightMeasureSpec);
        setMeasuredDimension(realWidth, realHeight);
    }

    private int getRealHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aHeight = 0;
            int bHeight = 0;
            int cHeight = 0;
            int dHeight = 0;
            for (int i = 0; i < getChildCount(); i++) {
                if (i == 0) {
                    aHeight = getChildAt(i).getMeasuredHeight();
                } else if (i == 1) {
                    bHeight = getChildAt(i).getMeasuredHeight();
                } else if (i == 2) {
                    cHeight = getChildAt(i).getMeasuredHeight();
                } else if (i == 3) {
                    dHeight = getChildAt(i).getMeasuredHeight();
                }
            }
            height = Math.max(aHeight, bHeight) + Math.max(cHeight, dHeight) + getPaddingTop() + getPaddingBottom();
        }
        return height;
    }

    private int getRealWidth(int widthMeasureSpec) {
        int width = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            int aWidth = 0;
            int bWidth = 0;
            int cWidth = 0;
            int dWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                if (i == 0) {
                    aWidth = getChildAt(i).getMeasuredWidth();
                } else if (i == 1) {
                    bWidth = getChildAt(i).getMeasuredWidth();
                } else if (i == 2) {
                    cWidth = getChildAt(i).getMeasuredWidth();
                } else if (i == 3) {
                    dWidth = getChildAt(i).getMeasuredWidth();
                }
            }
            width = Math.max(aWidth, cWidth) + Math.max(bWidth, dWidth) + getPaddingLeft() + getPaddingRight();

        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == 0) {
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
            } else if (i == 1) {
                child.layout(getMeasuredWidth() - child.getMeasuredWidth() - right, top, getMeasuredWidth() - right, child.getMeasuredHeight() + top);
            } else if (i == 2) {
                child.layout(left, getMeasuredHeight() - child.getMeasuredHeight() - bottom, child.getMeasuredWidth() + left, getMeasuredHeight() - bottom);
            } else if (i == 3) {
                child.layout(getMeasuredWidth() - child.getMeasuredWidth() - right, getMeasuredHeight() - child.getMeasuredHeight() - bottom, getMeasuredWidth() - right, getMeasuredHeight() - bottom);
            }

        }

    }
}
