package com.example.luckyzhang.baseutils.ui.activity.newskills;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.adapter.PageAdapter;
import com.example.luckyzhang.baseutils.widget.listener.ViewPagerChangeListener;

public class PagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    PageAdapter pageAdapter;
    private int[] images = {R.mipmap.i1, R.mipmap.i2, R.mipmap.i3};
    LinearLayout linearLayout;
    private ImageView[] imageVies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.viewGroup);
        //设置点
        imageVies = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 0, 0);
            ImageView imageView = new ImageView(PagerActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(25, 25));
            imageVies[i] = imageView;
            if (i == 0) {
                imageVies[i].setImageResource(R.drawable.circle_red);
            } else {
                imageVies[i].setImageResource(R.drawable.circle_black);
            }
            linearLayout.addView(imageVies[i], params);

        }
        pageAdapter = new PageAdapter(PagerActivity.this, images);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(new ViewPagerChangeListener(PagerActivity.this, viewPager, imageVies));

    }
}
