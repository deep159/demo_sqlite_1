0package com.sts.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.LoudnessEnhancer;
import android.util.Log;

/**
 * Created by Sunil on 15/3/2017.
 */

public class DB_Handler extends SQLiteOpenHelper {

    final  static String DB_NAME="student";
    final static int DB_VERISON=2;
    String TABLE="stu";
    String ID="id";
    String NAME="name";

    String CREATE_TABLE="create table "+TABLE+"("+ID+" varchar2(40),"+NAME+" varchar2(40)) if not exixts;";
    String SELECT_QUERY="select * from "+TABLE;
    String SELECT_PARTICILLAR="select * from "+TABLE+" where "+ID+"=?";


    public DB_Handler(Context context) {
        super(context,DB_NAME,null,DB_VERISON);
        Log.e(">>>","database created");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e(">>>","table is created");
    }

    public  void insertData(String id,String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        db.insert(TABLE,null,values);
        Log.e(">>>","values inserted");
    }

    public  void viewDate()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(SELECT_QUERY,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (cursor.moveToNext())
            {
                String id=cursor.getString(0);
                String name=cursor.getString(1);
                Log.e(">>>","id = "+id+"    name = "+name);
            }
        }
        else {
            Log.e(">>>","empty cursor");
        }
    }

    public void deleteData(String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        //String a[]={ID};
        db.delete(TABLE,ID+"=?",new String[]{id});
        Log.e(">>>","Data Deleted");
    }

    public  void updateData(String OLD_NAME,String NEW_NAME)
    {
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(NAME,NEW_NAME);

        db.update(TABLE,values,NAME+"=?",new String[]{OLD_NAME});
        Log.e(">>>","data updated");
    }

    public  void findData(String ID)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(SELECT_PARTICILLAR,new String[]{ID});
        if(cursor!=null)
        {
            if(cursor.moveToNext())
            {
                String id=cursor.getString(0);
                String name=cursor.getString(1);

                Log.e(">>>","ID   "+id);
                Log.e(">>>","NAME   "+name);
            }
            else
            {
                Log.e(">>>","data not found");
            }

        }
        else{
            Log.e(">>>","data not found");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_QUERY="drop table "+TABLE+" if exists";
        db.execSQL(DROP_QUERY);
        onCreate(db);

    }
}
