package com.gia.billing.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.gia.billing.R;
import com.gia.billing.helper.PreferenceManager;
import com.gia.billing.root.MyApplication;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    private static final String USER_SIGNUP = "User_signup";
    private Button signup_button;
    SharedPreferences sharedPreferences;
    private TextInputEditText signup_user_name, signup_phone, signup_password;
    private TextInputLayout signup_user_name_text_layout, signup_phone_text_layout, signup_password_text_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        sharedPreferences = getSharedPreferences(USER_SIGNUP, Context.MODE_PRIVATE);

        signup_user_name_text_layout = (TextInputLayout) findViewById(R.id.signup_user_name_text_layout);
        signup_phone_text_layout = (TextInputLayout) findViewById(R.id.signup_phone_text_layout);
        signup_password_text_layout = (TextInputLayout) findViewById(R.id.signup_password_text_layout);
        signup_user_name = (TextInputEditText) findViewById(R.id.signup_user_name);
        signup_phone = (TextInputEditText) findViewById(R.id.signup_phone);
        signup_password = (TextInputEditText) findViewById(R.id.signup_password);
        signup_button = (Button) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = signup_user_name.getText().toString().trim();
                String phone = signup_phone.getText().toString().trim();
                String password = signup_password.getText().toString().trim();
                if (user_name.length() >= 3 && phone.length() == 10 && password.length() >= 6) {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    PreferenceManager.getInstance().addPreference("User_Name", user_name);
                    PreferenceManager.getInstance().addPreference("Phone_Number", phone);
                    PreferenceManager.getInstance().addPreference("User_Password", password);
                    /*SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("User_Name", user_name);
                    editor.putString("Phone_Number", phone);
                    editor.putString("User_Password", password);
                    editor.commit();*/
                    Snackbar.make(view, R.string.signup_created, Snackbar.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    if (user_name.length()<3){
                        signup_user_name_text_layout.setError("Should be minimum 3 letters");
                        shakeError();
                    } else if (phone.length() != 10) {
                        signup_phone_text_layout.setError("Enter a valid phone number");
                    } else if (password.length() < 6) {
                        signup_password_text_layout.setError("Password should be minimum 6");
                    }
                        Snackbar.make(view, R.string.signup_error, Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    private TranslateAnimation shakeError() {
        TranslateAnimation animation = new TranslateAnimation(0, 10, 0, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new CycleInterpolator(7));
        return animation;
    }
}
