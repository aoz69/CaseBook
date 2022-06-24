package com.example.casebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class database extends SQLiteOpenHelper {

    public database(Context context) {
        super(context, "dpapp", null, 1);
    } //database name and version declared

    @Override
    public void onCreate(SQLiteDatabase db){
        //query for creating table named USERS with name,email,password and dates as attribute, email being primary key
        String q = " CREATE TABLE USERS (Name TEXT ,Email TEXT primary key ,Password TEXT , Date TEXT, UDate TEXT)";
        //query for creating table named COMMENT with id,comment,date,name and email being foreign key
        String qu = " CREATE TABLE COMMENT (ID INTEGER primary key , Comment TEXT,Date TEXT, Email TEXT,Date TEXT, UDate TEXT ,FOREIGN KEY(Email) REFERENCES USERS(Email))";
        db.execSQL(q); //run q query
        db.execSQL(qu);//run qu query
    }

    //for adding new users
    public void addUsers(String email, String name , String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        if (getUserByEmail(email) !=null ){ //CHECK EMAIL EXISTS before adding!!!
            return;
        }
        data.put("Email" , email); //puts values in database table for email
        data.put("Name" , name);//puts values in database table for name
        data.put("Password" , password);//puts values in database table for password
        data.put("Date" , date);//puts values in database table for date
        database.insert("USERS" , null , data); //runs insert query to add those data in table
        database.close(); //closes database
    }
    //for updating existing users
    public void UpdateUsers(String email, String name , String password){ //update User
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        data.put("Name" , name);
        data.put("Password" , password);
        data.put("UDate" , date);
        database.update("USERS" , data , "email =?" , new String[]{email});//runs update query to update those data in table whose email matches
        database.close();
    }
    //for deleting users
    public void DeleteUsers(String email){ // delete users
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("USERS" , "email = ?" , new String[] {email});//runs delete query to delete all data of the selected id
        database.close();
    }
    //for adding new comment
    public void addComment(String comment, String email){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        data.put("Comment" , comment);
        data.put("Email" , email);
        data.put("Date" , date);
        database.insert("COMMENT" , null , data);
        database.close();
    }

    public ArrayList<Comment> getCommentByUser(String email){
        SQLiteDatabase database = this.getWritableDatabase(); //to write data in out database
        //cursor for reading the data
        Cursor cmtCur = database.rawQuery("SELECT * FROM COMMENT WHERE email =?", new String[]{email}); //selects table row with email we want

        //creates a new array
        ArrayList<Comment> commentArrayList = parseCommentData(cmtCur);
        return commentArrayList; // returns user array list that we created above
    }

    public void UpdateComment(int id,String comment){ //update comment
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Comment" , comment);
        database.update("COMMENT" , data , "id =?" , new String[]{id+""} );
        database.close();
    }

    public void DeleteComment(int id){ // delete comment
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("COMMENT" , "id =?" , new String[] {id + ""});
        database.close();
    }

   public void DeleteCommentofUser(String email){ // delete comment
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("COMMENT" , "email = ?" , new String[] {email});//deletes comments of specific users
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

    public ArrayList<Users> parseUserData(Cursor userCur) {
        //creates a new array
        ArrayList<Users> usersArrayList = new ArrayList<>();

        if(userCur.moveToFirst()){ // moves cursor to 1st place
            do{
                usersArrayList.add(new Users(
                        userCur.getString(0), //id
                        userCur.getString(1), //name
                        userCur.getString(2), //email
                        userCur.getString(3), //password
                        userCur.getString(4))); //date
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
        Cursor cmtCur = database.rawQuery("SELECT * FROM COMMENT WHERE id=?", new String[] {id + ""}); //gets every data of comment table who has id given as wanted
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