package com.dbs.movement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private Toolbar mtoolbar;
    private Button ResetPasswordSendEmailButton;
    private EditText ResetEmailInput;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

    mAuth = FirebaseAuth.getInstance();


    ResetPasswordSendEmailButton = (Button) findViewById(R.id.reset_password_button);
    ResetEmailInput = (EditText) findViewById(R.id.reset_password_email);

    ResetPasswordSendEmailButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userEmail = ResetEmailInput.getText().toString();
            if (TextUtils.isEmpty(userEmail))
            {
                Toast.makeText(ResetPassword.this, "Please write valid email", Toast.LENGTH_SHORT).show();
            }
            else
            {
                mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ResetPassword.this, "Please check your email account", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                        }
                        else
                        {
                            String message = task.getException().getMessage();
                            Toast.makeText(ResetPassword.this,"error occured",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    });





    }
}
