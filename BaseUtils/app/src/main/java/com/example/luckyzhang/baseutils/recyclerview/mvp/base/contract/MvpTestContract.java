package com.example.luckyzhang.baseutils.recyclerview.mvp.base.contract;

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.BaseModel;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.BasePresenter;
import com.example.luckyzhang.baseutils.recyclerview.mvp.base.BaseView;

import java.util.List;

/**
 * Created by luckyzhang on 2017/4/26.
 */

public interface MvpTestContract {

    /**
     * 处理数据方面的问题
     */
    interface Model extends BaseModel {

        /**
         * 准备数据
         */
        public List<String> getTestDatas();

        /**
         * 设置弹出的dialog显示的内容
         */
        public String getContent();

    }

    /**
     * 处理视图方面的问题
     */
    interface View extends BaseView {
        void initAdapter(List<String> lists);

        void showDialog(String content);

    }

    /**
     * 处理具体的业务逻辑
     */
    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void showList();

        public abstract void showDialog();

    }

}
