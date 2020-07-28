package com.example.afinal.Profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.text.DecimalFormat;

public class MealMenuActivity extends AppCompatActivity{

    ImageView calprotein, calcarbs, caldairy, calfats, calvegetables, calfruits,caloriesplanBackRow;
    String TDEE;
    double tdee;
    double foodtdee;
    TextView energy,proteincal,carbcal,fatcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);
        DecimalFormat df = new DecimalFormat("#.#");
        TDEE=getIntent().getStringExtra("tdee");
        tdee=Double.valueOf(TDEE);
        foodtdee=tdee*0.15;
        energy=findViewById(R.id.energymealvalue);
        energy.setText(String.valueOf(df.format(foodtdee)));
        proteincal=findViewById(R.id.proteincal);
        carbcal=findViewById(R.id.carbcal);
        fatcal=findViewById(R.id.fatscal);
        Double prot=foodtdee*0.30;
        Double carb=foodtdee*0.15;
        Double fat=foodtdee*0.04;
        df.format(prot);
        proteincal.setText(String.valueOf(df.format(prot)));
        carbcal.setText(String.valueOf(df.format(carb)));
        fatcal.setText(String.valueOf(df.format(fat)));

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
