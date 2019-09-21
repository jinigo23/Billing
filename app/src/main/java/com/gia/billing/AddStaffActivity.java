package com.gia.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gia.billing.helper.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddStaffActivity extends AppCompatActivity {

    private TextInputLayout add_staff_name_layout, add_staff_username_layout, add_staff_password_layout;
    private TextInputEditText add_staff_name, add_staff_username, add_staff_password;
    private Button create_button;
    private String staff_name, staff_phone, staff_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        add_staff_name_layout = (TextInputLayout) findViewById(R.id.add_staff_name_layout);
        add_staff_username_layout = (TextInputLayout) findViewById(R.id.add_staff_username_layout);
        add_staff_password_layout = (TextInputLayout) findViewById(R.id.add_staff_password_layout);
        add_staff_name = (TextInputEditText) findViewById(R.id.add_staff_name);
        add_staff_username = (TextInputEditText) findViewById(R.id.add_staff_username);
        add_staff_password = (TextInputEditText) findViewById(R.id.add_staff_password);
        create_button = (Button) findViewById(R.id.create_button);

        staff_name = add_staff_name.getText().toString().trim();
        staff_phone = add_staff_username.getText().toString().trim();
        staff_password = add_staff_password.getText().toString().trim();

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staff_name.length() != 0) {
                    if (staff_phone.length() == 10) {
                        if (add_staff_password.length() >= 3) {
                            PreferenceManager.getInstance().addPreference("Staff_1_name", staff_name);
                            PreferenceManager.getInstance().addPreference("Staff_1_phone", staff_phone);
                            PreferenceManager.getInstance().addPreference("Staff_1_password", staff_password);
                            PreferenceManager.getInstance().toastData("Successfully created");
                        } else if (add_staff_password.length() == 0) {
                            add_staff_password_layout.setError("Enter the password");
                        } else {
                            add_staff_password_layout.setError("Password should be minimum 3");
                        }
                    } else {
                        add_staff_username_layout.setError("Phone number should be 10 digits");
                    }
                } else {
                    add_staff_name_layout.setError("Enter the valid name");
                }
            }
        });
    }
}
