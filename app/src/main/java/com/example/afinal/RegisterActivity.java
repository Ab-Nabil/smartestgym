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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    StorageReference storageReference;
    ProgressBar progressBar;
    FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    public String userID;
    public HashMap<String, String> user;
    Button mRegisterBtn;
    ImageView mProfilePic;
    TextView mLogin;
    EditText mUserName,mEmail,mPassword,mConfPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initiViews();
        progressBar = findViewById(R.id.regPrograssBar);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
//        storageReference = FirebaseStorage.getInstance().getReference();
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
        final String usernamevalue = mUserName.getText().toString();
        final String emailvalue = mEmail.getText().toString();
        String passwordvalue = mPassword.getText().toString();
        String confirmpasswordvalue = mConfPassword.getText().toString();

        //firebase email&password
        if (passwordvalue.length()>=6 && Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches()) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(emailvalue, passwordvalue)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
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
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "email not valid or Password <6 ", Toast.LENGTH_SHORT).show();
        }
        if (usernamevalue.isEmpty()){
            mUserName.setError("UserName required");
            mUserName.requestFocus();
            return;
        }
        FirebaseUser user = mAuth.getCurrentUser();

        if (user!=null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(usernamevalue).build()
                    ;
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){

                    }
                }
            });
        }

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
                Uri imageUrl = data.getData();
                mProfilePic.setImageURI(imageUrl);

                uplodaImage(imageUrl);
            }
        }
    }
//
//    private void uplodaImage(Uri imageUri) {
//        final StorageReference fileRef = storageReference.child("profile.jpg");
//        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Picasso.get().load(uri).into(mProfilePic);
//                    }
//                });
//            }
//        });
//    }
}
