package com.invocker.invocker_math.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydb.db";
    public static final String TABLE_NAME = "UserScope";
    public static final int DATABASE_VERSION = 1;
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TABLE_NAME+"(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(100)," +"scope INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
