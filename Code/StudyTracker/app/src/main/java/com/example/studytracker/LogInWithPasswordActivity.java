package com.example.studytracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LogInWithPasswordActivity extends AppCompatActivity {

    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_with_password);

        label = findViewById(R.id.logInEmailAddress);

        
    }
}