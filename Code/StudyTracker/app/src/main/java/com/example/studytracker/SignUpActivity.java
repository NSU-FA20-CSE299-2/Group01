package com.example.studytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText st_name, st_email, st_password;
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButton;
    FirebaseAuth mAuth;
    FirebaseFirestore fireStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_);

        st_name = findViewById(R.id.signUpName);
        st_email = findViewById(R.id.signUpEmailAddress);
        st_password = findViewById(R.id.signUpPassword);
        button = findViewById(R.id.signUpButton);
        radioGroup = findViewById(R.id.rg_gender);

        mAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = st_name.getText().toString().trim();
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
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fireStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", name);
                            user.put("Email", email);
                            user.put("Gender", radioButton.getText());
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Log.d(TAG, "onSuccess: User profile created for" + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }
}