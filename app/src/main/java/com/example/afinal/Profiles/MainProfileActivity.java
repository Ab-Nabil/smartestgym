package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.RegisterActivity;
import com.google.android.material.navigation.NavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainProfileActivity extends AppCompatActivity implements View.OnClickListener{

    Button menuicon;
    NavigationView navigationn;
    ImageView gym,home,logobackrow,defuserimg,checkcorrect;
    TextView usernamev,cupcounter;
    int counter=0;
    ImageView glss1,glss2,glss3,glss4,glss5,glss6,glss7,glss8;
    boolean a0=true;boolean a1=true;boolean a2=true;boolean a3=true;boolean a4=true;boolean a5=true;boolean a6=true;boolean a7=true;
    private static final int GALLERY_CODE=1;
    private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        menuicon=(Button) findViewById(R.id.menuicon);
        logobackrow=(ImageView)findViewById(R.id.menuBackRow);
        cupcounter=(TextView) findViewById(R.id.cupcounterr);
        usernamev=(TextView)findViewById(R.id.usernamev);
        checkcorrect=(ImageView)findViewById(R.id.checkcorrect);
        defuserimg=(ImageView)findViewById(R.id.defuserimg);
        gym=(ImageView)findViewById(R.id.gymworkout);
        home=(ImageView)findViewById(R.id.homeworkout);
        glss1=(ImageView) findViewById(R.id.glss1);
        glss2=(ImageView) findViewById(R.id.glss2);
        glss3=(ImageView) findViewById(R.id.glss3);
        glss4=(ImageView) findViewById(R.id.glss4);
        glss5=(ImageView) findViewById(R.id.glss5);
        glss6=(ImageView) findViewById(R.id.glss6);
        glss7=(ImageView) findViewById(R.id.glss7);
        glss8=(ImageView) findViewById(R.id.glss8);
        navigationn=(NavigationView)findViewById(R.id.navigation);
        //  Validate VIEWS
        //  Handle Views
        menuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationn.setVisibility(View.VISIBLE);
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
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && requestCode == resultCode && data != null) {
            mImageUri=data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

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
