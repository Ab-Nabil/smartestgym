package com.example.afinal.Profiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.LoginActivity;
import com.example.afinal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class MainProfileActivity extends AppCompatActivity implements View.OnClickListener{

    
    Button menuicon;
    NavigationView navigationn;
    ImageView gym,home,logobackrow,defuserimg,checkcorrect;
    TextView usernamev,cupcounter,mStartWeight,menuusernamev;
    int counter=0;
    ImageView glss1,glss2,glss3,glss4,glss5,glss6,glss7,glss8;
    boolean a0=true;boolean a1=true;boolean a2=true;boolean a3=true;boolean a4=true;boolean a5=true;boolean a6=true;boolean a7=true;
    private static final int GALLERY_CODE=1;
    private Uri mImageUri;
    boolean doubleBackToExitPressedOnce = false;
    boolean navi_open = false;

    FirebaseUser user;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        usernamev.setText(user.getDisplayName());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
        } else {
            // No user is signed in
        }

//        loadUserInfo();

        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mStartWeight.setText(documentSnapshot.getString("weight"));
                menuusernamev.setText(documentSnapshot.getString("username"));
                usernamev.setText(documentSnapshot.getString("username"));
            }
        });

        menuicon = findViewById(R.id.menuicon);
        logobackrow = findViewById(R.id.menuBackRow);
        cupcounter = findViewById(R.id.cupcounterr);
        usernamev = findViewById(R.id.usernamev);

        mStartWeight = findViewById(R.id.startweightvalue);

        checkcorrect = findViewById(R.id.checkcorrect);
        defuserimg = findViewById(R.id.defuserimg);
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(defuserimg);
            }
        });
        gym = findViewById(R.id.gymworkout);
        home = findViewById(R.id.homeworkout);
        glss1 = findViewById(R.id.glss1);
        glss2 = findViewById(R.id.glss2);
        glss3 = findViewById(R.id.glss3);
        glss4 = findViewById(R.id.glss4);
        glss5 = findViewById(R.id.glss5);
        glss6 = findViewById(R.id.glss6);
        glss7 = findViewById(R.id.glss7);
        glss8 = findViewById(R.id.glss8);
        navigationn = findViewById(R.id.navigation);
        menuusernamev =findViewById(R.id.menuusernamev);


        //  Validate VIEWS
        //  Handle Views
        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationn.setVisibility(View.VISIBLE);
                navi_open = true;
            }
        });

        defuserimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });
        glss1.setOnClickListener(this);
        glss2.setOnClickListener(this);
        glss3.setOnClickListener(this);
        glss4.setOnClickListener(this);
        glss5.setOnClickListener(this);
        glss6.setOnClickListener(this);
        glss7.setOnClickListener(this);
        glss8.setOnClickListener(this);
        gym.setOnClickListener(this);
        home.setOnClickListener(this);
        logobackrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationn.setVisibility(View.INVISIBLE);
                navi_open = false;
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (fAuth.getCurrentUser() ==null){
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }
//    }
//
//    private void loadUserInfo() {
//        user  = fAuth.getCurrentUser();
//
////        String photoUrl = user.getPhotoUrl().toString();
////        String displayName = user.getDisplayName();
////        usernamev.setText(displayName);
//    }

    @Override
    public void onBackPressed() {
        if (navi_open) {
            navi_open = false;
            navigationn.setVisibility(View.INVISIBLE);
            return;
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                finishAffinity();
                return;
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
            return;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gymworkout: {
                Intent intent=new Intent(MainProfileActivity.this,HomeWorkoutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.homeworkout:{
                Intent intent=new Intent(MainProfileActivity.this,gymWorkoutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.glss1:{
                if(a0==true){
                    ++counter;
                    glss1.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a0=false;
                }
                else{
                    --counter;
                    glss1.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a0=true;
                }
                break;
            }
            case R.id.glss2: {
                if(a1==true){
                    counter++;
                    glss2.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a1=false;
                }
                else{
                    --counter;
                    glss2.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a1=true;
                }
                break;
            }
            case R.id.glss3:{
                if(a2==true){
                    counter++;
                    glss3.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a2=false;
                }
                else{
                    --counter;
                    glss3.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a2=true;
                }
                break;
            }
            case R.id.glss4:{
                if(a3==true){
                    counter++;
                    glss4.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a3=false;
                }
                else{
                    --counter;
                    glss4.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a3=true;
                }
                break;
            }
            case R.id.glss5:{
                if(a4==true){
                    counter++;
                    glss5.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a4=false;
                }
                else{
                    --counter;
                    glss5.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a4=true;
                }
                break;
            }
            case R.id.glss6:{
                if(a5==true){
                    counter++;
                    glss6.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a5=false;
                }
                else{
                    --counter;
                    glss6.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a5=true;
                }
                break;
            }
            case R.id.glss7:{
                if(a6==true){
                    counter++;
                    glss7.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a6=false;
                }
                else{
                    --counter;
                    glss7.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a6=true;
                }
                break;
            }
            case R.id.glss8:{
                if(a7==true){
                    counter++;
                    glss8.setImageResource(R.drawable.fullglass);
                    cupcounter.setText(Integer.toString(counter));
                    a7=false;
                }
                else{
                    --counter;
                    glss8.setImageResource(R.drawable.emptyglass);
                    cupcounter.setText(Integer.toString(counter));
                    a7=true;
                }
                break;
            }


        }

        if(counter==8){
            checkcorrect.setVisibility(View.VISIBLE);
        }
        else {
            checkcorrect.setVisibility(View.INVISIBLE);
        }

    }

    public void openProfileMenu(View view) {
        Intent intent=new Intent(MainProfileActivity.this,ProfileMenuActivity.class);
        startActivity(intent);
    }

    public void openSettingMenu(View view) {
        Intent intent=new Intent(MainProfileActivity.this,SettingMenuActivity.class);
        startActivity(intent);
    }

    public void openProgressMenu(View view) {
        Intent intent=new Intent(MainProfileActivity.this,ProgressMenuActivity.class);
        startActivity(intent);
    }

    public void openMealMenu(View view) {
        Intent intent=new Intent(MainProfileActivity.this,MealMenuActivity.class);
        startActivity(intent);
    }
}
