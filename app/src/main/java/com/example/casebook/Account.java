package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account extends AppCompatActivity {
    database database; //database variable of database type
    Button edit;
    TextView email, date, udate;
    EditText name,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        database = new database(Account.this);
        edit = findViewById(R.id.button);
        email = findViewById(R.id.emailPlaceholder);
        name = findViewById(R.id.namePlaceholder);
        pass = findViewById(R.id.passwordPlaceholder);
        date = findViewById(R.id.dateViewholder);
        udate = findViewById(R.id.UdateViewholder);
        Users users = database.getUserByEmail(getIntent().getStringExtra("email")); // get email from intent as we passed in the before page
        email.setText(users.getEmail()); //sets text view content to email of the person logged in
        name.setText(users.getName());//sets text view content to name of the person logged in
        pass.setText(users.getPassowrd());//sets text view content to password of the person logged in
        date.setText(users.getDate()); //sets text view content to the actual date of user register
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //date format to day month and year
        String dates = sdf.format(new Date()); //date with given format
        udate.setText(dates); //sets text view content to the actual date of user updated

        edit.setOnClickListener(view -> { // do following on click:
            String newName = name.getText().toString();
            String newPass = pass.getText().toString();
            String newDate = date.getText().toString();
            String newUDate = udate.getText().toString();
            database.UpdateUsers(users.getEmail(), newName , newPass); //update database with given updated data
            Toast.makeText(this, "User data edited", Toast.LENGTH_SHORT).show(); //shows a message saying "user data edited"
        });
    }
}