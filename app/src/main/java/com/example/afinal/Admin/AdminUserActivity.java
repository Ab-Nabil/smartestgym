package com.example.afinal.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.afinal.R;
import com.google.firebase.firestore.UserDataReader;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUserActivity extends AppCompatActivity {
    TextView mUsernameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        mUsernameTV = findViewById(R.id.username_admin_user);
        Intent intent = getIntent();
        mUsernameTV.setText(intent.getStringExtra("name"));
    }
}
