package com.example.luckyzhang.baseutils.recyclerview.mvp.base.model;

import com.example.luckyzhang.baseutils.recyclerview.mvp.base.contract.MvpTestContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luckyzhang on 2017/4/26.
 */

public class MvpTestModel implements MvpTestContract.Model {

    @Override
    public List<String> getTestDatas() {

        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = "第" + i + "个";
            lists.add(str);
        }
        return lists;

    }

    @Override
    public String getContent() {
        String content = "dialog的内容";
        return content;
    }
}
