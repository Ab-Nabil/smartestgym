package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.Profiles.MainProfileActivity;
import com.example.afinal.R;
import com.example.afinal.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class CreateActiveLevelActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView aLBackRow;
    ImageView aLForwardRow;
    Button notActive;
    Button lightActive;
    Button Active;
    Button veryActive;
    boolean correctChoose = false;
    User user;
    String activityLevelValue;
    FirebaseDatabase sgdatabase;
    DatabaseReference sgreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_active_level);

        sgdatabase = FirebaseDatabase.getInstance();
        sgreference = sgdatabase.getReference();
        Gson gson = new Gson();
        String userDO = getIntent().getStringExtra("userFLO");
        user = gson.fromJson(userDO, User.class);

        aLForwardRow = findViewById(R.id.aLForwardRow);
        aLBackRow = findViewById(R.id.aLBackRow);
        notActive = findViewById(R.id.notActive);
        lightActive = findViewById(R.id.lightActive);
        Active = findViewById(R.id.Active);
        veryActive = findViewById(R.id.veryActive);

        notActive.setOnClickListener(this);
        lightActive.setOnClickListener(this);
        Active.setOnClickListener(this);
        veryActive.setOnClickListener(this);
        aLBackRow.setOnClickListener(this);
        aLForwardRow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aLForwardRow: {
                if (correctChoose) {
                    user.setUserActivityLevel(activityLevelValue);
                    Intent intent = new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                    sgreference.child("User").setValue(user);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(CreateActiveLevelActivity.this, "Empty Value", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            }
            case R.id.notActive: {
                activityLevelValue = "Not_Active";
                notActive.setBackgroundResource(R.drawable.tnvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;
                break;
            }

            case R.id.lightActive:{
                activityLevelValue = "Light_Active";
                lightActive.setBackgroundResource(R.drawable.tlactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;

                break;
            }

            case R.id.Active:{
                activityLevelValue = "Active";
                Active.setBackgroundResource(R.drawable.tactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;

                break;
            }

            case R.id.veryActive:{
                activityLevelValue = "Very_Active";
                veryActive.setBackgroundResource(R.drawable.tvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                notActive.setBackgroundResource(R.drawable.nvactive);
                correctChoose = true;

                break;
            }

            case R.id.aLBackRow:{
                onBackPressed();
                //Intent intent=new Intent(CreateActiveLevelActivity.this, CreateFitLevelActivity.class);
                //startActivity(intent);
            }
        }
    }
}
