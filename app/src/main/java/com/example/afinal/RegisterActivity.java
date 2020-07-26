package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.CreateProfile.CreateGenderActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    StorageReference storageReference;
    Uri imageUrl;
    ProgressBar progressBar;
    FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    public String userID;
    public HashMap<String, String> user;
    Button mRegisterBtn;
    ImageView mProfilePic;
    TextView mLogin;
    EditText mUserName,mEmail,mPassword,mConfPassword;
    String usernamevalue;
    String emailvalue;
    String passwordvalue;
    String confirmpasswordvalue;
    boolean imageChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initiViews();
        progressBar = findViewById(R.id.regPrograssBar);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        mProfilePic.setTag("0");
        imageChanged = false;

    }

    private void initiViews() {
        mProfilePic = findViewById(R.id.register_profilePic);
        mLogin = findViewById(R.id.register_login);
        mRegisterBtn = findViewById(R.id.register_buttonCreateAccount);
        mUserName = findViewById(R.id.register_userName);
        mEmail = findViewById(R.id.register_email);
        mPassword = findViewById(R.id. register_password);
        mConfPassword = findViewById(R.id.register_confirmPassword);

        mRegisterBtn.setOnClickListener(this);

        mLogin.setOnClickListener(this);
        mProfilePic.setOnClickListener(this);

    }

    private void registerUser(){
        usernamevalue = mUserName.getText().toString();
        String emailvalue = mEmail.getText().toString();
         passwordvalue = mPassword.getText().toString();
         confirmpasswordvalue = mConfPassword.getText().toString();

        //firebase email&password
//        if (passwordvalue.length()>=6 && Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches()) {
        if (Validate()) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(emailvalue, passwordvalue)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //upload image
                        uplodaImage(imageUrl);
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                        Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        userID = mAuth.getCurrentUser().getUid();
                        Intent intent = new Intent(RegisterActivity.this, CreateGenderActivity.class);
                        intent.putExtra("username",usernamevalue);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
//        else {
//            Toast.makeText(this, "email not valid or Password <6 ", Toast.LENGTH_SHORT).show();
//
//        }
        else if (!Validate()) {
            Toast toast = Toast.makeText(RegisterActivity.this, "UnSuccessfully SignUp", Toast.LENGTH_SHORT);
        }
        else {
            Toast toast = Toast.makeText(RegisterActivity.this, "UnSuccessfully SignUp", Toast.LENGTH_SHORT);
        }
//        if (usernamevalue.isEmpty()){
//            mUserName.setError("UserName required");
//            mUserName.requestFocus();
//            return;
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_buttonCreateAccount:
                registerUser();

                break;

            case R.id.register_profilePic:
                Intent gallaryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallaryIntent,1000);
                break;

            case R.id.register_login:
                finish();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode== Activity.RESULT_OK){
                imageUrl = data.getData();
                mProfilePic.setImageURI(imageUrl);
                imageChanged = true;

            }
        }
    }
    private void uplodaImage(Uri imageUri) {
        final StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(mProfilePic);
                    }
                });
            }
        });
    }
    private boolean Validate() {
        boolean valid;
        usernamevalue = mUserName.getText().toString();
        emailvalue = mEmail.getText().toString();
        passwordvalue = mPassword.getText().toString();
        confirmpasswordvalue = mConfPassword.getText().toString();

        if (usernamevalue.isEmpty() || emailvalue.isEmpty() || passwordvalue.isEmpty() || confirmpasswordvalue.isEmpty()) {
            valid = false;
            if (usernamevalue.isEmpty())
                mUserName.setError("Empty value");
            if (emailvalue.isEmpty())
                mEmail.setError("Empty value");
            if (passwordvalue.isEmpty())
                mPassword.setError("Empty value");
            if (confirmpasswordvalue.isEmpty())
                mConfPassword.setError("Empty value");
            return valid;
        } else {
            if (usernamevalue.length() <= 4) {
                valid = false;
                mUserName.setError("short username < 5");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches()) {
                valid = false;
                mEmail.setError("Please enter valid email!");
            } else if (!passwordvalue.equals(confirmpasswordvalue)) {
                mConfPassword.setError("Passwords are not matching!");
                mConfPassword.setFocusable(true);
                valid = false;
                mPassword.setError("Passwords are not matching!");
            } else if (passwordvalue.length() <= 7) {
                valid = false;
                mPassword.setError("short password < 8");
            }
            else {
                if (!passwordvalue.matches("(?=.*[0-9]).*")) {
                    valid = false;
                    mPassword.setError("password should contain digits");
                }
                else if (!passwordvalue.matches("(?=.*[a-z]).*")) {
                    valid = false;
                    mPassword.setError("password should contain lower case letter");
                }
                else if (!passwordvalue.matches("(?=.*[A-Z]).*")) {
                    valid = false;
                    mPassword.setError("password should contain upper case letter");
                }
                else if (!passwordvalue.matches("(?=.*[~!@#$%^&*()_/]).*")) {
                    valid = false;
                    mPassword.setError("password should contain special character letter");
                }
                else if(!imageChanged){
                    valid = false;
                    Toast.makeText(this, "Please Select Profile Picture", Toast.LENGTH_SHORT).show();
                }
                else {
                    valid = true;
                }
            }
        }
        return valid;
    }
}
