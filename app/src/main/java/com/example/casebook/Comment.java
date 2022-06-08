package com.example.casebook;

public class Comment {

    //for comments
    public String comment;
    public String Name;
            ;
    public String getComment() {
        return comment;
    }


    public void setComment(String comment){
        this.comment = comment;
    }

    public String getName() {
        return comment;
    }


    public void setName(String Name){
        this.Name = Name;
    }

    public Comment(String string, String comment, String Name){
        this.comment = comment;
        this.Name = Name;
    }
}
