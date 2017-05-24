package com.example.luckyzhang.baseutils.test;

import com.example.luckyzhang.baseutils.database.bean.TeacherBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luckyzhang on 2017/5/19.
 */

public class ListUtilsTestDatas {

    public static List<TeacherBean> getDatas() {
        List<TeacherBean> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TeacherBean teacherBean = new TeacherBean();
            teacherBean.setAge(30 + i);
            teacherBean.setAdrress("北京" + i);
            teacherBean.setName("zyn" + i);
            datas.add(teacherBean);
        }
        return datas;
    }
}
