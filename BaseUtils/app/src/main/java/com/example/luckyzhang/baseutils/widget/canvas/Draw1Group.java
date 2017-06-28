package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by luckyzhang on 2017/6/28.
 */

public class Draw1Group extends ViewGroup {

    private Paint paint;

    public Draw1Group(Context context) {
        this(context, null);
    }

    public Draw1Group(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Draw1Group(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAlpha(Color.alpha(120));
        TextView textView = new TextView(context);
        textView.setText("添加");
        textView.setBackgroundColor(Color.YELLOW);
        LayoutParams layoutParams = new LayoutParams(250, 250);
        addView(textView, layoutParams);
        setBackgroundColor(Color.alpha(255));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        TextView textView = (TextView) getChildAt(0);
        textView.layout(20, 20, textView.getMeasuredWidth(), 20 + textView.getMeasuredHeight() + 20);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(500, 500);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        rect.inset(2, 2);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(2);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.addRoundRect(rect, 20, 20, Path.Direction.CCW);
        canvas.drawPath(path, p);

    }
}
