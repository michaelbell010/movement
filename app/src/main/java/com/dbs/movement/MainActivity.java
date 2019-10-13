package com.dbs.movement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dbs.movement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        btnSignUp = findViewById(R.id.button);
        btnSignUp.setOnClickListener(view -> {
            String email = emailId.getText().toString();
            String pwd = password.getText().toString();
            if(email.isEmpty()){
                emailId.setError("Please enter email address");
                emailId.requestFocus();

            } else if (pwd.isEmpty()) {

                password.setError("Please enter password");
                password.requestFocus();

            }
            else if(email.isEmpty() && pwd.isEmpty())
            {
                Toast.makeText(MainActivity.this, "fields are empty!",Toast.LENGTH_SHORT).show();
            }
            else if(!(email.isEmpty() && pwd.isEmpty())){
                mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Signup Unsuccessful!", Toast.LENGTH_SHORT).show();

                    } else {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                });
            }
            else {
                Toast.makeText(MainActivity.this, "Error Ocurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
