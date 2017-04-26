package com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils;

import java.util.ArrayList;

/**
 * Created by baixiaokang on 16/6/21.
 */
public class BaseManager {
    public ArrayList<Integer> mRequestTasks;

    public void addTask(int taskId) {
        if (mRequestTasks == null)
            mRequestTasks = new ArrayList<>();
        this.mRequestTasks.add(taskId);
    }

    /**
     * 统一管理网络请求,在结束时自动移除所有网络任务
     */
    public void clearTask() {
        if (mRequestTasks != null)
            try {
                /*if (ClientRequestHelper.getInstance() != null) {
                    for (Integer taskId : mRequestTasks)
                        ClientRequestHelper.getInstance()
                                .cancelRequest(taskId, false);
                    mRequestTasks = null;
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
