package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.CreateProfile.CreateGenderActivity;
import com.google.gson.Gson;


public class RegisterActivity extends AppCompatActivity {
    EditText register_username, register_email, register_password, register_confirmpassword;

    //for passing Object
    User user = new User();
    Gson gson = new Gson();
    String userDO = gson.toJson(user);

    //Declaration ImageButton
    ImageButton register_profilePic;
    private static final int GALLERY_CODE = 1;
    private Uri mImageUri;
    //Declaration Button
    Button register_buttoncreateaccount;
    TextView register_login;

    //private FirebaseAuth sgAuth;

    //get values from EditText fields
    String usernamevalue;
    String emailvalue;
    String passwordvalue;
    String confirmpasswordvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //this method is used to connect XML views to its Objects
        register_confirmpassword = findViewById(R.id.register_confirmPassword);
        register_username = findViewById(R.id.register_userName);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_buttoncreateaccount = findViewById(R.id.register_buttonCreateAccount);
        register_profilePic = findViewById(R.id.register_profilePic);

        //this method used to set Login TextView click event
        register_login = findViewById(R.id.register_login);
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //to upload picture from gallery
        register_profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        register_buttoncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    user.setUserEmail(emailvalue);
                    user.setUserName(usernamevalue);
                    user.setUserPassword(passwordvalue);
        /*            sgAuth.createUserWithEmailAndPassword(emailvalue, passwordvalue)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("done", "createUserWithEmail:success");
                                        FirebaseUser user = sgAuth.getCurrentUser();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("fail", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

         */
                    Toast toast = Toast.makeText(RegisterActivity.this, "Successfully SignUp", Toast.LENGTH_SHORT);
                    toast.show();
                    //User signed up Successfully Launch You home screen activity
                    Intent intent = new Intent(RegisterActivity.this, CreateGenderActivity.class);
                    intent.putExtra("userRO", userDO);
                    startActivity(intent);
                    //finish();
                } else if (!Validate()) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "UnSuccessfully SignUp", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
        );
    }


    private boolean Validate() {
        boolean valid;
        usernamevalue = register_username.getText().toString();
        emailvalue = register_email.getText().toString();
        passwordvalue = register_password.getText().toString();
        confirmpasswordvalue = register_confirmpassword.getText().toString();

        if (usernamevalue.isEmpty() || emailvalue.isEmpty() || passwordvalue.isEmpty() || confirmpasswordvalue.isEmpty()) {
            valid = false;
            if (usernamevalue.isEmpty())
                register_username.setError("Empty value");
            if (emailvalue.isEmpty())
                register_email.setError("Empty value");
            if (passwordvalue.isEmpty())
                register_password.setError("Empty value");
            if (confirmpasswordvalue.isEmpty())
                register_confirmpassword.setError("Empty value");
            return valid;
        } else {
            if (usernamevalue.length() <= 4) {
                valid = false;
                register_username.setError("short username < 5");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches()) {
                valid = false;
                register_email.setError("Please enter valid email!");
            } else if (!passwordvalue.equals(confirmpasswordvalue)) {
                register_confirmpassword.setError("Passwords are not matching!");
                register_confirmpassword.setFocusable(true);
                valid = false;
                register_password.setError("Passwords are not matching!");
            } else if (passwordvalue.length() <= 7) {
                valid = false;
                register_password.setError("short password < 8");
            }
            else {
                if (!passwordvalue.matches("(?=.*[0-9]).*")) {
                    valid = false;
                    register_password.setError("password should contain digits");
                }
                else if (!passwordvalue.matches("(?=.*[a-z]).*")) {
                    valid = false;
                    register_password.setError("password should contain lower case letter");
                }
                 else if (!passwordvalue.matches("(?=.*[A-Z]).*")) {
                    valid = false;
                    register_password.setError("password should contain upper case letter");
                }
                else if (!passwordvalue.matches("(?=.*[~!@#$%^&*()_/]).*")) {
                    valid = false;
                    register_password.setError("password should contain special character letter");
                }
                else {
                    valid = true;

                }
            }
        }

        return valid;
    }
}