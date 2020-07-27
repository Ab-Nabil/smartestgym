package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.Admin.MainAdminActivity;
import com.example.afinal.Profiles.MainProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText mEmail,mPassword;
    TextView mRegister;
    Button mLogin;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.loginEmail);
        mPassword = findViewById(R.id.loginPassword);
        mLogin = findViewById(R.id.buttonLogin);
        mRegister = findViewById(R.id.signUpText);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        progressBar = findViewById(R.id.logPrograssBar);

    }

    private void userLogin() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (mEmail.getText().toString().isEmpty()){
            mEmail.setError("please enter your email");
        }
        else if (password.isEmpty()){
            mPassword.setError("please enter your password");
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                        Intent intent = new Intent(LoginActivity.this, MainProfileActivity.class);
                        //flag for clear so user can't go to login again after login
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                if (mEmail.getText().toString().equals("admin@admin.com")) {
                    Intent intent1 = new Intent(LoginActivity.this, MainAdminActivity.class);
                    //flag for clear so user can't go to login again after login
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);
                }else {
                    userLogin();
                }
                break;
            case R.id.signUpText:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
