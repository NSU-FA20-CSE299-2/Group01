package com.example.signin_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity  {
    EditText medit_Email;
    Button mproceed_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medit_Email = findViewById(R.id.edit_email);
        mproceed_button = findViewById(R.id.proceed_button);
        mAuth = FirebaseAuth.getInstance();

        mproceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogInWithPasswordActivity.class));
            }
        });

    }
}