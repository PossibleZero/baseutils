package com.example.luckyzhang.baseutils.recyclerview.mvp.base;


import com.example.luckyzhang.baseutils.recyclerview.mvp.base.ui.BaseActivity;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils.InstanceUtil;


/**
 * Created by luckyzhang on 16/6/20.
 */
public abstract class BaseMvpActivity<P extends BasePresenter, M extends BaseModel> extends BaseActivity {
    protected P mPresenter;

    @Override
    public void init() {
        mPresenter = InstanceUtil.getInstance(this, 0);
        M mModel = InstanceUtil.getInstance(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
        this.initView();
        this.initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }


    public abstract void initView();

    public abstract void initListener();


    public <T> T findViewbyId(int id) {
        return (T) findViewById(id);
    }
}
