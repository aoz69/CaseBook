package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
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
        String q = " CREATE TABLE " +table +
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
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        database.execSQL("DROP TABLE IF EXISTS " +table);
        onCreate(database);
    }

}
