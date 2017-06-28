package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.luckyzhang.baseutils.R;

import java.util.Random;

/**
 * Created by luckyzhang on 2017/6/28.
 */

public class DrawCodeView extends View {

    private int color;
    private int codeNumber;
    private Paint paint;
    private String mDatas = "2345";
    private Random random;

    public DrawCodeView(Context context) {
        this(context, null);
    }

    public DrawCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.code_view_styleable);
        color = typedArray.getColor(R.styleable.code_view_styleable_codeColor, getResources().getColor(R.color.color_333333));
        codeNumber = typedArray.getInt(R.styleable.code_view_styleable_codeNumber, 4);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(100);

        random = new Random();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        StringBuilder sb = setRandomCode();
        Rect textRect = getTextRect(sb.toString());
        int viewHeight = textRect.height();
        int viewWidth = textRect.width();
        int width = getRealWidth(widthMeasureSpec, viewWidth);
        int height = getRealHeight(heightMeasureSpec, viewHeight);
        setMeasuredDimension(width, height);
    }

    private int getRealHeight(int heightMeasureSpec, int viewHeight) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + viewHeight + getPaddingBottom();
        }
        return height;
    }

    private int getRealWidth(int widthMeasureSpec, int viewWidth) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + viewWidth + getPaddingRight();
        }
        return width;
    }

    public void setCodeNumber(int number) {
        this.codeNumber = number;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        paint.setColor(Color.WHITE);
        Rect rect = new Rect(0, 0, measuredWidth, measuredHeight);
        rect.inset(2, 2);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(color);

        //绘制N条线
        for (int i = 0; i < number; i++) {
            int x1 = random.nextInt(measuredWidth);
            int x2 = random.nextInt(measuredWidth);
            int y1 = random.nextInt(measuredHeight);
            int y2 = random.nextInt(measuredHeight);
            canvas.drawLine(x1, y1, x2, y2, paint);
        }

        paint.setColor(Color.RED);

        //绘制随机字
        StringBuilder sb = setRandomCode();
        Rect textRect = getTextRect(sb.toString());
        int x = measuredWidth / 2 - textRect.width() / 2;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int y = (int) (measuredHeight / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
        canvas.drawText(sb.toString(), x, y, paint);

    }

    private Rect getTextRect(String datas) {
        Rect rect = new Rect();
        paint.getTextBounds(datas, 0, datas.length(), rect);
        return rect;
    }

    private StringBuilder setRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codeNumber; i++) {
            int nextInt = new Random().nextInt(10);
            stringBuilder.append(nextInt);
        }
        return stringBuilder;
    }

    private int number = 10;

    public void setCount(int number) {
        this.number = number;
        setRandomCode();
        requestLayout();
    }
}
