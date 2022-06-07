package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

    public ArrayList<Users> getUsers(){ // for reading all users from the table
        // creates a new database for retrieving the information
        SQLiteDatabase database = this.getReadableDatabase();

        //cursor for reading the data
        Cursor userCur = database.rawQuery("SELECT * FROM USERS", null);

        //creates a new array
        ArrayList<Users> usersArrayList = new ArrayList<>();

        if(userCur.moveToFirst()){ // moves cursor to 1st place
            do{
                usersArrayList.add(new Users(
                        userCur.getString(1),
                        userCur.getString(2),
                        userCur.getString(3)));
            }

            while (userCur.moveToNext()); //moves next from 1st
        }

        userCur.close(); // closed cursor
        return usersArrayList; // returns user array list that we created above
    }


    public Cursor showUsers(String email) { //ID ID ID
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("SELECT * FROM USERS WHERE email = ?" , new String[] {email});
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // check if the table already exists nad drop if it does
        database.execSQL("DROP TABLE IF EXISTS " +table);
        onCreate(database);
    }

}
