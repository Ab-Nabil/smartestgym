package com.example.afinal.Profiles.CaloriesPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Profiles.MealMenuActivity;
import com.example.afinal.R;

public class CalProteinActivity extends AppCompatActivity {
    ImageView proteinBackRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_protein);
        proteinBackRow=(ImageView)findViewById(R.id.proteinBackRow);
        proteinBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalProteinActivity.this, MealMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
