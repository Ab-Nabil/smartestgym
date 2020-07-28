package com.example.afinal.Profiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText mNewPass,mConfPass;
    Button mBtn;
    ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mNewPass = findViewById(R.id.new_pass);
        mConfPass = findViewById(R.id.new_conf_pass);
        mBtn = findViewById(R.id.new_pass_btn);
        mBack = findViewById(R.id.passBackRow);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String newPassword = mNewPass.getText().toString();
                if (newPassword.isEmpty()) {
                    mNewPass.setError("Enter New Password");
                }else if(!mNewPass.getText().toString().equals(mConfPass.getText().toString())){
                    mConfPass.setError("Passwords are not matching!");
                }else if (mNewPass.length()<6){
                    mNewPass.setError("Password is weak");
                }
                else {
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangePasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ChangePasswordActivity.this,MainProfileActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(ChangePasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}