package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FitLevSettingActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout beginner,intermediate,advanced;
    TextView usernamev;
    FirebaseFirestore firestore;
    FirebaseAuth fAuth;
    ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_lev_setting);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        mBack = findViewById(R.id.flsettingBackRow);
        mBack.setOnClickListener(this);

        beginner = findViewById(R.id.fitLevelbeginner);
        intermediate = findViewById(R.id.fitLevelintermediate);
        advanced = findViewById(R.id.fitLeveladvanced);
        beginner.setOnClickListener(this);
        intermediate.setOnClickListener(this);
        advanced.setOnClickListener(this);
        usernamev = findViewById(R.id.usernamevfit);
        DocumentReference documentReference = firestore.collection("users").document(fAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                usernamev.setText(documentSnapshot.getString("username"));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fitLevelbeginner:
                Toast.makeText(this, "Your fit level is now Beginner", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fitLevelintermediate:
                Toast.makeText(this, "Your fit level is now Intermediate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fitLeveladvanced:
                Toast.makeText(this, "Your fit level is now Advanced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.flsettingBackRow:
                onBackPressed();
                break;
        }
    }
}
