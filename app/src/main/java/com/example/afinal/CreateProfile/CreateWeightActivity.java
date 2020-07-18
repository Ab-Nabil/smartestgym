package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.example.afinal.User;

import java.io.Serializable;

public class CreateWeightActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView weightbackRow;
    ImageView weightforwardRow;
    EditText weightValue;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_weight );
        user = new User();

        weightValue = findViewById(R.id.weightEditText);
        weightbackRow = findViewById(R.id.weightBackRow);
        weightforwardRow = findViewById(R.id.weightForwardRow);

        weightbackRow.setOnClickListener(this);
        weightforwardRow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weightForwardRow: {
                //get values from EditText fields
                String weightvalue = weightValue.getText().toString();
                if (weightvalue.isEmpty()) {
                    weightValue.setHint("Empty Value");
                } else {
                    user.setUserWeight(weightvalue);
                    Intent intent = new Intent(CreateWeightActivity.this, CreateHeightActivity.class);
                    Intent intent1 = new Intent(CreateWeightActivity.this, CreateActiveLevelActivity.class);
                    intent1.putExtra("userWO", (Serializable) user);
                    startActivity(intent);
                }
                break;
            }

            case R.id.weightBackRow:{
                onBackPressed();
                //Intent intent=new Intent(CreateWeightActivity.this,CreateGenderActivity.class);
                //startActivity(intent);
            }
            break;
        }
    }
}
