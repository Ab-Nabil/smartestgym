package com.example.afinal.Profiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class ProgressMenuActivity extends AppCompatActivity {
    LineChartView lineChartView;
    float day1ex,day2ex,day3ex,day4ex;
    String[] axisData = {"0","day1", "day2", "day3", "day4"};
    float[] yAxisData ;
    float i;
    ImageView progressBackRow,mprofilepic;
    TextView usernamev;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_menu);
        day1ex = 0;
        day2ex = 0;
        day3ex = 0;
        day4ex = 0;
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        mprofilepic = findViewById(R.id.defuserimgprofile);
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(mprofilepic);

            }
        });
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                day1ex = Float.valueOf(documentSnapshot.getString("exercalories"));
                day2ex = Float.valueOf(documentSnapshot.getString("exercalories1"));
                day3ex = Float.valueOf(documentSnapshot.getString("exercalories2"));
                day4ex = Float.valueOf(documentSnapshot.getString("exercalories3"));

                yAxisData = new float[]{0, day1ex, day2ex, day3ex, day4ex};
                lineChartView = findViewById(R.id.graph);
                //11- Next declare 2 types of List like the following.
                List yAxisValues = new ArrayList();
                List axisValues = new ArrayList();
                //12- Declare and initialize the line which appears inside graph chart, this line will hold the values of Y-Axis.
                Line line = new Line(yAxisValues).setColor(Color.parseColor("#000000"));
                //13- Next you need to add Axis and Y-Axis data inside yAxisValues and axisValues lists.
                for(int i = 0; i < axisData.length; i++){
                    axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
                }
                for (int i = 0; i < yAxisData.length; i++){
                    yAxisValues.add(new PointValue(i, yAxisData[i]));
                }
                //14- Declare a list of a type Line.
                List lines = new ArrayList();
                lines.add(line);

                //15- Now you can add the graph line to the overall data chart.
                LineChartData data = new LineChartData();
                data.setLines(lines);
                //23- Let's change the color of line chart to something nice
                line = new Line(yAxisValues).setColor(Color.parseColor("#000000"));


                Axis axis = new Axis();
                axis.setValues(axisValues);
                //27- You can change the text color to any color that you like
                axis.setTextColor(Color.parseColor("#000000"));
                data.setAxisXBottom(axis);
                //25- Let's increase the text size of the Axis data to be able to see it clearly.
                axis.setTextSize(16);


                //21- Don't worry about the text size as we will fix that later. Now let's work on Y-Axis values.
                Axis yAxis = new Axis();
                data.setAxisYLeft(yAxis);
                //29- Change the text size and color for Y-Axis data by adding the following code.
                yAxis.setTextColor(Color.parseColor("#000000"));
                yAxis.setTextSize(16);


                //16- Now you need to add the following code to be able to see the Android line chart.
                lineChartView.setLineChartData(data);

            }
        });

        usernamev=findViewById(R.id.usernamevprog);
        Intent intent = getIntent();
        usernamev.setText(intent.getStringExtra("username"));
        progressBackRow=(ImageView)findViewById(R.id.progressBackRow);
        progressBackRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}
