package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditComment extends AppCompatActivity {

    private EditText edit;
    public database database;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comment);
        edit = findViewById(R.id.input);
        database = new database(EditComment.this);
        Comment cmt = database.getCommentById(getIntent().getIntExtra("id" , 0)); // id leko
        edit.setText(cmt.getComment());
        done = findViewById(R.id.EditBtn);
        done.setOnClickListener(view -> {
            String newCmt = edit.getText().toString();
            database.UpdateComment(cmt.getId(), newCmt);
            Toast.makeText(EditComment.this, "Comment edited", Toast.LENGTH_SHORT).show();

        });
    }
}