package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.afinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditProfSettingActivity extends AppCompatActivity {
    TextView usernamev,agetv,heighttv,weighttv;

    FirebaseFirestore firestore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prof_setting);

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
        } else {
            // No user is signed in
        }

    }
}
