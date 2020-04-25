package com.example.afinal.Profiles.CaloriesPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Profiles.MealMenuActivity;
import com.example.afinal.R;

public class CalCarbsActivity extends AppCompatActivity {
    ImageView carbsBackRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_carbs);

        carbsBackRow=(ImageView)findViewById(R.id.carbsBackRow);
        carbsBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalCarbsActivity.this, MealMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
