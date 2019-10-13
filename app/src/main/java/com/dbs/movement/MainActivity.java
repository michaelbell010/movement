package com.dbs.movement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dbs.movement.R;
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
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email address");
                    emailId.requestFocus();

                } else if (pwd.isEmpty()) {

                    password.setError("Please enter password");
                    password.requestFocus();

                }
                else if(emailId.isEmpty() && pwd.isEmpty()){
                    Toast.makeText( context: MainActivity.this, text "fields are empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
