package com.example.afinal.Train;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.PlanAHomeActivity;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;
import com.example.afinal.Train.PlanB.PlanBHomeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class IntermediateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImageGo,mImageGo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        
        mImageGo = findViewById(R.id.inter_plan_a_go);
        mImageGo.setOnClickListener(this);
        mImageGo2 = findViewById(R.id.inter_plan_b_go);
        mImageGo2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inter_plan_a_go:
                Intent intent = new Intent(IntermediateActivity.this, PlanAHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.inter_plan_b_go:
                Intent intent1 = new Intent(IntermediateActivity.this, PlanBHomeActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
