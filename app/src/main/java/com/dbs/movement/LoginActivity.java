package com.dbs.movement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.dbs.movement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.InternalTokenProvider;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    TextView forgotpassword;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(LoginActivity.this, "Firebase Connection Success", Toast.LENGTH_LONG);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        btnSignIn = findViewById(R.id.button);
        tvSignUp = findViewById(R.id.textview);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }

            }

        };
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPassword.class));

            }
        });


        btnSignIn.setOnClickListener(view -> {
            String email = emailId.getText().toString();
            String pwd = password.getText().toString();
            if (email.isEmpty()) {
                emailId.setError("Please enter email address");
                emailId.requestFocus();

            } else if (pwd.isEmpty()) {

                password.setError("Please enter password");
                password.requestFocus();

            } else if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(LoginActivity.this, "fields are empty!", Toast.LENGTH_SHORT).show();
            } else if (!(email.isEmpty() && pwd.isEmpty())) {
                mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, task -> {

                    if (!task.isSuccessful())
                        Toast.makeText(LoginActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();

                    else {
                        Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intToHome);
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "Error Ocurred!", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intSignUp);
            }
        });
    }

        @Override
         protected void onStart() {
            super.onStart();
            mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        }
    }




