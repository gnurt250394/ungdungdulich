package com.example.trung.ngdngdulch.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    private static final String database = "SqlUser.sqlite";
    private static final int versions = 2;
    private static final String SqlCreateTable = "create table User(ID Integer primary key autoincrement, Tentaikhoan varchar, Matkhau varchar,Tennguoidung varchar,Ngaysinh varchar,Sodienthoai integer,Diachi varchar);";
    public Databasehelper( Context context) {
        super(context, database, null, versions);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
