package com.example.xanderjuice.guitarstudentsignup_app;

/**
 * Created by Andrew Gaines
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// extending sqliteOpenHelper to manage database creation and version management
public class DatabaseAssistant extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Guitar_Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "LESSON_DAY";
    public static final String COL_4 = "LESSON_TIME";
    public static final String COL_5 = "PAYMENT";
    public DatabaseAssistant(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // creates a table with specified columns bellow
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,LESSON_DAY TEXT,LESSON_TIME TEXT,PAYMENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(database);
    }

    // function that inserts the given parameters to database and returns true if inserted
    public boolean insertData(String name,String lessonDay,String lessonTime, String payment) {
        SQLiteDatabase database = this.getWritableDatabase();

        //using content values to store key value pairs
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,lessonDay);
        contentValues.put(COL_4,lessonTime);
        contentValues.put(COL_5, payment);
        long result = database.insert(TABLE_NAME,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // function that deletes a student based on the Id passed to the function
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public boolean updateData(String id,String name,String lessonDay,String lessonTime, String payment) {
        SQLiteDatabase db = this.getWritableDatabase();

        //using content values to store key value pairs
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,lessonDay);
        contentValues.put(COL_4,lessonTime);
        contentValues.put(COL_5, payment);

        // passing the  content values into the the database
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    // using a cursor to traverse the database table to retrieve students
    public Cursor getStudents() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor students = database.rawQuery("select * from "+TABLE_NAME,null);
        return students;
    }
}