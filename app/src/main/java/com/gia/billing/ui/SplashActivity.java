package com.gia.billing.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gia.billing.R;
import com.gia.billing.helper.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private ImageView circle_splash;
    SharedPreferences preferences, login_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        circle_splash = (ImageView) findViewById(R.id.circle_splash);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_circle);
        circle_splash.startAnimation(animation);

        preferences = getSharedPreferences("User_signup", MODE_PRIVATE);
        login_preferences = getSharedPreferences("User_login", MODE_PRIVATE);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String phone = PreferenceManager.getInstance().getString("Phone_Number");
                String login_password = PreferenceManager.getInstance().getString("Logged_in");
//                boolean isLoggedin = preferences.getBoolean("Logged_in", false);
                int login = preferences.getInt("Log_in", 0);

                /*if (phone == null) {
                    Intent intent = new Intent(SplashActivity.this, SignupActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("Signup :: ", "Login page " + phone);
                } else*/
                if (login_password == null) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("Login :: ", "Login page " + login_password);
                } else if (login_password.length()>1) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("Login successfully :: ", "Login successfully " + phone);
                }

            }
        }, 2000);
    }

}
