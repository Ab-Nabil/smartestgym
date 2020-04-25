package com.example.afinal.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.CreateProfile.CreateBdateActivity;
import com.example.afinal.CreateProfile.CreateFitLevelActivity;
import com.example.afinal.CreateProfile.CreateHeightActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalCarbsActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalDairyActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalFatsActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalFruitsActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalProteinActivity;
import com.example.afinal.Profiles.CaloriesPlan.CalVegetablesActivity;
import com.example.afinal.R;

public class MealMenuActivity extends AppCompatActivity{

    ImageView calprotein, calcarbs, caldairy, calfats, calvegetables, calfruits,caloriesplanBackRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);

        caloriesplanBackRow=(ImageView)findViewById(R.id.caloriesplanBackRow);
        calprotein = (ImageView) findViewById(R.id.calprotein);
        calcarbs = (ImageView) findViewById(R.id.calcarbs);
        caldairy = (ImageView) findViewById(R.id.caldairy);
        calfats = (ImageView) findViewById(R.id.calfats);
        calvegetables = (ImageView) findViewById(R.id.calvegetables);
        calfruits = (ImageView) findViewById(R.id.calfruits);

        caloriesplanBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, MainProfileActivity.class);
                startActivity(intent);
            }
        });

        calprotein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalProteinActivity.class);
                startActivity(intent);
            }
        });
        calcarbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalCarbsActivity.class);
                startActivity(intent);
            }
        });
        calfats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalFatsActivity.class);
                startActivity(intent);
            }
        });
        caldairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalDairyActivity.class);
                startActivity(intent);
            }
        });
        calfruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalFruitsActivity.class);
                startActivity(intent);
            }
        });
        calvegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealMenuActivity.this, CalVegetablesActivity.class);
                startActivity(intent);
            }
        });
    }
}
