package com.example.luckyzhang.baseutils.recyclerview.mvp.base.presenter;

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.contract.MvpTestContract;

/**
 * Created by luckyzhang on 2017/4/26.
 */

public class MvpTestPresenter extends MvpTestContract.Presenter {
    @Override
    public void showList() {
        mView.initAdapter(mModel.getTestDatas());
    }

    @Override
    public void showDialog() {
        mView.showDialog(mModel.getContent());
    }


}
