package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;

public class MealMenuActivity extends AppCompatActivity{

    ImageView calprotein, calcarbs, caldairy, calfats, calvegetables, calfruits,caloriesplanBackRow;
    String TDEE;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    Double foodtdee=1.0;
    TextView energy,proteincal,carbcal,fatcal,usernamev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);

        DecimalFormat df = new DecimalFormat("#.#");
        Intent intent = getIntent();
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        usernamev = findViewById(R.id.usernamevmenu);
        usernamev.setText(intent.getStringExtra("username"));

        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

            TDEE = documentSnapshot.getString("TDEE");

                DecimalFormat df = new DecimalFormat("#.#");
                foodtdee= Float.parseFloat(TDEE)*0.15;
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
            }
        });

//        usernamev = findViewById(R.id.usernamevmenu);
//        usernamev.setText(intent.getStringExtra("username"));
//        foodtdee= Float.parseFloat(TDEE)*0.15;
//        energy=findViewById(R.id.energymealvalue);
//        energy.setText(String.valueOf(df.format(foodtdee)));
//        proteincal=findViewById(R.id.proteincal);
//        carbcal=findViewById(R.id.carbcal);
//        fatcal=findViewById(R.id.fatscal);
//        Double prot=foodtdee*0.30;
//        Double carb=foodtdee*0.15;
//        Double fat=foodtdee*0.04;
//        df.format(prot);
//        proteincal.setText(String.valueOf(df.format(prot)));
//        carbcal.setText(String.valueOf(df.format(carb)));
//        fatcal.setText(String.valueOf(df.format(fat)));

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
