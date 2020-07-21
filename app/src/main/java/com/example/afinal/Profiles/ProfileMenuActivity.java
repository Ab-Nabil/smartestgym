package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileMenuActivity extends AppCompatActivity {
    ImageView profilebackrow;
    TextView agetv,weighttv,heighttv,emailtv,usernamev;

    FirebaseUser user;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        agetv = findViewById(R.id.agevalue);
        emailtv = findViewById(R.id.EmailProfile);
        usernamev = findViewById(R.id.usernamev);
        heighttv = findViewById(R.id.heightProfile);
        weighttv = findViewById(R.id.startingWeight);

        profilebackrow=(ImageView)findViewById(R.id.profileBackRow);
        profilebackrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this,MainProfileActivity.class);
                startActivity(intent);
            }
        });

        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                agetv.setText(documentSnapshot.getString("age")+" Years");
                heighttv.setText(documentSnapshot.getString("height")+" CM");
                weighttv.setText(documentSnapshot.getString("weight")+" KG");
                usernamev.setText(documentSnapshot.getString("username"));

            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            emailtv.setText(userEmail);
        } else {
            // No user is signed in
        }
    }

    public void activeLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(ProfileMenuActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
