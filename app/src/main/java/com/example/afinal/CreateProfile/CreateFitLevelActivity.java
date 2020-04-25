package com.example.afinal.CreateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.afinal.R;

public class CreateFitLevelActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView fLBackRow;
    ImageView flForwardRow;
    Button fitLevelBeginner;
    Button fitLevelIntermediate;
    Button fitLevelAdvanced;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_fit_level );

        flForwardRow=(ImageView)findViewById(R.id.flForwardRow);
        fLBackRow=(ImageView)findViewById(R.id.fLBackRow);
        fitLevelBeginner=(Button) findViewById(R.id.fitLevelBeginner);
        fitLevelIntermediate=(Button) findViewById(R.id.fitLevelIntermediate);
        fitLevelAdvanced=(Button)findViewById(R.id.fitLevelAdvanced);

        fitLevelBeginner.setOnClickListener(this);
        fitLevelIntermediate.setOnClickListener(this);
        fitLevelAdvanced.setOnClickListener(this);
        fLBackRow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fitLevelBeginner: {
                fitLevelBeginner.setBackgroundResource(R.drawable.tbfit);
                fitLevelIntermediate.setBackgroundResource(R.drawable.ifit);
                fitLevelAdvanced.setBackgroundResource(R.drawable.afit);

                flForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateFitLevelActivity.this, CreateActiveLevelActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.fitLevelIntermediate:{
                fitLevelIntermediate.setBackgroundResource(R.drawable.tifit);
                fitLevelBeginner.setBackgroundResource(R.drawable.bfit);
                fitLevelAdvanced.setBackgroundResource(R.drawable.afit);
                flForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateFitLevelActivity.this, CreateActiveLevelActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.fitLevelAdvanced:{
                fitLevelAdvanced.setBackgroundResource(R.drawable.tafit);
                fitLevelBeginner.setBackgroundResource(R.drawable.bfit);
                fitLevelIntermediate.setBackgroundResource(R.drawable.ifit);
                flForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateFitLevelActivity.this, CreateActiveLevelActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.fLBackRow:{
                Intent intent=new Intent(CreateFitLevelActivity.this,CreateBdateActivity.class);
                startActivity(intent);
            }
        }
    }
}
