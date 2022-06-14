package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import kotlin.text.UStringsKt;

public class database extends SQLiteOpenHelper {

    public database(Context context) {
        super(context, "dpapp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String q = " CREATE TABLE USERS (Name TEXT ,Email TEXT primary key ,Password TEXT , Date TEXT, UDate TEXT)";
        String qu = " CREATE TABLE COMMENT (ID INTEGER primary key , Comment TEXT,Date TEXT, Email TEXT ,FOREIGN KEY(Email) REFERENCES USERS(Email)  )";
        db.execSQL(q);
        db.execSQL(qu);

    }


    public void addUsers(String email, String name , String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        if (getUserByEmail(email) !=null ){ //CHECK EMAIL EXISTS before adding!!!
            return;
        }
        data.put("Email" , email);
        data.put("Name" , name);
        data.put("Password" , password);
        data.put("Date" , new Date().toString());
        database.insert("USERS" , null , data);
        database.close();
    }

    public void UpdateUsers(String email, String name , String password){ //update User
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Name" , name);
        data.put("Password" , password);
        data.put("UDate" , new Date().toString());
        database.update("USERS" , data , "where email =?" , new String[]{email});
        database.close();
    }

    public void DeleteUsers(String email){ // delete users
        SQLiteDatabase database = this.getWritableDatabase();
       /** DeleteCommentofUser(email); // deletes user comment also**/
        database.delete("USERS" , "WHERE email = ?" , new String[] {email});
        database.close();
    }

    public void addComment(String comment, String email){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Comment" , comment);
        data.put("Email" , email);
        data.put("Date" , new Date().toString());
        database.insert("COMMENT" , null , data);
        database.close();
    }

    public ArrayList<Comment> getCommentByUser(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        //cursor for reading the data
        Cursor cmtCur = database.rawQuery("SELECT * FROM COMMENT WHERE email =?", new String[]{email});

        //creates a new array
        ArrayList<Comment> commentArrayList = parseCommentData(cmtCur);
        return commentArrayList; // returns user array list that we created above

    }

    public void UpdateComment(int id,String comment){ //update comment
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Comment" , comment);
        database.update("COMMENT" , data , "WHERE id = ?" , new String[]{id+""} );
        database.close();
    }

    public void DeleteComment(int id){ // delete comment
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("COMMENT" , "WHERE id = ?" , new String[] {id + ""});
        database.close();
    }

   /** public void DeleteCommentofUser(String email){ // delete comment
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("COMMENT" , "WHERE email = ?" , new String[] {email});
        database.close();
    }**/


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
    public Users getUserByEmail(String email) { //USED FOR LOGIN
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
                        userCur.getString(2),
                        userCur.getString(3),
                        userCur.getString(4)));
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
                        cmtCur.getInt(0), //send id
                        cmtCur.getString(1), //send comment
                        cmtCur.getString(2),
                        getUserByEmail( cmtCur.getString(3)))); //send user
            }

            while (cmtCur.moveToNext()); //moves next from 1st
        }

        cmtCur.close(); // closed cursor
        return commentArrayList; // returns user array list that we created above

    }

    public Comment getCommentById(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cmtCur = database.rawQuery("SELECT * FROM COMMENT WHERE id=?", new String[] {id + ""});
        if(cmtCur.getCount()>0){
            return parseCommentData(cmtCur).get(0);
        }
        else {
            return null;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // check if the table already exists nad drop if it does
        database.execSQL("DROP TABLE IF EXISTS USERS");
        database.execSQL("DROP TABLE IF EXISTS COMMENT");
        onCreate(database);
    }

}