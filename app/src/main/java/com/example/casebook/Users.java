package com.example.casebook;

public class Users {

    // for users
    public String name;
    public String email;
    public String passowrd;


    public String getName() { //getter
        return name;
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

    public Users(String name, String email, String passowrd) { //constructor
        this.name = name;
        this.email = email;
        this.passowrd = passowrd;
    }

}
