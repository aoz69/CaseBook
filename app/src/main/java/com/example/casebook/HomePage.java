package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button ShowOwnComment, ShowComment, ShowUsers, cmtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ShowUsers = findViewById(R.id.ShowUsers);
        ShowComment = findViewById(R.id.ShowComment);
        cmtAdd = findViewById(R.id.addComment);
        ShowOwnComment = findViewById(R.id.ShowOwnComment);
        Intent intent = new Intent();
        String email = intent.getStringExtra("email");
        ShowUsers.setOnClickListener(view -> {
            user(email);
        });
        ShowOwnComment.setOnClickListener(view -> {
            oComment(email);
        });
        ShowComment.setOnClickListener(view -> {
            allComment(email);
        });
        cmtAdd.setOnClickListener(view -> {
            addCmt(email);
        });
    }

    private void addCmt(String email) {
        Intent intent = new Intent(this, AddComment.class);
        startActivity(intent);
    }

    private void allComment(String email) {
        Intent intent = new Intent (this, show_comment.class);
        startActivity(intent);
    }

    private void oComment(String email) {
        Intent intent = new Intent (this, OwnComment.class);
        startActivity(intent);
    }

    private void user(String email) {
        Intent intent = new Intent (this, ShowUsers.class);

        startActivity(intent);
    }
}