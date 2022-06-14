package com.example.casebook;

import javax.sql.StatementEvent;

public class Users {

    // for users
    public String name;
    public String email;
    public String passowrd;
    private String date;
    private String Udate;


    public String getName() { //getter
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getUdate() {
        return Udate;
    }

    public void setName(String name) { //setter
        this.name = name;
    }

    public String getEmail() {//getter
        return email;
    }

    public void setEmail(String email) {//setter
        this.email = email;
    }

    public String getPassowrd() {//getter
        return passowrd;
    }

    public void setPassowrd(String passowrd) {//setter
        this.passowrd = passowrd;
    }

    public Users(String name, String email, String passowrd, String date, String udate) {
        this.name = name;
        this.email = email;
        this.passowrd = passowrd;
        this.date = date;
        this.Udate = udate;
    }
}
