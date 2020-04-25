package com.example.afinal.CreateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.afinal.R;

public class CreateBdateActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bDateBackRow;
    ImageView bDateForwardRow;
    EditText bDateValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_bdate );

        bDateValue=(EditText)findViewById(R.id.bDateEditText);
        bDateBackRow=(ImageView)findViewById(R.id.bDateBackRow);
        bDateForwardRow=(ImageView)findViewById(R.id.bDateForwardRow);

        bDateBackRow.setOnClickListener(this);
        bDateValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bDateEditText:{
                bDateForwardRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(CreateBdateActivity.this, CreateFitLevelActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            }

            case R.id.bDateBackRow:{
                Intent intent=new Intent(CreateBdateActivity.this,CreateHeightActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
