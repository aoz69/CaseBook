package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;

public class show_comment extends AppCompatActivity {

    private ArrayList<Comment> commentView = new ArrayList<>();
    private database dbs;
    private CommentAdapter commentAdapter;
    private RecyclerView commentRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);
        String email = getIntent().getStringExtra("email");
        dbs = new database(show_comment.this); // initialize dbs

        commentView = dbs.readComment(); // read users array
        // passing array list to the adapter

        commentAdapter = new CommentAdapter(commentView, this);
        commentRV = findViewById(R.id.commentShow);

        //layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentRV.setLayoutManager(linearLayoutManager);
        commentRV.setAdapter(commentAdapter);
    }
}