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
    database database;
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
        Users users = database.getUserByEmail(getIntent().getStringExtra("email")); // id leko
        email.setText(users.getEmail());
        name.setText(users.getName());
        pass.setText(users.getPassowrd());
        date.setText(users.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dates = sdf.format(new Date());
        udate.setText(dates);

        edit.setOnClickListener(view -> {
            String newName = name.getText().toString();
            String newPass = pass.getText().toString();
            String newDate = date.getText().toString();
            String newUDate = udate.getText().toString();
            database.UpdateUsers(users.getEmail(), newName , newPass);
            Toast.makeText(this, "User data edited", Toast.LENGTH_SHORT).show();
        });
    }
}