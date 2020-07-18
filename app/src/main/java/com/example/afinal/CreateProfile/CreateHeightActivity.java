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

public class CreateHeightActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView heightbackRow;
    ImageView heightforwardRow;
    EditText heightValue;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_height );

        heightValue = findViewById(R.id.heightEditText);
        heightbackRow = findViewById(R.id.heightBackRow);
        heightforwardRow = findViewById(R.id.heightForwardRow);

        heightbackRow.setOnClickListener(this);
        heightforwardRow.setOnClickListener(this);
        user = new User();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.heightForwardRow: {
                //get values from EditText fields
                String heightvalue = heightValue.getText().toString();
                if (heightvalue.isEmpty()) {
                    heightValue.setHint("Empty Value");
                } else {
                    user.setUserHeight(heightvalue);
                    Intent intent = new Intent(CreateHeightActivity.this, CreateBdateActivity.class);
                    Intent intent1 = new Intent(CreateHeightActivity.this, CreateActiveLevelActivity.class);
                    intent1.putExtra("userHO", (Serializable) user);
                    startActivity(intent);
                }
                break;
            }

            case R.id.heightBackRow:{
                onBackPressed();
                //Intent intent=new Intent(CreateHeightActivity.this,CreateWeightActivity.class);
                //startActivity(intent);
            }
            break;
        }
    }
}
