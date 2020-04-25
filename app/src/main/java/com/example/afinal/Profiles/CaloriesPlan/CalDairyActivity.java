package com.example.afinal.Profiles.CaloriesPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Profiles.MealMenuActivity;
import com.example.afinal.R;

public class CalDairyActivity extends AppCompatActivity {
    ImageView dairyBackRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_dairy);
        dairyBackRow=(ImageView)findViewById(R.id.dairyBackRow);
        dairyBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalDairyActivity.this, MealMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
