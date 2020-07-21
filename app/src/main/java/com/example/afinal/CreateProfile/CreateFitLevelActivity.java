package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.google.gson.Gson;

public class CreateFitLevelActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView fLBackRow;
    ImageView flForwardRow;
    Button fitLevelBeginner;
    Button fitLevelIntermediate;
    Button fitLevelAdvanced;
    boolean correctChoose = false;
    String fit;
    String gender,weight,height,age,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_fit_level );

        flForwardRow = findViewById(R.id.flForwardRow);
        fLBackRow = findViewById(R.id.fLBackRow);
        fitLevelBeginner = findViewById(R.id.fitLevelBeginner);
        fitLevelIntermediate = findViewById(R.id.fitLevelIntermediate);
        fitLevelAdvanced = findViewById(R.id.fitLevelAdvanced);

        fitLevelBeginner.setOnClickListener(this);
        fitLevelIntermediate.setOnClickListener(this);
        fitLevelAdvanced.setOnClickListener(this);
        fLBackRow.setOnClickListener(this);
        flForwardRow.setOnClickListener(this);

        Bundle bundle=getIntent().getExtras();

        gender =bundle.getString("g");
        weight =bundle.getString("w");
        height = bundle.getString("h");
        age = bundle.getString("age");
        username = bundle.getString("username");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flForwardRow: {
                if (correctChoose) {
                    Intent intent = new Intent(CreateFitLevelActivity.this, CreateActiveLevelActivity.class);
                    intent.putExtra("h",height);
                    intent.putExtra("g",gender);
                    intent.putExtra("w",weight);
                    intent.putExtra("age",age);
                    intent.putExtra("fit",fit);
                    intent.putExtra("username",username);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(CreateFitLevelActivity.this, "Empty Value", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            }
            case R.id.fitLevelBeginner: {
                fitLevelBeginner.setBackgroundResource(R.drawable.tbfit);
                fitLevelIntermediate.setBackgroundResource(R.drawable.ifit);
                fitLevelAdvanced.setBackgroundResource(R.drawable.afit);
                fit = "1";
                correctChoose = true;
                break;
            }

            case R.id.fitLevelIntermediate:{
                fitLevelIntermediate.setBackgroundResource(R.drawable.tifit);
                fitLevelBeginner.setBackgroundResource(R.drawable.bfit);
                fitLevelAdvanced.setBackgroundResource(R.drawable.afit);
                correctChoose = true;
                fit = "2";
                break;
            }

            case R.id.fitLevelAdvanced:{
                fitLevelAdvanced.setBackgroundResource(R.drawable.tafit);
                fitLevelBeginner.setBackgroundResource(R.drawable.bfit);
                fitLevelIntermediate.setBackgroundResource(R.drawable.ifit);
                correctChoose = true;
                fit = "3";
                break;
            }

            case R.id.fLBackRow:{
                onBackPressed();
                //  Intent intent=new Intent(CreateFitLevelActivity.this,CreateBdateActivity.class);
                // startActivity(intent);
            }
        }
    }
}
