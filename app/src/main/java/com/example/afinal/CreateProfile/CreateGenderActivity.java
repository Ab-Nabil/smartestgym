package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.example.afinal.User;
import com.google.gson.Gson;

public class CreateGenderActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView gendermaleimage;
    ImageView genderfemaleimage;
    ImageView genderForwardrow;
    boolean correctChoose = false;

    User user;
    String genderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_gender );

        Gson gson = new Gson();
        String userDO = getIntent().getStringExtra("userRO");
        user = gson.fromJson(userDO, User.class);

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
                    user.setUserGender(genderValue);
                    Intent intent = new Intent(CreateGenderActivity.this, CreateWeightActivity.class);

                    Gson gson1 = new Gson();
                    String userDO = gson1.toJson(user);
                    intent.putExtra("userGO", userDO);

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
                genderValue = "Male";
                break;
            }

            case R.id.genderFemaleImage:{
                genderfemaleimage.setImageResource(R.drawable.genderfemaleaccept);
                gendermaleimage.setImageResource(R.drawable.gendermale);
                correctChoose = true;
                genderValue = "Female";
                break;
            }

        }
    }
}
