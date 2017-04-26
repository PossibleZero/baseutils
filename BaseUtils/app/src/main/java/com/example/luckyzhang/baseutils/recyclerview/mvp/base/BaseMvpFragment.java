package com.example.luckyzhang.baseutils.recyclerview.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils.InstanceUtil;


/**
 * Created by baixiaokang on 16/6/20.
 */
public abstract class BaseMvpFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {
    protected P mPresenter;
    public Context mContext;
    public View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(this.getLayoutId(), null);
        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        mPresenter = InstanceUtil.getInstance(this, 0);
        M mModel = InstanceUtil.getInstance(this, 1);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
        this.initView();
        this.initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initListener();

    public void setClickViews(View... vs) {
        for (View v : vs) {
            if (this instanceof View.OnClickListener)
                v.setOnClickListener((View.OnClickListener) this);
        }
    }

    public <T> T findViewbyId(int id) {
        return (T) mContentView.findViewById(id);
    }
}
