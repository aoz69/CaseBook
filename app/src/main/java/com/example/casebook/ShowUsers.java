package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowUsers extends AppCompatActivity {

    private ArrayList<Users> usersView;
    private database dbs;
    private userRecycleView userAdapter;
    private RecyclerView userRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        usersView = new ArrayList<>(); //initialize userView
        dbs = new database(ShowUsers.this); // initialize dbs

        usersView = dbs.getUsers(); // read users array
        // passing array list to the adapter
        userAdapter = new userRecycleView(usersView,ShowUsers.this);
        userRV = findViewById(R.id.userShow);

        //layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowUsers.this, RecyclerView.VERTICAL, false);
        userRV.setLayoutManager(linearLayoutManager);
    }
}