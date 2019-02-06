package com.education.studentregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseHelper extends SQLiteAssetHelper

{
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "detail";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    public boolean insertData(String name,String number,String Age,String Gender,String Image,String Fees,String Disscount, String Total) {
        SQLiteDatabase db = getWritableDatabase();

        String q="insert into detail values ('"+name+"','"+number+"','"+Age+"','"+Gender+"','"+Image+"','"+Fees+"','"+Disscount+"','"+Total+"')";
        db.execSQL(q);
        db.close();


return false;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from detail",null);
        return res;
    }
    public void add(String s,String s1)
    {
        SQLiteDatabase db = getReadableDatabase();
        String q="insert into sss (name,sirname) values ('"+s+"','"+s1+"')";
        db.execSQL(q);
        db.close();
    }


}
