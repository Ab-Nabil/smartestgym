package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );

        ImageView splashbackground=(ImageView)findViewById ( R.id.splashbackground);
        Animation splashanimation = AnimationUtils.loadAnimation ( this, R.anim.splashanimation );
        splashbackground.startAnimation ( splashanimation );
        final Intent i =new Intent(SplashActivity.this,OnBoardActivity.class);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

}
