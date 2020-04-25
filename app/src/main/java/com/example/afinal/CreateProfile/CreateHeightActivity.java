package com.example.afinal.CreateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.afinal.R;

public class CreateHeightActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView heightbackRow;
    ImageView heightforwardRow;
    EditText heightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_height );

        heightValue=(EditText)findViewById(R.id.heightEditText);
        heightbackRow=(ImageView)findViewById(R.id.heightBackRow);
        heightforwardRow=(ImageView)findViewById(R.id.heightForwardRow);

        heightbackRow.setOnClickListener(this);
        heightValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.heightEditText:{
                heightforwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateHeightActivity.this, CreateBdateActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.heightBackRow:{
                Intent intent=new Intent(CreateHeightActivity.this,CreateWeightActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
