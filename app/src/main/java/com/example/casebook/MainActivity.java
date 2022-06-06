package com.example.casebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = findViewById(R.id.submit);
        register = findViewById(R.id.RegHere);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();

            }
        });
    }

    private void reg() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void change() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}