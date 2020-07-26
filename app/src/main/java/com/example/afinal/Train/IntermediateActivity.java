package com.example.afinal.Train;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.PlanAHomeActivity;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;
import com.example.afinal.Train.PlanB.PlanBHomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class IntermediateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImageGo,mImageGo2,profilePic,backarrow;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        profilePic = findViewById(R.id.profile_pic_intermediate);
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });
        mImageGo = findViewById(R.id.inter_plan_a_go);
        mImageGo.setOnClickListener(this);
        mImageGo2 = findViewById(R.id.inter_plan_b_go);
        mImageGo2.setOnClickListener(this);

        backarrow = findViewById(R.id.inter_back_arrow);
        backarrow.setOnClickListener(this);
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
            case R.id.inter_back_arrow:
                onBackPressed();
                break;
        }
    }
}
