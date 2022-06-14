package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddComment extends AppCompatActivity {


    public EditText comment;
    public Button sharp;
    public database dbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        comment = findViewById(R.id.commentTxt);
        sharp = findViewById(R.id.postt);
        dbr=new database(AddComment.this);
        String email = getIntent().getStringExtra("email");
        Users users = dbr.getUserByEmail(email);
        String name = users.name; //GETS NAME
        sharp.setOnClickListener(view -> {
            String cmt = comment.getText().toString();
            dbr.addComment(cmt, email);
            change();
        });
    }
    private void change() { // change activity function
        Intent intent = new Intent(this, show_comment.class); // changes activity to ShowUser

        startActivity(intent);
    }
}