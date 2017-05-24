package com.example.luckyzhang.baseutils.database;

import android.view.View;
import android.widget.AdapterView;

import com.example.luckyzhang.baseutils.base.DemoBaseActivity;
import com.example.luckyzhang.baseutils.database.db.DBManager;
import com.example.luckyzhang.baseutils.database.db.TeacherDBManager;

/**
 * 数据库的操作练习
 */
public class DBActivity extends DemoBaseActivity {

    String[] strs = {"创建数据库", "增加", "删除", "修改", "查询", "更新时检测teacher表", "事务操作(批量插入)", "copy表到另外一个表中"};

    @Override
    protected String[] initDatas() {
        return strs;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                DBManager.getInstance().createTable();
                break;
            case 1:
                DBManager.getInstance().insertTable();
                break;
            case 2:
                DBManager.getInstance().deleteById();
                break;
            case 3:
                DBManager.getInstance().update();
                break;
            case 4:
                DBManager.getInstance().selectCount();
                break;
            case 5:
                TeacherDBManager.getInstance().insertTable();
                break;
            case 6:
                TeacherDBManager.getInstance().insertTransaction(null);
                break;
            case 7:
                TeacherDBManager.getInstance().updateTable();
                break;

        }

    }


}
