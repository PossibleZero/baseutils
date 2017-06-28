package com.example.luckyzhang.baseutils.ui.activity.canvasActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.canvas.DrawCodeView;

public class CavasCodeActivity extends AppCompatActivity implements View.OnClickListener {

    Button changeBtn;
    DrawCodeView drawCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cavas_code);
        drawCodeView = (DrawCodeView) findViewById(R.id.codeView);
        changeBtn = (Button) findViewById(R.id.change);
        changeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                drawCodeView.setCount(20);
                break;
        }
    }
}
