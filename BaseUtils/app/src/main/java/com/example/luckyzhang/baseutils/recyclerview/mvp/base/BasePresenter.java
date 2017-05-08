package com.example.luckyzhang.baseutils.recyclerview.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils.BaseManager;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by luckyzhang on 16/6/20.
 */
public abstract class BasePresenter<V, M> {
    public M mModel;
    public V mView;
    public Reference<Activity> mContextReference;
    public BaseManager mBaseManager = new BaseManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        if (v instanceof Activity)
            this.mContextReference = new WeakReference<>((Activity) v);
        else if (v instanceof Fragment)
            this.mContextReference = new WeakReference<Activity>(((Fragment) v).getActivity());
    }

    public Context getContext() {
        //最好是用application的,防止内存泄露
        return /*  mContextReference == null || mContextReference.get() == null ? LagouApplication.getAppApplication() : */mContextReference.get();
    }

    public void onDestroy() {
        mBaseManager.clearTask();
        if (mContextReference != null) mContextReference.clear();
    }
}