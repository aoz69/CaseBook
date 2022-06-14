package com.example.casebook;

public class Comment {

    //for comments
    private String comment;
    private Users user;
    private int id;
    private String date;

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getName() {
        return user.getName();
    }


    public Comment(int id,String comment,String date, Users user){
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.user = user;
    }
}
