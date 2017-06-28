package com.example.luckyzhang.baseutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.tools.LogUtils;

/**
 * Created by luckyzhang on 2017/6/14.
 */

public class TestViewGroup extends ViewGroup {
    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.test_view_style);
        int viewColor = typedArray.getColor(R.styleable.test_view_style_viewColor, getResources().getColor(R.color.color_333333));
        int viewWidth = typedArray.getInt(R.styleable.test_view_style_viewwidth, 11);
        LogUtils.d("viewColor:" + viewColor + "  viewWidth:" + viewWidth);

        typedArray.recycle();
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        context.obtainStyledAttributes(attrs,R.styleable.test_view_style,defStyleAttr,R.style.AppTheme);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interceptTag = false;
        float x = ev.getX();
        float y = ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
