package com.example.afinal.CreateProfile;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afinal.R;
import com.google.android.material.snackbar.Snackbar;

public class CreateGenderActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView gendermaleimage;
    ImageView genderfemaleimage;
    ImageView genderForwardrow;
    TextView gendermaletext,genderfemaletext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_gender );

        genderForwardrow=(ImageView)findViewById(R.id.genderForwardRow);
        gendermaletext=(TextView)findViewById(R.id.genderMaleText);
        genderfemaletext=(TextView)findViewById(R.id.genderFemaleText);
        gendermaleimage=(ImageView) findViewById(R.id.genderMaleImage);
        genderfemaleimage=(ImageView) findViewById(R.id.genderFemaleImage);

        gendermaleimage.setOnClickListener(this);
        genderfemaleimage.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.genderMaleImage: {
                gendermaleimage.setImageResource(R.drawable.gendermaleaccept);
                genderfemaleimage.setImageResource(R.drawable.genderfemale);
                genderForwardrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateGenderActivity.this, CreateWeightActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.genderFemaleImage:{
                genderfemaleimage.setImageResource(R.drawable.genderfemaleaccept);
                gendermaleimage.setImageResource(R.drawable.gendermale);
                genderForwardrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateGenderActivity.this, CreateWeightActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

        }
    }
}
