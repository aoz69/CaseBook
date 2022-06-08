package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText namereg,emailreg, passwordreg;
    private  Button register;
    private database dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         namereg = findViewById(R.id.nameReg);
         emailreg = findViewById(R.id.emailReg);
         passwordreg = findViewById(R.id.passwordReg);
         register = findViewById(R.id.reg);
        dbr = new database(RegisterActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailreg.getText().toString(); // get text user input in email
                String name = namereg.getText().toString();// get text user input in name
                String password= passwordreg.getText().toString();// get text user input in password
                if(dbr.getUserByEmail(email)!=null) { // check if email already exists
                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbr.addUsers(email , name , password);
                Toast.makeText(RegisterActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                emailreg.setText("");
                namereg.setText("");
                passwordreg.setText("");
                change(); // calling new activity
            }
        });

    }

    private void change() { // change activity function
        Intent intent = new Intent(this, ShowUsers.class); // changes activity to ShowUser
        startActivity(intent);
    }
}