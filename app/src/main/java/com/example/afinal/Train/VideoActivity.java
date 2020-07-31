package com.example.afinal.Train;

import android.content.Intent;
    import android.content.res.Resources;
    import android.media.MediaPlayer;
    import android.net.Uri;
    import android.os.Bundle;
    import android.os.CountDownTimer;
    import android.os.SystemClock;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.Chronometer;
    import android.widget.ImageView;
    import android.widget.MediaController;
    import android.widget.TextView;
    import android.widget.Toast;
    import android.widget.VideoView;

    import com.example.afinal.Profiles.MainProfileActivity;
import com.example.afinal.Profiles.ProgressMenuActivity;
import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
    import com.example.afinal.Train.PlanA.TrainAdapter;
    import com.example.afinal.Train.PlanA.TrainModel;
    import com.example.afinal.R;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.DocumentSnapshot;
    import com.google.firebase.firestore.EventListener;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.FirebaseFirestoreException;
    import com.google.firebase.storage.FirebaseStorage;
    import com.google.firebase.storage.StorageReference;

    import java.text.DecimalFormat;
    import java.util.ArrayList;
    import java.util.HashMap;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    DocumentReference documentReference;
    String weightv;
    float w,exeresult;
    String exercalories1,exercalories,exercalories2,exercalories3;
    private Chronometer chronometer;
    Button start,back,complete,pause,continu,reload;
    private long pauseoffset;
    int id1,id2,id3;
    private TextView rest1,rest2;
    CountDownTimer downTimer1,downTimer2;
    CheckBox checkBox1,checkBox2,checkBox3;
    ImageView backarrow;
    TextView f_tv,s_tv;
    VideoView mVideoView;
    String videoName,result,videoPath,mDay;
    ArrayList<TrainModel> trainModels;
    TrainAdapter trainAdapter;
    int position;
    HashMap<String, String> user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        final DecimalFormat df = new DecimalFormat("#.#");
        mDay = "";
        Intent i = getIntent();
        mDay = i.getStringExtra("day");
        result = i.getStringExtra("name");
        String tool = i.getStringExtra("tool");
        position = i.getIntExtra("position",0);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("users").document(fAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                weightv=documentSnapshot.getString("weight");
                w = Float.parseFloat(weightv);
                exercalories = documentSnapshot.getString("exercalories");
                exercalories1 = documentSnapshot.getString("exercalories1");
                exercalories2 = documentSnapshot.getString("exercalories2");
                exercalories3 = documentSnapshot.getString("exercalories3");

            }
        });

        intitView();

        backarrow.setOnClickListener(this);
        pause.setOnClickListener(this);
        complete.setOnClickListener(this);
        back.setOnClickListener(this);

        f_tv = findViewById(R.id.video_f_tv);
        s_tv = findViewById(R.id.video_s_tv);
        selectVideo();
        f_tv.setText(result);
        s_tv.setText(tool);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backarrow.setVisibility(View.INVISIBLE);
                chronometer.setBase ( SystemClock.elapsedRealtime () - pauseoffset );
                chronometer.start ();
                start.setVisibility ( View.INVISIBLE );
                pause.setVisibility(View.VISIBLE);
                checkBox1.setVisibility(View.VISIBLE);
                downTimer1=new CountDownTimer( 10000, 1000 ) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        checkBox1.setVisibility(View.INVISIBLE);
                        rest1.setText( millisUntilFinished / 1000 + " : seconds for rest" );
                    }

                    @Override
                    public void onFinish() {
                        rest1.setText("Rest 1 Finish");
                        checkBox1.setVisibility(View.INVISIBLE);
                        checkBox2.setVisibility ( View.VISIBLE );
                        chronometer.setBase ( SystemClock.elapsedRealtime () - pauseoffset );
                        chronometer.start ();
                        pause.setVisibility ( View.VISIBLE );
                    }
                };
                checkBox1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseoffset = SystemClock.elapsedRealtime () - chronometer.getBase ();
                        chronometer.stop ();
                        pause.setVisibility ( View.INVISIBLE );
                        continu.setVisibility ( View.INVISIBLE );
                        TextView xtext1 = findViewById ( R.id.t1 );
                        xtext1.setText("exercise 1 done.");
                        downTimer1.start();
                        id1=1;
                    }
                });
                downTimer2=new CountDownTimer( 10000, 1000 ) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        checkBox2.setVisibility ( View.INVISIBLE );
                        rest2.setText ( millisUntilFinished / 1000 + " : seconds for rest" );
                    }

                    @Override
                    public void onFinish() {
                        rest2.setText ( "Rest 2 finish" );
                        checkBox2.setVisibility ( View.INVISIBLE );
                        checkBox3.setVisibility ( View.VISIBLE );
                        chronometer.setBase ( SystemClock.elapsedRealtime () - pauseoffset );
                        chronometer.start ();
                        pause.setVisibility ( View.VISIBLE );
                    }
                };
                checkBox2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseoffset = SystemClock.elapsedRealtime () - chronometer.getBase ();
                        chronometer.stop ();
                        pause.setVisibility ( View.INVISIBLE );
                        TextView xtext2 = findViewById ( R.id.t2 );
                        xtext2.setText ( "exercise 2 done." );
                        downTimer2.start ();
                        id2=1;
                    }
                });
                checkBox3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkBox3.setVisibility(View.INVISIBLE);
                        pauseoffset = SystemClock.elapsedRealtime () - chronometer.getBase ();
                        chronometer.stop ();
                        pause.setVisibility ( View.INVISIBLE );
                        complete.setVisibility ( View.VISIBLE );
                        TextView xtext3 = findViewById ( R.id.t3 );
                        xtext3.setText ( "exercise 3 done." );
                        id3=1;
                    }
                });
                pause.setVisibility ( View.VISIBLE );
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop ();
                backarrow.setVisibility(View.VISIBLE);
                pauseoffset = SystemClock.elapsedRealtime () - chronometer.getBase ();
                pause.setVisibility ( View.INVISIBLE );
                continu.setVisibility ( View.VISIBLE );
                checkBox1.setVisibility ( View.INVISIBLE );
                checkBox2.setVisibility ( View.INVISIBLE );
                checkBox3.setVisibility ( View.INVISIBLE );
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase ( SystemClock.elapsedRealtime () );
                pauseoffset=0;
                Intent i = new Intent(VideoActivity.this, VideoActivity.class);  //your class
                startActivity(i);
                finish();
            }
        });

        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase ( SystemClock.elapsedRealtime () - pauseoffset );
                chronometer.start ();
                continu.setVisibility ( View.INVISIBLE );
                pause.setVisibility ( View.VISIBLE );

                if (id1 == 0)
                    checkBox1.setVisibility ( View.VISIBLE );
                else {
                    if (id2 == 0)
                        checkBox2.setVisibility ( View.VISIBLE );
                    else {
                        if (id3 == 0)
                            checkBox3.setVisibility ( View.VISIBLE );
                    }
                }
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseoffset);
                pauseoffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                reload.setVisibility(View.VISIBLE);
                continu.setVisibility(View.INVISIBLE);
                if (mDay.startsWith("1")) {
                    if (result.equals("Incline Barbell Bench Press")) {
                        exercalories = "0";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference Ref = db.collection("users").document(userID);
                        Ref.update("exercalories", exercalories);
                        Toast.makeText(VideoActivity.this, mDay, Toast.LENGTH_LONG).show();
                    }
                    float total = (float) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 60000;
                    float metValue = (float) 5.0;
                    final float caloresult = (float) ((total) * w * 3.5 * metValue) / 200;
                    Toast.makeText(VideoActivity.this, df.format(caloresult) + " calories Burned", Toast.LENGTH_SHORT).show();
                    float finalCalo = Float.parseFloat(exercalories) + caloresult;
                    //add cal to firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference Ref = db.collection("users").document(userID);
                    Ref.update("exercalories", String.valueOf(finalCalo));
                }
                else if (mDay.startsWith("2")){
                    if (result.equals("Wide-grip Lat Pulldown")) {
                        exercalories1 = "0";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference Ref = db.collection("users").document(userID);
                        Ref.update("exercalories1", exercalories1);
                    }
                    float total = (float) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 60000;
                    float metValue = (float) 5.0;
                    final float caloresult = (float) ((total) * w * 3.5 * metValue) / 200;
                    Toast.makeText(VideoActivity.this, df.format(caloresult) + " calories Burned", Toast.LENGTH_SHORT).show();
                    float finalCalo = Float.parseFloat(exercalories1) + caloresult;
                    //add cal to firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference Ref = db.collection("users").document(userID);
                    Ref.update("exercalories1", String.valueOf(finalCalo));
                }
                else if (mDay.startsWith("3")){
                    if (result.equals("Dumbbell Shoulder Press")) {
                        exercalories2 = "0";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference Ref = db.collection("users").document(userID);
                        Ref.update("exercalories2", exercalories2);
                    }
                    float total = (float) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 60000;
                    float metValue = (float) 5.0;
                    final float caloresult = (float) ((total) * w * 3.5 * metValue) / 200;
                    Toast.makeText(VideoActivity.this, df.format(caloresult) + " calories Burned", Toast.LENGTH_SHORT).show();
                    float finalCalo = Float.parseFloat(exercalories2) + caloresult;
                    //add cal to firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference Ref = db.collection("users").document(userID);
                    Ref.update("exercalories2", String.valueOf(finalCalo));
                }
                else if (mDay.startsWith("4")){
                    if (result.equals("Barbell Back Squat")) {
                        exercalories3 = "0";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference Ref = db.collection("users").document(userID);
                        Ref.update("exercalories3", exercalories3);
                    }
                    float total = (float) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 60000;
                    float metValue = (float) 5.0;
                    final float caloresult = (float) ((total) * w * 3.5 * metValue) / 200;
                    Toast.makeText(VideoActivity.this, df.format(caloresult) + " calories Burned", Toast.LENGTH_SHORT).show();
                    float finalCalo = Float.parseFloat(exercalories3) + caloresult;
                    //add cal to firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference Ref = db.collection("users").document(userID);
                    Ref.update("exercalories3", String.valueOf(finalCalo));
                }
                else if (mDay.startsWith("h")){

                    float total = (float) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 60000;
                    float metValue = (float) 5.0;
                    final float caloresult = (float) ((total) * w * 3.5 * metValue) / 200;
                    Toast.makeText(VideoActivity.this, df.format(caloresult) + " calories Burned", Toast.LENGTH_SHORT).show();
                    //add cal to firestore

                }
                backarrow.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                complete.setVisibility(View.INVISIBLE);
            }
        });
    }
        private void selectVideo() {
            switch (result){
                case "Incline Barbell Bench Press":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr1;
                    videoPath();
                    break;
                case "Dumbbell Chest Press":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr2;
                    videoPath();
                    break;
                case "Low Cable Fly":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr3;
                    videoPath();
                    break;
                case "Machine fly":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr4;
                    videoPath();
                    break;
                case "Dips":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr5;
                    videoPath();
                    break;
                case "Wide-Grip Barbell Curl":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr6;
                    videoPath();
                    break;
                case "Dumbbell Hammer Preacher Curls":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr7;
                    videoPath();
                    break;
                case "Close-Grip EZ-Bar Curl":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr8;
                    videoPath();
                    break;
                case "Crunches":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr9;
                    videoPath();
                    break;
                case "Russian Twists":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day1tr10;
                    videoPath();
                    break;

                    //day2
                case "Wide-grip Lat Pulldown":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr0;
                    videoPath();
                    break;
                case "V-Bar Lat Pulldown":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr1;
                    videoPath();
                    break;
                case "Barbell Bent-Over Row":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr2;
                    videoPath();
                    break;
                case "Straight-Arm Pulldown":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr3;
                    videoPath();
                    break;
                case "Hyperextensions":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr4;
                    videoPath();
                    break;
                case "Cable Reverse-Grip Pushdown":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr5;
                    videoPath();
                    break;
                case "rope pushdown":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr6;
                    videoPath();
                    break;
                case "dumbbell hammer preacher curl":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr7;
                    videoPath();
                    break;
                case "bent-over two-arm kickback":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day2tr8;
                    videoPath();
                    break;

                    //home
                case "Jumping Rope":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.hometr1;
                    videoPath();
                    break;
                case "Mountain Climbers":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.hometr2;
                    videoPath();
                    break;

                    //day3
                case "Dumbbell Shoulder Press":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day3tr1;
                    videoPath();
                    break;
                case "Barbell Front Raise":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day3tr2;
                    videoPath();
                    break;
                case "seated dumbbell lateral raise":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day3tr3;
                    videoPath();
                    break;
                case "Seated Arnold Press":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day3tr4;
                    videoPath();
                    break;

                    //day4
                case "Barbell Back Squat":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day4tr1;
                    videoPath();
                    break;
                case "Hack Squats (Shoulder Width)":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day4tr2;
                    videoPath();
                    break;
                case "Smith machine lunges":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day4tr3;
                    videoPath();
                    break;
                case "Seated Leg Curl":
                    videoPath = "android.resource://" + getPackageName() +"/" + R.raw.day4tr4;
                    videoPath();
                    break;


            }
        }

        private void intitView() {
            mVideoView = findViewById(R.id.video_view);
            back = findViewById(R.id.video_button_back);
            pause=findViewById(R.id.video_button_pause);
            start=findViewById(R.id.video_button_start);
            complete = findViewById(R.id.video_button_complete);
            backarrow = findViewById(R.id.video_back_arrow);
            reload=findViewById(R.id.video_button_reload);
            continu=findViewById(R.id.video_button_continue);
            rest1=findViewById(R.id.rest1);
            rest2=findViewById(R.id.rest2);
            checkBox1=findViewById(R.id.first_check_box);
            checkBox2=findViewById(R.id.second_check_box);
            checkBox3=findViewById(R.id.third_check_box);
            chronometer = findViewById ( R.id.chronometer );
            chronometer.setFormat ( "Time: %s" );
            chronometer.setBase ( SystemClock.elapsedRealtime () );
            chronometer.setOnChronometerTickListener ( new Chronometer.OnChronometerTickListener () {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long time = SystemClock.elapsedRealtime () - chronometer.getBase ();
                    int h = (int) (time / 3600000);
                    int m = (int) (time - h * 3600000) / 60000;
                    int s = (int) (time - h * 3600000 - m * 60000) /1000;
                    String t = (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
                    chronometer.setText ("Timer : "+ t );
                }
            } );
        }

        private void videoPath(){
            Uri uri = Uri.parse(videoPath);
            mVideoView.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);
            mVideoView.setMediaController(mediaController);
            mediaController.setAnchorView(mVideoView);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.video_button_back: {
//                    if (result == "Russian Twists"){
//
//                    }
                        onBackPressed();

                }

                case R.id.video_back_arrow:
                    onBackPressed();
                    break;
            }

        }
    }