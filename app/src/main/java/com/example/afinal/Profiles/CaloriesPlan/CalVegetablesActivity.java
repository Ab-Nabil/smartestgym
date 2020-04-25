package com.example.afinal.Profiles.CaloriesPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Profiles.MealMenuActivity;
import com.example.afinal.R;

public class CalVegetablesActivity extends AppCompatActivity {
    ImageView vegetableBackRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_vegetables);

        vegetableBackRow=(ImageView)findViewById(R.id.vegetableBackRow);
        vegetableBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalVegetablesActivity.this, MealMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
