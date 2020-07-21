package com.example.afinal.Train.PlanB;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;
import com.example.afinal.Train.PlanB.Fragments.Day1Fragment;
import com.example.afinal.Train.PlanB.Fragments.Day2Fragment;
import com.example.afinal.Train.PlanB.Fragments.Day3Fragment;
import com.example.afinal.Train.PlanB.Fragments.Day4Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class PlanBHomeActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout layout;
    ImageView mImageGo1,mImageGo2,mImageGo3,mImageGo4;
    TextView mplan_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_b_home);
        layout =findViewById(R.id.page_container_b);

        mImageGo1 = findViewById(R.id.inter_b_day1_go);
        mImageGo1.setOnClickListener(this);

        mImageGo2 = findViewById(R.id.inter_b_day2_go);
        mImageGo2.setOnClickListener(this);

        mImageGo3 = findViewById(R.id.inter_b_day3_go);
        mImageGo3.setOnClickListener(this);

        mImageGo4 = findViewById(R.id.inter_b_day4_go);
        mImageGo4.setOnClickListener(this);

        mplan_tv = findViewById(R.id.plans_b_tv_change);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inter_b_day1_go:
                layout.removeAllViews();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.page_container_b,new Day1Fragment()).commit();
                mplan_tv.setText("Day 1 - Chest + biceps + abs");
                break;

            case R.id.inter_b_day2_go:
                layout.removeAllViews();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction().replace(R.id.page_container_b,new Day2Fragment()).commit();
                mplan_tv.setText("Day 2 - Back + triceps");
                break;
            case R.id.inter_b_day3_go:
                layout.removeAllViews();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.page_container_b,new Day3Fragment()).commit();
                mplan_tv.setText("Day 3 - Shoulders + abs");
                break;
            case R.id.inter_b_day4_go:
                layout.removeAllViews();
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                fragmentManager4.beginTransaction().replace(R.id.page_container_b,new Day4Fragment()).commit();
                mplan_tv.setText("Day 4 - Legs");
                break;
        }
    }
}
