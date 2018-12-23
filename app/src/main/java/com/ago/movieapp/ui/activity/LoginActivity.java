package com.ago.movieapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ago.movieapp.R;
import com.ago.movieapp.data.cache.sharedpreference.UserPreference;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginButton loginButton;
    private TextView textView_skip;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        initUI();
        initFacebookLogin();
    }

    private void initUI(){

        textView_skip = findViewById(R.id.textView_skip);
        textView_skip.setOnClickListener(this);
    }

    private void initFacebookLogin(){

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.loginButton);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();

                //cache user data in sharedPref
                UserPreference.getInstance().setID(profile.getId());
                UserPreference.getInstance().setName(profile.getFirstName());
                UserPreference.getInstance().setImageURL(profile.getProfilePictureUri(500,500).toString());

                goHome();
            }

            @Override
            public void onCancel() {
                }

            @Override
            public void onError(FacebookException exception) {
            }
        });


    }

    private void goHome(){

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){

            case R.id.textView_skip:
                intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
