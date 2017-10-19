package com.example.luckyzhang.baseutils.ui.activity.annotation;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luckyzhang.baseutils.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class IntDefAnnotationActivity extends AppCompatActivity {

    public static final int MONDAY = 0x01;
    public static final int TUESDAY = 0x02;
    public static final int WEDNESDAY = 0x03;

    @IntDef({MONDAY, TUESDAY, WEDNESDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDays {
    }

    @WeekDays
    int currentDay = MONDAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_def_annotation);
        setWeekDay(IntDefAnnotationActivity.MONDAY);
        @WeekDays int currentDay = getCurrentDay();
        switch (currentDay) {
            case 1:
                break;
        }
    }

    public void setWeekDay(@WeekDays int currentDay) {
        this.currentDay = currentDay;
    }

    @IntDefAnnotationActivity.WeekDays
    public int getCurrentDay() {
        return currentDay;
    }

}
