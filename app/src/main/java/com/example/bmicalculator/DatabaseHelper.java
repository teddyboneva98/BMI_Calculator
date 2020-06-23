package com.example.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calculations.db";
    public static final String TABLE_NAME = "calculations_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "WEIGHT";
    public static final String COL3 = "HEIGHT";
    public static final String COL4 = "RESULT";

    public DatabaseHelper (Context context) {super(context,DATABASE_NAME,null,1);}

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "WEIGHT TEXT, HEIGHT TEXT, RESULT TEXT)";
        db.execSQL(createTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public boolean addData(String weight, String height, String calculatedResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Присвояване на стойности за всяка колона
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, weight);
        contentValues.put(COL3, height);
        contentValues.put(COL4, calculatedResult);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //Ако данните са въведени неправилно, ще върне -1
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
    /*
    public  Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    } */
}
