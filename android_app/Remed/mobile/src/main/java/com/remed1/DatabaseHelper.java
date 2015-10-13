package com.remed1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import java.sql.RowId;

/**
 * Created by ΜΗΤΣΟΣ on 5/10/2015.
 */
public class DatabaseHelper  extends SQLiteOpenHelper{

    public static final String  DATABASE_NAME = "Reminder.db";
    public static final String  TABLE_NAME = "Pills_Info";
    public static final String  Col_0 = "Id";
    public static final String  Col_1 = "Pill_Name";
    public static final String  Col_2 = "Starting_Date";
    public static final String  Col_3 = "Starting_Time";
    public static final String  Col_4 = "Schedule";
    public static final String  Col_5 = "Shape_Color";
    public static final String  Col_6 = "Dosage";
    public static final String  Col_7 = "Instructions";
////////////////////////////////////////////
    public static final String  TABLE_NAME1 = "User_Info";
    public static final String  Col1_0 = "Name_User";
    public static final String  Col1_1 = "Surname_User";
    public static final String  Col1_2 = "Age_User";
    public static final String  Col1_3 = "Gender_User";

    ////////////////////////////////////////


    public DatabaseHelper(Context context ){
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Pill_Name TEXT NOT NULL, " +
                "Starting_Date TEXT NOT NULL, " +
                "Starting_Time TEXT NOT NULL, " +
                "Schedule TEXT NOT NULL, " +
                "Shape_Color TEXT, " +
                "Dosage TEXT NOT NULL, " +
                "Instructions TEXT NOT NULL)");
        db.execSQL("create table " + TABLE_NAME1 + "(Name_User TEXT, Surname_User TEXT, Age_User TEXT, Gender_User TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists " + TABLE_NAME);
        db.execSQL("Drop Table if exists " + TABLE_NAME1);
        onCreate(db);

    }

   public boolean insertData(String pn,String sd, String st, String sdl, String sc, String dos, String inst ){

       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       if(pn.length() == 0) {

           return false;
       }
       contentValues.put(Col_1, pn);
       contentValues.put(Col_2, sd);
       contentValues.put(Col_3, st);
       contentValues.put(Col_4, sdl);
       contentValues.put(Col_5, sc);
       contentValues.put(Col_6, dos);
       contentValues.put(Col_7, inst);
       long result = db.insert(TABLE_NAME, null, contentValues);
       if(result == -1)
           return false;
       else
           return true;
    }

    public Cursor viewMyMeds(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor display = db.rawQuery("select * from " + TABLE_NAME, null);
        return display;

    }

    public boolean editData(String id, String pn,String sd, String st, String sdl, String sc, String dos, String inst)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_0, id);
        contentValues.put(Col_1, pn);
        contentValues.put(Col_2, sd);
        contentValues.put(Col_3, st);
        contentValues.put(Col_4, sdl);
        contentValues.put(Col_5, sc);
        contentValues.put(Col_6, dos);
        contentValues.put(Col_7, inst);
        db.update(TABLE_NAME, contentValues, "Id = ?", new String[] {id} );
        return true;
    }

    public boolean editUserProfile(String nu, String su, String au, String gu){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1_0, nu);
        contentValues.put(Col1_1, su);
        contentValues.put(Col1_2, au);
        contentValues.put(Col1_3, gu);
        db.update(TABLE_NAME1, contentValues, "rowid = ?", new String[] {"1"} );
        return true;
    }


    public Integer removeData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Id = ?",new String[]{id} );
    }

    public boolean insertUserProfile(String nu, String su, String au, String gu){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col1_0, nu);
        contentValues.put(Col1_1, su);
        contentValues.put(Col1_2, au);
        contentValues.put(Col1_3, gu);

        long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public int IsEmpty(){
        String countQuery = "SELECT  * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME1);
    }

    public String getUserName() {
       SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT " +Col1_0+ " FROM "+TABLE_NAME1, null);
        String username = "";
         for(cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()){
            username = cr.getString(cr.getColumnIndex(Col1_0));}
        cr.close();
        return username;

    }


    public int getUserGender() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT " + Col1_3 + " FROM " + TABLE_NAME1, null);
        if (cr != null)
            try {
                if (cr.moveToFirst()) {
                    String isMale = cr.getString(cr.getColumnIndex(Col1_3));
                    if (isMale.equals("Male"))
                        return 1;
                    else
                        return 2;
                }
            } finally {
                cr.close();
            }
        return 0;

    }
    public Cursor getUserInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(TABLE_NAME1, new String[]{Col1_0, Col1_1,Col1_2,Col1_3},
                null, null, null, null, null);
        return mCursor;}

    public Cursor getpillnames(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(TABLE_NAME, new String[]{Col_1, Col_6 },
                null, null, null, null, null);
        return mCursor;}


    public Cursor getPillsInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor info = db.query(TABLE_NAME, new String[]{Col_1, Col_2, Col_4, Col_7},
                null, null, null, null, null);
        return info;}
}
