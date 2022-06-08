package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class TimeLine extends AppCompatActivity {


    public EditText comment;
    public Button post;
    public database dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comment = findViewById(R.id.commentTxt);
        post = findViewById(R.id.post);
        setContentView(R.layout.activity_time_line);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt = comment.getText().toString();
                dbr.addComment(cmt);
                change();
            }
        });
    }
    private void change() { // change activity function
        Intent intent = new Intent(this, ShowUsers.class); // changes activity to ShowUser
        startActivity(intent);
    }
}