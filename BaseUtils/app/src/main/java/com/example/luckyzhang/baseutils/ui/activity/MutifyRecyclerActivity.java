package com.example.luckyzhang.baseutils.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.ui.fragment.MutifyTyp1Fragment;
import com.example.luckyzhang.baseutils.ui.fragment.SimpleTyp2Fragment;

public class MutifyRecyclerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button type1Btn;
    private Button type2Btn;
    private FrameLayout fragment;
    private MutifyTyp1Fragment mutifyTyp1Fragment;
    private SimpleTyp2Fragment simpleTyp2Fragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutify_recycler);
        fragment = (FrameLayout) findViewById(R.id.content_framelayout);
        type1Btn = (Button) findViewById(R.id.type1_btn);
        type2Btn = (Button) findViewById(R.id.type2_btn);
        type1Btn.setOnClickListener(this);
        type2Btn.setOnClickListener(this);
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        mutifyTyp1Fragment = new MutifyTyp1Fragment();
        simpleTyp2Fragment = new SimpleTyp2Fragment();
        fragmentTransaction.add(R.id.content_framelayout, mutifyTyp1Fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type1_btn:
                FragmentTransaction ft = supportFragmentManager.beginTransaction();
                if (mutifyTyp1Fragment.isAdded()) {
                    ft.hide(simpleTyp2Fragment).show(mutifyTyp1Fragment).commitAllowingStateLoss();
                } else {
                    ft.hide(simpleTyp2Fragment).add(R.id.content_framelayout, mutifyTyp1Fragment).commitAllowingStateLoss();
                }

                break;
            case R.id.type2_btn:
                FragmentTransaction ft2 = supportFragmentManager.beginTransaction();
                if (simpleTyp2Fragment.isAdded()) {
                    ft2.hide(mutifyTyp1Fragment).show(simpleTyp2Fragment).commitAllowingStateLoss();
                } else {
                    ft2.hide(mutifyTyp1Fragment).add(R.id.content_framelayout, simpleTyp2Fragment).commitAllowingStateLoss();
                }

                break;
        }
    }
}
