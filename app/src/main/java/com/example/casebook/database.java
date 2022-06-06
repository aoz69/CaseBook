package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    private static final String databaseName = "dbapp";

    private static final int ver = 1;

    private static final String table = "USERS";

    private static final String for_id = "id";

    private static final String for_name ="Name";

    private static final String for_email = "Email";

    private static final String for_pass = "Password";

    public database(Context context) {
        super(context, databaseName, null, ver);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String q = " CREATE TABLE USERS" +
                "(" + for_name + " TEXT ," + for_email + " TEXT, " + for_pass  + " TEXT )";

        db.execSQL(q);
    }


    public void addUsers(String email, String name , String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(for_email , email);
        data.put(for_name , name);
        data.put(for_pass , password);

        database.insert(table , null , data);
        database.close();
    }


    public Cursor showUsers(String email) { //ID ID ID
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("SELECT * FROM USERS WHERE email = ?" , new String[] {email});
    }



    /*
    cursor lai read garna
    database db = new database(this);
    Cursor cursor = db.showUsers(String email);
    (VARIABLE)cursor.movetofirst();
    name = cursor.getString(cursor.getColulmnIndexOrThrow("Name"); //name leu
    */


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // check if the table already exists nad drop if it does
        database.execSQL("DROP TABLE IF EXISTS " +table);
        onCreate(database);
    }

}
