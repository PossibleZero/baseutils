package com.example.luckyzhang.baseutils.ui.activity.newskills;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.luckyzhang.baseutils.R;

/**
 * 属性动画
 */
public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        imageView = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                removeObj();
                break;
        }
    }

    private void removeObj() {
        float translationX = imageView.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX",translationX, -100f, translationX);
        animator.setDuration(1000);
        animator.start();
    }
}
