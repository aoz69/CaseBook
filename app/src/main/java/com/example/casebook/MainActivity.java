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
        Button submit = findViewById(R.id.submit);
        register = findViewById(R.id.RegHere);
        db = new database(this);
        email = findViewById(R.id.emailInput);
        pass = findViewById(R.id.passwordInput);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pas = pass.getText().toString();
                Users user = db.getUserByEmail(em);

                if(user==null){
                    Toast.makeText(MainActivity.this, "Email invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!pas.equals(user.getPassowrd())){
                    Toast.makeText(MainActivity.this, "password invalid", Toast.LENGTH_SHORT).show();
                    Log.e("", "onClick: " + user.getPassowrd());
                    return;
                }

                change();


            }
        });
    }

    private void reg() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void change() {
        Intent intent = new Intent(this, TimeLine.class);
        startActivity(intent);
    }
}