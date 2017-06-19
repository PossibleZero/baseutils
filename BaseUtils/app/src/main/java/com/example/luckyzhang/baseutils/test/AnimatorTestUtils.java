package com.example.luckyzhang.baseutils.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by luckyzhang on 2017/5/26.
 */
public class AnimatorTestUtils {

    public static void objectAnim(View view) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(1000);
        objectAnimator.ofFloat(view, "rotation", 0f, 360f);
        objectAnimator.start();
    }


    public static void valueAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
            }
        });
        valueAnimator.start();

    }


    public static void setAnim() {
        AnimatorSet set = new AnimatorSet();
//        set.play().with().after();
    }

    public static void valuePointAnim() {
//        ValueAnimator.ofObject();
    }



}
