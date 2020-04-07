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
    ImageView genderlogorow;
    TextView gendermaletext,genderfemaletext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_gender );

        genderlogorow=(ImageView)findViewById(R.id.genderLogoRow);
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
                Snackbar.make(gendermaletext, "User is male! ", Snackbar.LENGTH_LONG).show();
                gendermaletext.setTextColor (R.color.colorPrimary);
                genderfemaletext.setTextColor ( R.color.colorBlack );
                genderlogorow.setOnClickListener(new View.OnClickListener() {
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
                genderfemaletext.setTextColor (R.color.colorPrimary);
                gendermaletext.setTextColor ( R.color.colorWhite );
                Snackbar.make(genderfemaletext, "User is female! ", Snackbar.LENGTH_LONG).show();
                genderlogorow.setOnClickListener(new View.OnClickListener() {
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
