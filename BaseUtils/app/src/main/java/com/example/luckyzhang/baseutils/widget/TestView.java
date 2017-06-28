package com.example.luckyzhang.baseutils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;

/**
 * Created by luckyzhang on 2017/6/14.
 */

public class TestView extends View {
    Paint paint;
    float[] points = new float[]{
            20, 30,
            20, 40
    };

    float[] lines = new float[]{
            30, 30, 80, 30,
            40, 40, 80, 40,
            50, 50, 80, 50,
    };

    private Context mContext;
    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        this.mContext =context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //绘制基础
        canvas.drawPoint(10, 10, paint);

        canvas.drawPoints(points, paint);

        canvas.drawLine(20, 20, 50, 20, paint);

        canvas.drawLines(lines, paint);

        Rect rect = new Rect(30, 20, 50, 60);
        canvas.drawRect(rect, paint);

        RectF rectF = new RectF(40, 50, 20, 80);
        canvas.drawRoundRect(rectF, 20, 40, paint);

        canvas.drawOval(rectF, paint);

        canvas.drawCircle(50, 50, 50, paint);

        canvas.drawArc(rectF, 0, 90, false, paint);

        canvas.scale(0.5f,0.5f);

        try {
            mContext.getResources().getAssets().open("");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
