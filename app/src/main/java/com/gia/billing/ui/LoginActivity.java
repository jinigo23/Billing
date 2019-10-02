package com.gia.billing.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gia.billing.R;
import com.gia.billing.helper.AppDataManager;
import com.gia.billing.helper.PreferenceManager;
import com.gia.billing.root.MyApplication;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private static final String USER_LOGIN = "User_login";
    private Button login_button;
    private CheckBox admin_checkBox;
    private TextInputEditText login_phone, login_password;
    private TextInputLayout login_phone_text_layout, login_password_text_layout;
    SharedPreferences sharedPreferences, loginSharedPreferences;
    Intent intent;
    private TextView login_welcome_text, login_forgot_password;
    int logged_in_successfully = 1;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginSharedPreferences = MyApplication.getApplication().getSharedPreferences("User_details", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("User_signup", MODE_PRIVATE);

        login_welcome_text = (TextView) findViewById(R.id.login_welcome_text);
        login_forgot_password = (TextView) findViewById(R.id.login_forgot_password);
        login_phone_text_layout = (TextInputLayout) findViewById(R.id.login_phone_text_layout);
        login_password_text_layout = (TextInputLayout) findViewById(R.id.login_password_text_layout);
        login_phone = (TextInputEditText) findViewById(R.id.login_phone);
        login_password = (TextInputEditText) findViewById(R.id.login_password);
        admin_checkBox = (CheckBox) findViewById(R.id.admin_checkBox);
        login_button = (Button) findViewById(R.id.login_button);

        String user_name = sharedPreferences.getString("User_Name", null);
        login_welcome_text.setText("Hey " + user_name);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String phone = sharedPreferences.getString("Phone_Number", null);
                /*String phone = PreferenceManager.getInstance().getString("Phone_Number");
                String password = PreferenceManager.getInstance().getString("User_Password");*/

                String phone = "9999988888";
                String password = "mmmmmm";

                String user_phone = login_phone.getText().toString().trim();
                String user_password = login_password.getText().toString().trim();
                if (user_phone.equals(phone) && user_password.equals(password)) {
                    /*SharedPreferences.Editor editor = loginSharedPreferences.edit();
                    editor.putString("Logged_in", user_password);
                    editor.putBoolean("Admin", admin_checkBox.isChecked());
                    editor.commit();*/
                    boolean isAdmin = admin_checkBox.isChecked();
                    PreferenceManager.getInstance().addPreference("Admin", isAdmin);
                    PreferenceManager.getInstance().addPreference("Logged_in", user_password);

                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    login_phone_text_layout.setError("Enter the valid phone number");
                    login_password_text_layout.setError("Enter the valid password");
                }
            }
        });

        login_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View popupView = layoutInflater.inflate(R.layout.popup_forgot_password, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Forgot password?");
                builder.setView(popupView);

                final TextInputEditText enter_phone = popupView.findViewById(R.id.login_phone);
                final TextInputEditText forgot_password = popupView.findViewById(R.id.login_phone);
                final TextInputLayout enter_phone_text_layout = popupView.findViewById(R.id.enter_phone_text_layout);
                final TextInputLayout forgot_password_text_layout = popupView.findViewById(R.id.forgot_password_text_layout);

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}
