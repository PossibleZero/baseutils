package com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils;

/**
 * Created by luckyzhang on 2017/4/26.
 */

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.model.MvpTestModel;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.presenter.MvpTestPresenter;

/**
 * @ 实例化工厂 此类由apt自动生成
 */
public final class InstanceFactory {
    /**
     * @此方法由apt自动生成
     */
    public static Object create(Class mClass) throws IllegalAccessException, InstantiationException {
        switch (mClass.getSimpleName()) {
      case "MvpTestModel": return new MvpTestModel();
      case "MvpTestPresenter": return new MvpTestPresenter();
      /*
      case "HomePresenter": return new HomePresenter();
      case "LoginModel": return new LoginModel();
      case "LoginPresenter": return new LoginPresenter();
      case "EditModel": return new EditModel();
      case "EditPresenter": return new EditPresenter();
      case "EditBaseModel": return new EditBaseModel();
      case "EditBasePresenter": return new EditBasePresenter();
      case "PreviewModel": return new PreviewModel();
      case "PreviewPresenter": return new PreviewPresenter();*/
            default:
                return mClass.newInstance();
        }
    }
}