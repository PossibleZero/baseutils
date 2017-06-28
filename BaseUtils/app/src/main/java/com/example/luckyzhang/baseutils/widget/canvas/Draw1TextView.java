package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.luckyzhang.baseutils.tools.LogUtils;

/**
 * Created by luckyzhang on 2017/6/27.
 */

public class Draw1TextView extends View {

    private Paint paint;

    private static final String DATA = "luckyzhang是棒棒哒!";

    public Draw1TextView(Context context) {
        this(context, null);
    }

    public Draw1TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(80);
        paint.setColor(Color.RED);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Rect textRect = getTextRect();
        int textWidth = textRect.width();
        int textHeight = textRect.height();
        int realWidth = getRealWidth(widthMeasureSpec, textWidth);
        int realHeight = getRealHeight(heightMeasureSpec, textHeight);
        setMeasuredDimension(realWidth, realHeight);

        LogUtils.d("onMeasure");
    }

    private int getRealHeight(int heightMeasureSpec, int textHeight) {
        int width = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = textHeight;
        }
        return width;
    }

    private int getRealWidth(int widthMeasureSpec, int textWidth) {
        int heighth = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            heighth = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            heighth = textWidth;
        }
        return heighth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect textRect = getTextRect();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float y = measuredHeight / 2 - textRect.height() / 2 + (descent - ascent) / 2 - descent;
        int x = measuredWidth / 2 - textRect.width() / 2;
        canvas.drawText(DATA, x, y, paint);
        LogUtils.d("onDraw");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.d("onLayout");

    }

    private Rect getTextRect() {
        Rect rect = new Rect();
        paint.getTextBounds(DATA, 0, DATA.length(), rect);
        return rect;
    }


}
