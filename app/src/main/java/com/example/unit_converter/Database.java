package com.example.unit_converter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public static final String db_nm="data_db.db";
    public static final String tb_nm="history_info";

    public static final String col_0="n";
    public static final String col_1="value1";
    public static final String col_2="fr";
    public static final String col_3="value2";
    public static final String col_4="t";


    public Database(Context context) {

        super(context, db_nm, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + tb_nm + "(value1 text,fr text,value2 text,t text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tb_nm);
        onCreate(db);
    }
    public int deleteData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(tb_nm,null,null);
    }

    public boolean insert(String value1 ,String fr,String value2,String t)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();


        cv.put(col_1,value1);
        cv.put(col_2,fr);
        cv.put(col_3,value2);
        cv.put(col_4,t);

        long res=db.insert(tb_nm,null,cv);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+tb_nm,null);
        return res;
    }
}