package com.example.luckyzhang.baseutils.widget.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by luckyzhang on 2017/6/26.
 */

public class Draw2View extends View {

    private Paint paint;
    private Bitmap bufferBitmap;
    private Canvas bufferCanvas;
    private Path path;

    public Draw2View(Context context) {
        this(context, null);
    }

    public Draw2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        if (bufferBitmap == null) {
            bufferBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
            bufferCanvas = new Canvas(bufferBitmap);
        }
    }

    int preX;
    int preY;
    int lastX;
    int lastY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                preX = x;
                preY = y;
                path.moveTo(preX, preY);
                break;
            case MotionEvent.ACTION_MOVE:
                lastX = x;
                lastY = y;
                int controlX = (x+lastX)/2;
                int controlY = (y+lastY)/2;
                path.quadTo(controlX, controlY, lastX, lastY);
                invalidate();
                preX = lastX;
                preY = lastY;
                break;
            case MotionEvent.ACTION_UP:
                bufferCanvas.drawPath(path, paint);
                invalidate();
                break;

        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bufferBitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
    }
}
