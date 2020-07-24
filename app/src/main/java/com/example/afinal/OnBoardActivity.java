package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.afinal.Profiles.MainProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class OnBoardActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    ViewFlipper viewFlipper2;
    Button skip,next;
    private float lastX;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_on_board );
        viewFlipper=(ViewFlipper)findViewById(R.id.viewFlipper1);
        viewFlipper2=(ViewFlipper)findViewById(R.id.viewFlipper2);

        fAuth = FirebaseAuth.getInstance();
        skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( OnBoardActivity.this, WelcomeActivity.class );
                startActivity ( intent );
            }
        } );
    }

    //to Accept the Right and Left swiping
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentX = touchevent.getX ();
                if (lastX < currentX) {
                    if (viewFlipper.getDisplayedChild () == 0)
                    {
                        break;
                    }
                    viewFlipper.setInAnimation ( this, R.anim.in_from_left );
                    viewFlipper.setOutAnimation ( this, R.anim.out_to_right );
                    viewFlipper.showNext ();
                    viewFlipper2.showNext ();
                } else if (lastX > currentX) {
                    if (viewFlipper.getDisplayedChild () == 1) {
                        Intent intent = new Intent ( OnBoardActivity.this, WelcomeActivity.class );
                        startActivity ( intent );
                        break;
                    }
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation ( this, R.anim.in_from_right );
                    viewFlipper.setOutAnimation ( this, R.anim.out_to_left );
                    // Show The Previous Screen
                    viewFlipper.showPrevious ();
                    viewFlipper2.showPrevious ();

                }
                break;
            }

        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fAuth.getCurrentUser()!= null){
            Intent intent = new Intent(this, MainProfileActivity.class);
            startActivity(intent);
        }
    }
}
