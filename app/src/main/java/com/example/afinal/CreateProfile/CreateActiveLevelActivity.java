package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.Profiles.MainProfileActivity;
import com.example.afinal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;

public class CreateActiveLevelActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView aLBackRow;
    ImageView aLForwardRow;
    Button notActive;
    Button lightActive;
    Button Active;
    Button veryActive;
    boolean correctChoose = false;

    FirebaseFirestore firestore;
    FirebaseAuth fAuth;
    HashMap<String, String> user;
    String userID;

    String gender,weight,height,age,fit,active,username;
    String exercalories = "0";
    String exercalories1 ="0";
    String exercalories2 ="0";
    String exercalories3 ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_active_level);
        aLForwardRow = findViewById(R.id.aLForwardRow);
        aLBackRow = findViewById(R.id.aLBackRow);
        notActive = findViewById(R.id.notActive);
        lightActive = findViewById(R.id.lightActive);
        Active = findViewById(R.id.Active);
        veryActive = findViewById(R.id.veryActive);

        notActive.setOnClickListener(this);
        lightActive.setOnClickListener(this);
        Active.setOnClickListener(this);
        veryActive.setOnClickListener(this);
        aLBackRow.setOnClickListener(this);
        aLForwardRow.setOnClickListener(this);

        //firestore
        firestore= FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        Intent intent = getIntent();
        Bundle bundle=getIntent().getExtras();

        gender =bundle.getString("g");
        weight =bundle.getString("w");
        height =bundle.getString("h");
        age = bundle.getString("age");
        fit = bundle.getString("fit");
        username = bundle.getString("username");



        user = new HashMap<>();
        user.put("username",username);
        user.put("gender",gender);
        user.put("weight",weight);
        user.put("height",height);
        user.put("age",age);
        user.put("fit",fit);
        user.put("exercalories",exercalories);
        user.put("exercalories1",exercalories1);
        user.put("exercalories2",exercalories2);
        user.put("exercalories3",exercalories3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aLForwardRow: {
                if (correctChoose) {
                    Intent intent = new Intent(CreateActiveLevelActivity.this, MainProfileActivity.class);
                    DocumentReference documentReference = firestore.collection("users").document(userID);

                    user.put("active",active);
                    user.put("membership","");
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(CreateActiveLevelActivity.this, "Welcome on Board", Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(CreateActiveLevelActivity.this, "Empty Value", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

            }
            case R.id.notActive: {
                notActive.setBackgroundResource(R.drawable.tnvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;
                active = "1";
                break;
            }

            case R.id.lightActive:{
                lightActive.setBackgroundResource(R.drawable.tlactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                Active.setBackgroundResource(R.drawable.active);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;
                active = "2";

                break;
            }

            case R.id.Active:{
                Active.setBackgroundResource(R.drawable.tactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                notActive.setBackgroundResource(R.drawable.nvactive);
                veryActive.setBackgroundResource(R.drawable.vactive);
                correctChoose = true;
                active = "3";

                break;
            }

            case R.id.veryActive:{
                veryActive.setBackgroundResource(R.drawable.tvactive);
                lightActive.setBackgroundResource(R.drawable.lactive);
                Active.setBackgroundResource(R.drawable.active);
                notActive.setBackgroundResource(R.drawable.nvactive);
                correctChoose = true;
                active = "4";

                break;
            }

            case R.id.aLBackRow:{
                onBackPressed();

            }
        }
    }
}
