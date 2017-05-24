package com.example.luckyzhang.baseutils.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luckyzhang.baseutils.database.tools.DBSqliteOpenHelper;
import com.example.luckyzhang.baseutils.tools.LogUtils;

/**
 * Created by luckyzhang on 2017/5/17.
 */

public class DBManager {

    private DBManager() {
    }

    //fixme 创建数据库的时候使用这种固定模板,不容易出错

    private static class Holder {
        private static final DBManager dbmanager = new DBManager();
    }

    private static final String COLUMN_ID = "_id";
    private static final String TABLE_NAME = "student";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_AGE = "student_age";
    private static final String COLUMN_ADDRESS = "student_address";
    private static final String COLUMN_DES = "student_des";

    public static DBManager getInstance() {
        return Holder.dbmanager;
    }

    DBSqliteOpenHelper openHelper;

    public static final String DROP_TABLE_INSTANT_REPLY = "drop table if exists " + TABLE_NAME;

    public static final String CREATE_TABLE_STUDENT = "create table if not exists " + TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR, " +
            COLUMN_AGE + " INTEGER, " +
            COLUMN_ADDRESS + " TEXT, " +
            COLUMN_DES + " BLOG " +
            ")";

    /**
     * 初始化数据库
     *
     * @param context
     */
    public void init(Context context) {
        openHelper = DBSqliteOpenHelper.getInstance(context);
    }

    public synchronized void createTable() {
        openHelper.getWritableDatabase();
    }

    public synchronized void insertTable() {
        try {
            int i = 20;
            SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, "zhangyn");
            contentValues.put(COLUMN_AGE, i++);
            contentValues.put(COLUMN_ADDRESS, "北京");
            contentValues.put(COLUMN_DES, "程序猿");
            writableDatabase.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {

        }

    }

    public synchronized void deleteById() {
        try {
            SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
            writableDatabase.delete(TABLE_NAME, COLUMN_AGE + "=?", new String[]{"20"});
        } catch (Exception e) {
            LogUtils.d("");

        }
    }

    public synchronized void selectCount() {
        Cursor cursor = null;
        try {
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            String where = COLUMN_AGE + "=?";
            String[] args = new String[]{"20"};
            cursor = readableDatabase.query(TABLE_NAME, null, where, args, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String des = cursor.getString(cursor.getColumnIndex(COLUMN_DES));
                LogUtils.d("name:" + name + "  age:" + age + " address:" + address + " des:" + des);
            }

        } catch (Exception e) {

        } finally {
            try {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {

            }

        }
    }

    public synchronized void update() {
        try {
            SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ADDRESS, "lj");
            writableDatabase.update(TABLE_NAME, values, COLUMN_AGE + "=?", new String[]{"20"});

        } catch (Exception e) {

        }
    }

}
