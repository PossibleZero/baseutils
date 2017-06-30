package com.example.luckyzhang.baseutils.ui.activity.eventActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.canvas.MultiLauncher;

public class MultiLauncherActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextBtn, preBtn;
    private MultiLauncher multiLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_launcher);
        multiLauncher = (MultiLauncher) findViewById(R.id.multi_launcher);
        nextBtn = (Button) findViewById(R.id.next);
        preBtn = (Button) findViewById(R.id.pre);
        nextBtn.setOnClickListener(this);
        preBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                multiLauncher.moveToNext();
                break;
            case R.id.pre:
                multiLauncher.moveToPre();
                break;
        }
    }
}
