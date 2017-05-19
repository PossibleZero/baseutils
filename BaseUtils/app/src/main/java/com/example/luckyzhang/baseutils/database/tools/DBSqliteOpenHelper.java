package com.example.luckyzhang.baseutils.database.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luckyzhang.baseutils.database.db.DBManager;
import com.example.luckyzhang.baseutils.database.db.TeacherDBManager;

/**
 * Created by luckyzhang on 2017/5/17.
 */

public class DBSqliteOpenHelper extends SQLiteOpenHelper {


    //fixme 数据库在升级的时候,必须下个版本高于上一个版本;
    // 数据库创建是在获取getWritableDatabase或者getReadableDatabase时才行,正常初始化时并不创建数据库

    private static final int V1_1 = 1; // 不同的发布版本
    private static final int V1_2 = 2; //
    private static final int V1_3 = 3; //
    private static final int V1_4 = 4; //

    private static final String DATABASE_NAME = "luckyzhang.db";

    private static final int DATABASE_VERSION = V1_4; //当前发布的版本

    private Context mContext;

    private static DBSqliteOpenHelper helper;

    /**
     * 简建成单例的
     *
     * @param context
     * @return
     */
    public static DBSqliteOpenHelper getInstance(Context context) {
        if (helper == null) {
            synchronized (DBSqliteOpenHelper.class) {
                if (helper == null) {
                    helper = new DBSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
                }
            }
        }
        return helper;
    }

    private DBSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBManager.CREATE_TABLE_STUDENT);

    }

    //这种操作方法有助于数据的升级操作
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            if (oldVersion < 2) {
                db.execSQL(TeacherDBManager.CREATE_TABLE_TEACHER);
            }
        }

    }
}
