package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.WelcomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProfileMenuActivity extends AppCompatActivity {
    float TDEE;
    TextView energy;

    ImageView profilebackrow,profileimg;
    TextView agetv,weighttv,heighttv,emailtv,usernamev,goalweighttv;
    LinearLayout logout,changepass;
    FirebaseUser user;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userID = fAuth.getCurrentUser().getUid();




        agetv = findViewById(R.id.agevalue);
        emailtv = findViewById(R.id.EmailProfile);
        usernamev = findViewById(R.id.usernamev);
        heighttv = findViewById(R.id.heightProfile);
        weighttv = findViewById(R.id.startingWeight);
        goalweighttv = findViewById(R.id.goalWeightmenu);

        profilebackrow=(ImageView)findViewById(R.id.profileBackRow);
        profilebackrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileMenuActivity.this,MainProfileActivity.class);
                startActivity(intent);
            }
        });
        //logout
        logout = findViewById(R.id.logoutinfo);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent=new Intent(ProfileMenuActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
        changepass = findViewById(R.id.changepasswordlinear);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ProfileMenuActivity.this,ChangePasswordActivity.class);
               startActivity(intent);
            }
        });

        profileimg= findViewById(R.id.defuserimgprofile);
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimg);
            }
        });
        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                TDEE = Float.parseFloat(documentSnapshot.getString("TDEE"));
                DecimalFormat df = new DecimalFormat("#.#");
                energy=findViewById(R.id.energyprofilevalue);
                energy.setText(df.format(TDEE));

                agetv.setText(documentSnapshot.getString("age")+" Years");
                heighttv.setText(documentSnapshot.getString("height")+" CM");
                weighttv.setText(documentSnapshot.getString("weight")+" KG");
                usernamev.setText(documentSnapshot.getString("username"));
                String g = documentSnapshot.getString("gender");
                String height = documentSnapshot.getString("height");
                float h = Float.parseFloat(height);
                /*The Broca equation
                Women: IBW [kg] = (height[cm] - 100) - ((height[cm] - 100) × 15%)
                Men: IBW [kg] = (height[cm] - 100) - ((height[cm] - 100) × 10%)
                */
                if (g.startsWith("m")) {
                    double IBW = (h - 100) - ((h - 100) * 10 / 100);
                    //Math.ceil(IBW);
                    goalweighttv.setText(""+(int)(IBW)+" KG");
                } else if (g.startsWith("f")){
                    double IBW = ((int)h - 100) - ((h - 100) * 15 / 100);
                    goalweighttv.setText(""+(int)(IBW)+" KG");
                }
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
}
