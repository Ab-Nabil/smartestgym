package com.example.afinal.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.R;

public class SettingMenuActivity extends AppCompatActivity {
    ImageView settingbackrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
        settingbackrow=(ImageView)findViewById(R.id.settingBackRow);
        settingbackrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingMenuActivity.this,MainProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openFitLev(View view) {
        Intent intent=new Intent(SettingMenuActivity.this,FitLevSettingActivity.class);
        startActivity(intent);
    }

    public void openReminder(View view) {
        Intent intent=new Intent(SettingMenuActivity.this,ReminderSettingActivity.class);
        startActivity(intent);
    }

    public void openEditProfile(View view) {
        Intent intent=new Intent(SettingMenuActivity.this,EditProfSettingActivity.class);
        startActivity(intent);
    }
}
