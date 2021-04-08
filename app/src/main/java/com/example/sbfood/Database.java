package com.example.sbfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "SonyFood.db", factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Acc(ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL VARCHAR(100), USERNAME VARCHAR(100),PASSWORD VARCHAR(100), FULLNAME VARCHAR(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Acc");
        onCreate(db);


    }

    public Boolean insertData(String username, String password, String email, String fullname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("PASSWORD", password);
        contentValues.put("EMAIL", email);
        contentValues.put("FULLNAME", fullname);
        long rs = db.insert("Acc", null, contentValues);
        if (rs == -1) {
            return false;
        } else
            return true;


    }

    public Boolean checkUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Acc WHERE USERNAME=?", new String[]{name});
        if (cursor.getCount() > 0) {
            return true;

        } else
            return false;
    }

    public Boolean checkUserPass(String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Acc WHERE USERNAME=? AND PASSWORD =?", new String[]{name, pass});
        if (cursor.getCount() > 0) {
            return true;

        } else
            return false;
    }

    public Cursor ViewList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Acc", null);
        return cursor;

    }
}

//    public Boolean getFullName(String username) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT FULLNAME FROM Acc WHERE USERNAME=?", new String[]{username});
//       if (cursor.getCount()>0){
//           return cursor;
//
//       } else {
//           return false;
//       }



