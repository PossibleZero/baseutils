package com.example.luckyzhang.baseutils.ui.activity.eventActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.canvas.Scroller1Group;

public class Scroller1Activity extends AppCompatActivity implements View.OnClickListener {

    private Scroller1Group scroller1Group;
    private Button startBtn,endBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller1);
        scroller1Group = (Scroller1Group) findViewById(R.id.scrollView);
        startBtn = (Button) findViewById(R.id.startBtn);
        endBtn = (Button) findViewById(R.id.endBtn);
        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                scroller1Group.startScroll();
                break;
            case R.id.endBtn:
                scroller1Group.abort();
                break;
        }
    }
}
