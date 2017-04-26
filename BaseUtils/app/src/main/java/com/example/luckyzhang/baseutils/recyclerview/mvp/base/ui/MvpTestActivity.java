package com.example.luckyzhang.baseutils.recyclerview.mvp.base.ui;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.BaseMvpActivity;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.contract.MvpTestContract;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.model.MvpTestModel;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.presenter.MvpTestPresenter;

import java.util.List;

public class MvpTestActivity extends BaseMvpActivity<MvpTestPresenter, MvpTestModel> implements MvpTestContract.View {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_mvp_test;
    }

    private ListView listView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 1:
                    mPresenter.showList();
                    dialog.hide();
                    break;
            }
        }
    };

    @Override
    public void initView() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView = (ListView) findViewById(R.id.listview);
        mPresenter.showDialog();
        Message message = new Message();
        message.arg1 = 1;
        handler.sendMessageDelayed(message, 2000);
//        dialog.hide();
    }

    @Override
    public void initListener() {

    }

    private ArrayAdapter simpleAdapter;

    @Override
    public void initAdapter(List<String> lists) {
        simpleAdapter = new ArrayAdapter(mPresenter.getContext(), R.layout.item_listview, lists);
        listView.setAdapter(simpleAdapter);

    }


    Dialog dialog;

    @Override
    public void showDialog(String content) {
        dialog = new Dialog(mPresenter.getContext());
        dialog.setTitle(content);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
