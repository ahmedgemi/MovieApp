package com.ago.movieapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ago.movieapp.R;
import com.ago.movieapp.data.cache.sharedpreference.UserPreference;


public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (UserPreference.getInstance().isFirstTime()){

                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);

                    //Shared Element transaction animation only supported for SDK >= 21
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(SplashActivity.this, imageView, "splash_image");

                        startActivity(intent, options.toBundle());
                    }
                    else {
                        startActivity(intent);
                    }

                }
                else {
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                }
            }
        },2000);
    }
}
