package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class OwnComment extends AppCompatActivity {

    private ArrayList<Comment> commentView = new ArrayList<>();
    private database dbs;
    private OwnCommentAdapter commentAdapter;
    private RecyclerView commentRV;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);
        email = getIntent().getStringExtra("email");
        dbs = new database(this); // initialize dbs
        commentView = dbs.getCommentByUser(email); // read users array

        // passing array list to the adapter

        commentAdapter = new OwnCommentAdapter(commentView, this);
        commentRV = findViewById(R.id.commentShow);

        //layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentRV.setLayoutManager(linearLayoutManager);
        commentRV.setAdapter(commentAdapter);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, OwnComment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
        finish();
    }
}