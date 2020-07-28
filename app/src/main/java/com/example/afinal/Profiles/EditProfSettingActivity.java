package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditProfSettingActivity extends AppCompatActivity implements View.OnClickListener {
    TextView usernamev,agetv,heighttv,weighttv;
    ImageView usernameE,ageE,weightE,heightE,mBack;
    FirebaseFirestore firestore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prof_setting);
        usernameE = findViewById(R.id.usernameeditsetting);
        ageE = findViewById(R.id.ageeditsetting);
        weightE = findViewById(R.id.sweditsetting);
        heightE = findViewById(R.id.heighteditsetting);
        usernameE.setOnClickListener(this);
        ageE.setOnClickListener(this);
        weightE.setOnClickListener(this);
        heightE.setOnClickListener(this);
        mBack = findViewById(R.id.settingEPBackRow);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        usernamev = findViewById(R.id.usernamev);
//        userID = fAuth.getCurrentUser().getUid();
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        agetv = findViewById(R.id.agevalueSettings);
        usernamev = findViewById(R.id.usernamevSettings);
        heighttv = findViewById(R.id.heightSettings);
        weighttv = findViewById(R.id.weightSettings);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference documentReference = firestore.collection("users").document(fAuth.getCurrentUser().getUid());
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    agetv.setText(documentSnapshot.getString("age")+" Years");
                    heighttv.setText(documentSnapshot.getString("height")+" CM");
                    weighttv.setText(documentSnapshot.getString("weight")+" KG");
                    usernamev.setText(documentSnapshot.getString("username"));

                }
            });
        }
        else {
            // No user is signed in
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usernameeditsetting:
                Toast.makeText(this, "You Can't update your Username at the moment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ageeditsetting:
                Toast.makeText(this, "You Can't update your Age at the moment", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sweditsetting:
                Toast.makeText(this, "You Can't update your Weight at the moment", Toast.LENGTH_SHORT).show();

                break;
            case R.id.heighteditsetting:
                Toast.makeText(this, "You Can't update your Height at the moment", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
