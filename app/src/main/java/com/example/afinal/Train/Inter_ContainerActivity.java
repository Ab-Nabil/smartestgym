package com.example.afinal.Train;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afinal.R;
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

public class Inter_ContainerActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnn;
    int pageNum;
    TextView mplan_tv;
    ImageView image_container;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter__container);
        mplan_tv = findViewById(R.id.plans_container_tv);
        image_container = findViewById(R.id.profile_pic_intermediate_container);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image_container);
            }
        });
        Bundle bundle=getIntent().getExtras();
        pageNum = bundle.getInt("pageNum");

        if (pageNum == 0){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.page_container_Empty,new Day1Fragment()).commit();
            mplan_tv.setText("Day 1 - Chest + biceps + abs");
        }else if(pageNum == 1){
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            fragmentManager2.beginTransaction().replace(R.id.page_container_Empty,new Day2Fragment()).commit();
            mplan_tv.setText("Day 2 - Back + triceps");
        }
        else if(pageNum == 2){
            FragmentManager fragmentManager3 = getSupportFragmentManager();
            fragmentManager3.beginTransaction().replace(R.id.page_container_Empty,new Day3Fragment()).commit();
            mplan_tv.setText("Day 3 - Shoulders + abs");
        }
        else if(pageNum == 3){
            FragmentManager fragmentManager4 = getSupportFragmentManager();
            fragmentManager4.beginTransaction().replace(R.id.page_container_Empty,new Day4Fragment()).commit();
            mplan_tv.setText("Day 4 - Legs");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
