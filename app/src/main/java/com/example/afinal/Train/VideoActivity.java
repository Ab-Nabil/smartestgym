package com.example.afinal.Train;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView f_tv,s_tv;
    VideoView mVideoView;
    String videoName;
    ArrayList<TrainModel> trainModels;
    TrainAdapter trainAdapter;
    int position;
    String result;
    String videoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent i = getIntent();
        result = i.getStringExtra("name");
        String tool = i.getStringExtra("tool");
         position = i.getIntExtra("position",0);
        intitView();
        selectVideo();
        f_tv.setText(result);
        s_tv.setText(tool);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
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
        }
    }

    private void intitView() {

        f_tv = findViewById(R.id.video_f_tv);
        s_tv = findViewById(R.id.video_s_tv);
        mVideoView = findViewById(R.id.video_view);

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
            
        }
    }
}
