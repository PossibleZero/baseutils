package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Created by luckyzhang on 2017/6/29.
 */

public class Scroller1Group extends ViewGroup {
    public Scroller1Group(Context context) {
        this(context, null);
    }

    public Scroller1Group(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private Scroller scroller;

    private void initView(Context context) {
        scroller = new Scroller(context);
        Button textView = new Button(context);
        textView.setText("自定义View");
        textView.setTextColor(Color.RED);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.addView(textView, layoutParams);
//        setBackgroundColor(context.getResources().getColor(R.color.color_999999));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY &&
                MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);

            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view = getChildAt(0);
        view.layout(20, 20, 20 + view.getMeasuredWidth(), 20 + view.getMeasuredHeight());

    }

    public void startScroll() {
        scroller.startScroll(getScrollX(), getScrollY(), -900, 0, 10000);
        postInvalidate();
    }

    public void abort() {
        scroller.abortAnimation();
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
