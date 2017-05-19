package com.example.luckyzhang.baseutils.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.luckyzhang.baseutils.database.tools.DBSqliteOpenHelper;
import com.example.luckyzhang.baseutils.tools.LogUtils;

/**
 * Created by luckyzhang on 2017/5/19.
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

    public static final String CREATE_TABLE_TEACHER = "create table if not exists " + TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_AGE + " INTEGER " +
            ")";


    private DBSqliteOpenHelper db;

    public void init(Context context) {
        db = DBSqliteOpenHelper.getInstance(context);
    }

    public void insertTable() {
        try {
            SQLiteDatabase writableDatabase = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "teacher");
            values.put(COLUMN_AGE, 30);
            writableDatabase.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            LogUtils.d("e:" + e.getMessage());

        }
    }
}
