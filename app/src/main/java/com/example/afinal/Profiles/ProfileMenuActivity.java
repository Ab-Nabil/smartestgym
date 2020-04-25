package com.example.afinal.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.R;
import com.example.afinal.WelcomeActivity;

public class ProfileMenuActivity extends AppCompatActivity {
    ImageView profilebackrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);

        profilebackrow=(ImageView)findViewById(R.id.profileBackRow);
        profilebackrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this,MainProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    public void activeLogout(View view) {
        Intent intent=new Intent(ProfileMenuActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
