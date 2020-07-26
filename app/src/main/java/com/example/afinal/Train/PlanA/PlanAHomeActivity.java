package com.example.afinal.Train.PlanA;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.Train.Inter_ContainerActivity;
import com.example.afinal.Train.PlanA.Fragments.Day1Fragment;
import com.example.afinal.Train.PlanA.Fragments.Day2Fragment;
import com.example.afinal.Train.PlanA.Fragments.Day3Fragment;
import com.example.afinal.Train.PlanA.Fragments.Day4Fragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class PlanAHomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout;
    ImageView mImageGo1,mImageGo2,mImageGo3,mImageGo4,profilePic;
    TextView mplan_tv;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_a_home);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        profilePic = findViewById(R.id.profile_pic_intermediate_plana);
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });

        layout =findViewById(R.id.page_container_a);

        mImageGo1 = findViewById(R.id.inter_day1_go);
        mImageGo1.setOnClickListener(this);

        mImageGo2 = findViewById(R.id.inter_day2_go);
        mImageGo2.setOnClickListener(this);

        mImageGo3 = findViewById(R.id.inter_day3_go);
        mImageGo3.setOnClickListener(this);

        mImageGo4 = findViewById(R.id.inter_day4_go);
        mImageGo4.setOnClickListener(this);

        mplan_tv = findViewById(R.id.plans_tv_change);
        ImageView backarrow = findViewById(R.id.inter_back_arrow_plana);
        backarrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inter_day1_go:
                Intent intent = new Intent(this, Inter_ContainerActivity.class);
                intent.putExtra("pageNum",0);
                startActivity(intent);
                break;

            case R.id.inter_day2_go:
                Intent intent1 = new Intent(this, Inter_ContainerActivity.class);
                intent1.putExtra("pageNum",1);
                startActivity(intent1);
                break;
            case R.id.inter_day3_go:
                Intent intent2 = new Intent(this, Inter_ContainerActivity.class);
                intent2.putExtra("pageNum",2);
                startActivity(intent2);
                break;
            case R.id.inter_day4_go:
                Intent intent3 = new Intent(this, Inter_ContainerActivity.class);
                intent3.putExtra("pageNum",3);
                startActivity(intent3);
                break;
            case R.id.inter_back_arrow_plana:
                onBackPressed();
                break;
        }
    }
}
