package com.caumap.caumapbeta2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase  db;
    private static DatabaseAccess instance;
    Cursor c  = null ;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    //query and return database
    public String getLocation(String name){
    c = db.rawQuery("select Location from table1 where BSSID = '" + name + "'", new String[]{});
    StringBuffer buffer = new StringBuffer();
    while (c.moveToNext()){
        String location = c.getString(0);
        buffer.append(""+location);
    }
    return buffer.toString();
    }
}
