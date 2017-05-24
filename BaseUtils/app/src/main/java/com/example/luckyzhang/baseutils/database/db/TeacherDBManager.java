package com.example.luckyzhang.baseutils.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.luckyzhang.baseutils.database.bean.TeacherBean;
import com.example.luckyzhang.baseutils.database.tools.DBSqliteOpenHelper;
import com.example.luckyzhang.baseutils.test.ListUtilsTestDatas;
import com.example.luckyzhang.baseutils.tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luckyzhang on 2017/5/19.
 * <p>
 * 数据库升级的两种思路
 * 1、 修改、添加、更改表结构 用alert table进行操作
 * 之后还要进行下在onCreate时建立下新的表结构(这里面是最新最全的表)
 * <p>
 * 2、结构发生重大改变的时候,建立新的临时的表,把旧的数据copy到新表中,删除久表,更改名字
 * 之后还要进行下在onCreate时建立下新的表结构(这里面必须是最新最全的表)
 */

public class TeacherDBManager {

    private TeacherDBManager() {
    }

    private static class Holder {
        private static final TeacherDBManager sHolder = new TeacherDBManager();
    }

    public static TeacherDBManager getInstance() {
        return Holder.sHolder;
    }

    private static final String TABLE_NAME = "teacher";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "t_name";
    private static final String COLUMN_AGE = "t_age";
    private static final String COLUMN_ADDRESS = "t_address";

    public static final String CREATE_TABLE_TEACHER = "create table if not exists " + TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_AGE + " INTEGER " +
            ")";

    /**
     * 创建新的数据库
     */
    public static final String CREATE_NEW_TABLE_TEACHER = "create table if not exists " + TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_ADDRESS + " VARCHAR, " +
            COLUMN_AGE + " INTEGER " +
            ")";

    //修改表结构
    public static final String CHANGE_TABLE_STRUCT = "alter table " + TABLE_NAME + " add " + COLUMN_ADDRESS + " VARCHAR ";


    private DBSqliteOpenHelper db;

    public void init(Context context) {
        db = DBSqliteOpenHelper.getInstance(context);
    }

    public synchronized void insertTable() {
        try {
            SQLiteDatabase writableDatabase = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "teacher");
            values.put(COLUMN_AGE, 30);
//            values.put(COLUMN_ADDRESS, "北京");
            writableDatabase.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            LogUtils.d("e:" + e.getMessage());

        }
    }


    //开启事务,批量操作方面
    public synchronized void insertTransaction(List<TeacherBean> lists) {
        lists = ListUtilsTestDatas.getDatas();
        SQLiteDatabase writableDatabase = null;
        List<ContentValues> contentValues = new ArrayList<>();
        for (TeacherBean teacherBean : lists
                ) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, teacherBean.getName());
            values.put(COLUMN_AGE, teacherBean.getAge());
            values.put(COLUMN_ADDRESS, teacherBean.getAdrress());
            contentValues.add(values);
        }
        try {
            writableDatabase = db.getWritableDatabase();
            writableDatabase.beginTransaction();
            for (ContentValues contentV : contentValues
                    ) {
                writableDatabase.insert(TABLE_NAME, null, contentV);
            }
        } catch (Exception e) {
            LogUtils.d("e:" + e.getMessage());
        } finally {
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        }
    }

    /**
     * 更新表名,将一个数据copy到另外一个表中
     */
    public void updateTable() {
        SQLiteDatabase sqLiteDatabase = null;

        try {
            sqLiteDatabase = db.getWritableDatabase();
            sqLiteDatabase.beginTransaction();

            // 1, Rename table.
            String tempTableName = TABLE_NAME + "_temp";
            String sql = "ALTER TABLE " + TABLE_NAME + " RENAME TO " + tempTableName;
            sqLiteDatabase.execSQL(sql);

            // 2, Create table.
            //onCreateTable(db);
            sqLiteDatabase.execSQL(CREATE_NEW_TABLE_TEACHER);

            // 3, Load data
            sql = "INSERT INTO " + TABLE_NAME +
                    " (" + COLUMN_NAME + "," + COLUMN_AGE + ") " +
                    " SELECT " + COLUMN_NAME + "," + COLUMN_AGE + " FROM " + tempTableName;

            sqLiteDatabase.execSQL(sql);

            // 4, Drop the temporary table.
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tempTableName);

            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }

    }


}
