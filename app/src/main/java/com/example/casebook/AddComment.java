package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddComment extends AppCompatActivity {

    private EditText comment;
    private TextView welcome;
    private Button ShowOwnComment, ShowComment, ShowUsers,sharp, Account;
    private database dbr;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        comment = findViewById(R.id.commentTxt);
        sharp = findViewById(R.id.postt);
        welcome = findViewById(R.id.welcome);

        dbr = new database(AddComment.this); //creates new database object
        email = getIntent().getStringExtra("email"); //gets email from intent
        Users users = dbr.getUserByEmail(email);
        String name = users.name; //GETS NAME
        welcome.setText("Welcome " + name + "!"); //display name of the user who is logged in currently

        ShowUsers = findViewById(R.id.ShowUsers);
        ShowComment = findViewById(R.id.ShowComment);
        ShowOwnComment = findViewById(R.id.showOwnComment);
        Account = findViewById(R.id.Account);
        Account.setOnClickListener(view -> { //if account button is clicked call account function also send email we got from intent
            account(email);
        });
        sharp.setOnClickListener(view -> { // if the button is clicked call change function and send email
            String cmt = comment.getText().toString();// get comment user typed and convert to string
            dbr.addComment(cmt, email); //sends comment and email, adds that comment into database of respective user
            change(email);
        });
        if(name.equals("admin")) { //only show this button if the name of logged in user is admin
            ShowUsers.setVisibility(View.VISIBLE);
        }
        else{
            ShowUsers.setVisibility(View.INVISIBLE);//if it's not admin hide the button
        }

        ShowUsers.setOnClickListener(view -> { //calls user function when clicked on and sends email
            user(email);
        });

        ShowOwnComment.setOnClickListener(view -> {//calls oComment function when clicked on and sends email
            oComment(email);
        });
        ShowComment.setOnClickListener(view -> {//calls allComments function when clicked on and sends email
            allComment(email);
        });
    }

    private void account(String email) { //function for starting new activity
        Intent intent = new Intent (this, Account.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    private void allComment(String email) { //function for starting new activity
        Intent intent = new Intent (this, show_comment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    private void oComment(String email) {//function for starting new activity
        Intent intent = new Intent (this, OwnComment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    private void user(String email) {//function for starting new activity
        Intent intent = new Intent (this, ShowUsers.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }
    private void change(String email) { // change activity function
        Intent intent = new Intent(this, show_comment.class); // changes activity to ShowUser
        intent.putExtra("email" , email);
        startActivity(intent);
    }

    @Override
    protected void onRestart() { //refreshes the current page
        super.onRestart();
        Intent intent = new Intent(this, AddComment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
        finish();
    }
}