package com.example.luckyzhang.baseutils.ui.activity.property;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.luckyzhang.baseutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luckyzhang on 2018/1/5.
 */

public class ViewFlipperWidget extends FrameLayout {
    ViewFlipper viewflpper;
    private List datas = new ArrayList<>();
    private Context mContext;

    public ViewFlipperWidget(@NonNull Context context) {
        this(context, null);
    }

    public ViewFlipperWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewFlipperWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.widget_view_flipper, this);
        viewflpper = (ViewFlipper) view.findViewById(R.id.viewflpper);
        addView(getTestDatas());
    }

    public void addView(List<String> data) {
        if (data != null && (data.size() > 0)) {
            TextView textView1 = null;
            TextView textView2 = null;
            View childView = null;
            for (int i = 0; i < data.size(); i++) {
                if (i % 2 == 0) { //奇数
                    childView = View.inflate(mContext, R.layout.include_wdiget_flipper_linear, null);
                    textView1 = (TextView) childView.findViewById(R.id.text1);
                    textView2 = (TextView) childView.findViewById(R.id.text2);
                    textView1.setText(data.get(i));
                    if (childView != null)
                        viewflpper.addView(childView); //两个数据添加一个view
                } else { //偶数
                    if (textView2 != null)
                        textView2.setText(data.get(i));

                }
            }
            viewflpper.startFlipping();
        }
    }

    private List<String> getTestDatas() {
        List<String> datas = new ArrayList<>();
        String data = null;
        for (int i = 0; i < 5; i++) {

            datas.add("数据:" + i);
        }
        return datas;
    }
}
