package com.example.luckyzhang.baseutils.ui.activity.property;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.luckyzhang.baseutils.R;

public class ViewFlipperActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewFlipper viewFlipper;

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflpper);
//        viewFlipper.setFlipInterval(500);
        textView = (TextView) findViewById(R.id.tv2);
        button = (Button) findViewById(R.id.btn1);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                viewFlipper.startFlipping();
                viewFlipper.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewFlipper.stopFlipping();
                    }
                }, 6500);
                break;
        }
    }
}
