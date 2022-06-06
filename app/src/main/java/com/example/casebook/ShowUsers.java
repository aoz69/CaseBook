package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShowUsers extends AppCompatActivity {

    private RecyclerView usersShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        usersShow = findViewById(R.id.userShow);


    }
}