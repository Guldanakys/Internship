package com.example.internship;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreenActivity extends AppCompatActivity {

    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mLayout = findViewById(R.id.splash_layout);

        fadeInAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMain();
            }
        }, 1000);

    }

    private void fadeInAnimation() {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mLayout.setAnimation(fadeIn);
    }

    public void startMain() {
        Intent starter = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(starter);
        finish();
    }
}
