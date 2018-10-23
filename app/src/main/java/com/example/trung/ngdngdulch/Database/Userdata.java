package com.example.trung.ngdngdulch.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;
import android.text.Selection;

import com.example.trung.ngdngdulch.Database.Databasehelper;

public class Userdata {
    private SQLiteDatabase database;
    private Databasehelper db;

    public Userdata(Context context) {
       db = new Databasehelper(context);
    }
    public void open(){
        database = db.getWritableDatabase();
    }

    public void close() {
        db.close();
    }
    public void Insert(String tenTK,String MK,String hoVaTen,String ngaySinh,String Sdt,String diaChi) {
        ContentValues row = new ContentValues();
        row.put("Tentaikhoan", tenTK);
        row.put("Matkhau", MK);
        row.put("Tennguoidung", hoVaTen);
        row.put("Ngaysinh", ngaySinh);
        row.put("Sodienthoai", Sdt);
        row.put("Diachi", diaChi);
        database.insert("User", null, row);
    }
    public void delete(String name){
        database.delete("User","Tentaikhoan='"+name+"'",null);
    }
    public void update(String tenTK,String MK,String hoVaTen,String ngaySinh,int Sdt,String diaChi){
        ContentValues row = new ContentValues();

        row.put("Matkhau", MK);
        row.put("Tennguoidung", hoVaTen);
        row.put("Ngaysinh", ngaySinh);
        row.put("Sodienthoai", Sdt);
        row.put("Diachi", diaChi);
        database.update("User",row,"Tentaikhoan='"+tenTK+"'",null);
    }
    public Cursor select(){

        String sql ="select * from User;";
        return database.rawQuery(sql,null);
    }
    public Cursor select1(){
        String sql = "select * from User where ID > ? and ID <?;";
        String[] str = {"1","4"};
        return database.rawQuery(sql,str);
    }
}
