package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button ShowOwnComment;
    private Button ShowComment;
    private Button ShowUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ShowUsers = findViewById(R.id.ShowUsers);
        ShowComment = findViewById(R.id.ShowComment);
        ShowOwnComment = findViewById(R.id.ShowOwnComment);

        ShowUsers.setOnClickListener(view -> {
            user();
        });
        ShowOwnComment.setOnClickListener(view -> {
            oComment();
        });
        ShowComment.setOnClickListener(view -> {
            allComment();
        });
    }

    private void allComment() {
        Intent intent = new Intent (this, show_comment.class);
        startActivity(intent);
    }

    private void oComment() {
        Intent intent = new Intent (this, OwnComment.class);
        startActivity(intent);
    }

    private void user() {
        Intent intent = new Intent (this, ShowUsers.class);
        startActivity(intent);
        
    }
}