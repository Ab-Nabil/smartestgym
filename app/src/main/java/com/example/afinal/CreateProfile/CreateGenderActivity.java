package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;

public class CreateGenderActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView gendermaleimage;
    ImageView genderfemaleimage;
    ImageView genderForwardrow;
    boolean correctChoose = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_gender );

        genderForwardrow = findViewById(R.id.genderForwardRow);
        gendermaleimage = findViewById(R.id.genderMaleImage);
        genderfemaleimage = findViewById(R.id.genderFemaleImage);

        gendermaleimage.setOnClickListener(this);
        genderfemaleimage.setOnClickListener(this);
        genderForwardrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.genderForwardRow: {
                if (correctChoose) {
                    Intent intent = new Intent(CreateGenderActivity.this, CreateWeightActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(CreateGenderActivity.this, "Empty Value", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            }
            case R.id.genderMaleImage: {
                gendermaleimage.setImageResource(R.drawable.gendermaleaccept);
                genderfemaleimage.setImageResource(R.drawable.genderfemale);
                correctChoose = true;
                break;
            }

            case R.id.genderFemaleImage:{
                genderfemaleimage.setImageResource(R.drawable.genderfemaleaccept);
                gendermaleimage.setImageResource(R.drawable.gendermale);
                correctChoose = true;
                break;
            }

        }
    }
}
