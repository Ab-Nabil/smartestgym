package com.example.afinal.CreateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.afinal.Profiles.MainProfileActivity;
import com.example.afinal.R;

public class CreateActiveLevelActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView aLBackRow;
    ImageView aLForwardRow;
    Button notActive;
    Button lightActive;
    Button Active;
    Button veryActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_active_level);
        aLForwardRow=(ImageView)findViewById(R.id.aLForwardRow);
        aLBackRow=(ImageView)findViewById(R.id.aLBackRow);
        notActive=(Button) findViewById(R.id.notActive);
        lightActive=(Button) findViewById(R.id.lightActive);
        Active=(Button)findViewById(R.id.Active);
        veryActive=(Button)findViewById(R.id.veryActive);

        notActive.setOnClickListener(this);
        lightActive.setOnClickListener(this);
        Active.setOnClickListener(this);
        veryActive.setOnClickListener(this);
        aLBackRow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notActive: {
                notActive.setBackgroundResource(R.drawable.tnvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);
                aLForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.lightActive:{
                lightActive.setBackgroundResource(R.drawable.tlactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);

                aLForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.Active:{
                Active.setBackgroundResource(R.drawable.tactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                veryActive.setBackgroundResource(R.drawable.vactive);
                aLForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.veryActive:{
                veryActive.setBackgroundResource(R.drawable.tvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                notActive.setBackgroundResource(R.drawable.nvactive);
                aLForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.aLBackRow:{
                Intent intent=new Intent(CreateActiveLevelActivity.this, CreateFitLevelActivity.class);
                startActivity(intent);
            }
        }
    }
}
