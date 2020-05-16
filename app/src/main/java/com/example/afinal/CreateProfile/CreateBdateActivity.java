package com.example.afinal.CreateProfile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateBdateActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bDateBackRow;
    ImageView bDateForwardRow;
    EditText bDateValue;
    final Calendar myCalendar = Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_bdate );

        bDateValue = findViewById(R.id.bDateEditText);
        bDateBackRow = findViewById(R.id.bDateBackRow);
        bDateForwardRow = findViewById(R.id.bDateForwardRow);
        bDateBackRow.setOnClickListener(this);
        bDateForwardRow.setOnClickListener(this);


        bDateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateBdateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bDateForwardRow: {
                //get values from EditText fields
                String bdatevalue = bDateValue.getText().toString();
                if (bdatevalue.isEmpty()) {
                    bDateValue.setHint("Empty Value");
                } else {
                    Intent intent = new Intent(CreateBdateActivity.this, CreateFitLevelActivity.class);
                    startActivity(intent);
                }
                break;
            }

            case R.id.bDateBackRow:{
                Intent intent=new Intent(CreateBdateActivity.this,CreateHeightActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        bDateValue.setText(sdf.format(myCalendar.getTime()));
    }
}
