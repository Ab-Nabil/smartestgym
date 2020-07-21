package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class CreateHeightActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView heightbackRow;
    ImageView heightforwardRow;
    EditText heightValue;
    String mHeight;

    FirebaseAuth fAuth;
    FirebaseFirestore firestore;

    String gender,weight,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_height );

        heightValue = findViewById(R.id.heightEditText);
        heightbackRow = findViewById(R.id.heightBackRow);
        heightforwardRow = findViewById(R.id.heightForwardRow);

        heightbackRow.setOnClickListener(this);
        heightforwardRow.setOnClickListener(this);


        Bundle bundle=getIntent().getExtras();

        gender = bundle.getString("g");
        weight = bundle.getString("w");
        username = bundle.getString("username");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.heightForwardRow: {
                mHeight = heightValue.getText().toString();

                if (mHeight.isEmpty()) {
                    heightValue.setError("Enter Your Height");
                }
                else {
                    Intent intent;
                    intent = new Intent(CreateHeightActivity.this, CreateBdateActivity.class);
                    intent.putExtra("h",mHeight);
                    intent.putExtra("g",gender);
                    intent.putExtra("w",weight);
                    intent.putExtra("username",username);


                    startActivity(intent);
                }
                break;
            }

            case R.id.heightBackRow:{
                onBackPressed();
            }
            break;
        }
    }
}
