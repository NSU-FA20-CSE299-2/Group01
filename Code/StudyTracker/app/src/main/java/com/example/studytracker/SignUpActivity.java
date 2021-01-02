package com.example.studytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText st_name, st_email, st_password;
    Button button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_);

        st_name = findViewById(R.id.signUpName);
        st_email = findViewById(R.id.signUpEmailAddress);
        st_password = findViewById(R.id.signUpPassword);
        button = findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = st_email.getText().toString().trim();
                String password = st_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    st_email.setError(("Email is required"));
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    st_email.setError("Enter a valid email address");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    st_password.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    st_password.setError("Minimum six in length is required");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Registered Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}