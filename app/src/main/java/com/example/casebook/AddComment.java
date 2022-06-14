package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddComment extends AppCompatActivity {

    private EditText comment;
    private TextView welcome;
    private Button ShowOwnComment, ShowComment, ShowUsers,sharp;
    private database dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        comment = findViewById(R.id.commentTxt);
        sharp = findViewById(R.id.postt);
        welcome = findViewById(R.id.welcome);
        dbr = new database(AddComment.this);
        ShowUsers = findViewById(R.id.ShowUsers);
        ShowComment = findViewById(R.id.ShowComment);
        ShowOwnComment = findViewById(R.id.showOwnComment);

        String email = getIntent().getStringExtra("email");
        Users users = dbr.getUserByEmail(email);
        String name = users.name; //GETS NAME
        welcome.setText("Welcome " + name + "!");

        sharp.setOnClickListener(view -> {
            String cmt = comment.getText().toString();
            dbr.addComment(cmt, email);
            change(email);
        });

        ShowUsers.setOnClickListener(view -> {
            user(email);
        });
        ShowOwnComment.setOnClickListener(view -> {
            oComment(email);
        });
        ShowComment.setOnClickListener(view -> {
            allComment(email);
        });
    }

    private void allComment(String email) {
        Intent intent = new Intent (this, show_comment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    private void oComment(String email) {
        Intent intent = new Intent (this, OwnComment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    private void user(String email) {
        Intent intent = new Intent (this, ShowUsers.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }
    private void change(String email) { // change activity function
        Intent intent = new Intent(this, show_comment.class); // changes activity to ShowUser
        intent.putExtra("email" , email);
        startActivity(intent);
    }
}