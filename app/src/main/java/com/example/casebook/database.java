package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public database(Context context) {
        super(context, "dpapp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String q = " CREATE TABLE USERS (Name TEXT ,Email TEXT primary key ,Password TEXT)";
        String qu = " CREATE TABLE COMMENT (ID TEXT primary key , Name TEXT , Comment TEXT, Email TEXT ,FOREIGN KEY(Email) REFERENCES USERS(Email)  )";
        db.execSQL(q);
        db.execSQL(qu);

    }

    /**
     * CHECK EMAIL EXISTS WITH getUserBYEmail() before adding!!!
     * @param email
     * @param name
     * @param password
     */

    public void addUsers(String email, String name , String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Email" , email);
        data.put("Name" , name);
        data.put("Password" , password);
        database.insert("USERS" , null , data); //TODO: insert lai update banau add lau update banau
        database.close();
    }

    public void addComment(String comment, String email){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Comment" , comment);
//        data.put("Email" , email);
        database.insert("COMMENT" , null , data); //TODO: insert lai update banau add lau update banau
        database.close();
    }

    public ArrayList<Users> readUsers(){ // for reading all users from the table
        // creates a new database for retrieving the information
        SQLiteDatabase database = this.getReadableDatabase();

        //cursor for reading the data
        Cursor userCur = database.rawQuery("SELECT * FROM USERS", null);

        //creates a new array
        ArrayList<Users> usersArrayList = parseUserData(userCur);

        return usersArrayList; // returns user array list that we created above
    }

    public ArrayList<Comment> readComment(){ // for reading all users from the table
        // creates a new database for retrieving the information
        SQLiteDatabase database = this.getReadableDatabase();

        //cursor for reading the data
        Cursor cmtCur = database.rawQuery("SELECT * FROM COMMENT", null);

        //creates a new array
        ArrayList<Comment> commentArrayList = parseCommentData(cmtCur);
        return commentArrayList; // returns user array list that we created above
    }

    /**
     * USED FOR LOGIN
     * @param email
     * @return
     */

    public Users getUserByEmail(String email) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor userCur = database.rawQuery("SELECT * FROM USERS WHERE email=?", new String[] {email});
        if(userCur.getCount()>0){
        return parseUserData(userCur).get(0);
        }
        else {
            return null;
        }
    }

    /**
     *
     * @param userCur
     * @return array list of users from that cursor
     *  USE THIS METHOD TO PARSE ALL THE DATA FROM CURSOR
     */

    public ArrayList<Users> parseUserData(Cursor userCur) {
        //creates a new array
        ArrayList<Users> usersArrayList = new ArrayList<>();
        ArrayList<Users> commentArrayList = new ArrayList<>();

        if(userCur.moveToFirst()){ // moves cursor to 1st place
            do{
                usersArrayList.add(new Users(
                        userCur.getString(0),
                        userCur.getString(1),
                        userCur.getString(2)));
            }

            while (userCur.moveToNext()); //moves next from 1st
        }

        userCur.close(); // closed cursor
        return usersArrayList; // returns user array list that we created above
    }


    public ArrayList<Comment> parseCommentData(Cursor cmtCur) {
        //creates a new array
        ArrayList<Comment> commentArrayList = new ArrayList<>();

        if(cmtCur.moveToFirst()){ // moves cursor to 1st place
            do{
                commentArrayList.add(new Comment(
                        cmtCur.getString(0),
                        cmtCur.getString(1),
                        cmtCur.getString(2)));
            }

            while (cmtCur.moveToNext()); //moves next from 1st
        }

        cmtCur.close(); // closed cursor
        return commentArrayList; // returns user array list that we created above
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // check if the table already exists nad drop if it does
        database.execSQL("DROP TABLE IF EXISTS USERS");
        database.execSQL("DROP TABLE IF EXISTS COMMENT");
        onCreate(database);
    }

}