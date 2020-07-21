package com.example.afinal.CreateProfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class CreateWeightActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView weightbackRow;
    ImageView weightforwardRow;
    EditText weightValue;
    SharedPreferences sp;
    String mWeight;

    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
//    HashMap<String, String> user;

    String userID,gender,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_create_weight );
        weightValue = findViewById(R.id.weightEditText);
        weightbackRow = findViewById(R.id.weightBackRow);
        weightforwardRow = findViewById(R.id.weightForwardRow);
        weightbackRow.setOnClickListener(this);
        weightforwardRow.setOnClickListener(this);

        firestore= FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        userID = fAuth.getCurrentUser().getUid();

        Bundle bundle=getIntent().getExtras();

        gender =bundle.getString("g");
        username = bundle.getString("username");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weightForwardRow:{
                mWeight  = weightValue.getText().toString();
                DocumentReference documentReference = firestore.collection("users").document(userID);
//                user = new HashMap<>();
//                user.put("weight",mWeight);
//                documentReference.set(user);

                if (mWeight.isEmpty()){
                    weightValue.setError("Enter Your Weight");
                    return;
                }
                else {
                    Intent intent ;

                    intent = new Intent(CreateWeightActivity.this, CreateHeightActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("w",mWeight);
                    intent.putExtra("g",gender);
                    startActivity(intent);
                }
                break;
            }
            case R.id.weightBackRow:{
                onBackPressed();
            }
            break;
        }
    }
}
