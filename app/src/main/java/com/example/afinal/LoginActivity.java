package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.Profiles.MainProfileActivity;
public class LoginActivity extends AppCompatActivity {

    public String username;
    //Declaration EditTexts
    EditText loginEmail, loginPassword;
    //Declaration Button
    Button buttonLogin;
    TextView signUpText;


    //Get values from EditText fields
    String emailValue;
    String passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        signUpText = findViewById(R.id.signUpText);

        //this method used to set signup TextView click event
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(validate ()){

                    Toast toast=Toast. makeText(LoginActivity.this,"Successfully LogIn",Toast. LENGTH_SHORT);
                    toast.show ();
                    //User Logged in Successfully Launch You home screen activity
                    Intent intent=new Intent(LoginActivity.this, MainProfileActivity.class);
                    startActivity(intent);
                    //finish();
                }
               /* else {
                    Toast toast=Toast. makeText(LoginActivity.this,"Email or Password are not valid !",Toast. LENGTH_SHORT);
                    toast.show ();
                }
                */

            }
        } );
    }
    private boolean validate() {
        boolean valid = false;
        //Get values from EditText fields
        emailValue = loginEmail.getText().toString();
        passwordValue = loginPassword.getText().toString();

        //Handling validation for Valid Email field
        if (emailValue.isEmpty()||passwordValue.isEmpty()) {
            valid = false;
            if (emailValue.isEmpty())
            loginEmail.setError("Empty Email!");
            if (passwordValue.isEmpty())
            loginPassword.setError("Empty password!");
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            valid = false;
            loginEmail.setError("Please enter valid email!");
        }

        else {
                if (passwordValue.length() >7) {
                    valid = true;
                    loginPassword.setError(null);
                    loginEmail.setError(null);
                } else {
                    valid = false;
                    loginPassword.setError("Password is to short!");
                }
            }
        return valid;
    }
}
