package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    database db;
    TextView register;
    EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = findViewById(R.id.submit); //finds button from xml file
        register = findViewById(R.id.RegHere); //finds text view from xml
        db = new database(this); //database object creation
        email = findViewById(R.id.emailInput); //finds text view from xml
        pass = findViewById(R.id.passwordInput);//finds text view from xml

        register.setOnClickListener(new View.OnClickListener() { //on register button press:
            @Override
            public void onClick(View view) {
                reg();
            } //call register function which is to change activity
        });

        submit.setOnClickListener(new View.OnClickListener() {//on submit button press:
            @Override
            public void onClick(View view) {
                String em = email.getText().toString(); //get email content user typed and convert to string
                String pas = pass.getText().toString();//get password content user typed and convert to string
                Users user = db.getUserByEmail(em);//get users by primarykey i.e email and store in user

                if(user==null){ //checks if email matches with database, if it doesn't throws an error
                    Toast.makeText(MainActivity.this, "Email invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!pas.equals(user.getPassowrd())){//checks if pass matches with database, if it doesn't throws an error
                    Toast.makeText(MainActivity.this, "password invalid", Toast.LENGTH_SHORT).show();
                    Log.e("", "onClick: " + user.getPassowrd());
                    return;
                }

                change(em); // calls change function and sends email of database to change


            }
        });
    }

    private void reg() { //reg function to change activity
        Intent intent = new Intent(this,RegisterActivity.class); //creates new intent to change activity
        startActivity(intent); //change activity
    }

    private void change(String email) {
        Intent intent = new Intent(this, AddComment.class);
        intent.putExtra("email" , email);
        startActivity(intent);
    }
}