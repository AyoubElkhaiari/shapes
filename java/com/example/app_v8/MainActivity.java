package com.example.app_v8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout ;
    Button btnShow ;
    Animation scaleUp, scaleDown ;
    Dialog animatedDialog ;
    private boolean isClicked = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* === animated background === */
        layout = findViewById(R.id.layout) ;

        AnimationDrawable ad = (AnimationDrawable) layout.getBackground();
        ad.setEnterFadeDuration(2500);
        ad.setExitFadeDuration(5000);
        ad.start();

        btnShow = findViewById(R.id.showDialog) ;
        /* === setting animation to button  === */
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up) ;
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down) ;

//        btnShow.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    btnShow.startAnimation(scaleUp);
//                }else if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    btnShow.startAnimation(scaleDown);
//                }
//                return true ;
//            }
//        });
        btnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!isClicked) {
                        btnShow.startAnimation(scaleUp);
                    }
                    isClicked = false;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isClicked = true;
                    btnShow.startAnimation(scaleDown);
                }
                return false;
            }
        });






        animatedDialog = new Dialog(this, R.style.dialogstyle) ;
        animatedDialog.setContentView(R.layout.dialog);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","clicked");
                animatedDialog.show();
            }
        });

        Button btnReset = animatedDialog.findViewById(R.id.reset) ;

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedDialog.dismiss();


            }
        });

    }
}