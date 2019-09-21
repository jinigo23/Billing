package com.gia.billing.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.gia.billing.R;
import com.gia.billing.helper.PreferenceManager;
import com.gia.billing.ui.LoginActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private TextView account_user_name, account_phone_number;
    private Button button_account_change_password, button_account_logout;
    SharedPreferences sharedPreferences, loginSharedPreferences;
    private View contentView;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_account, container, false);

            sharedPreferences = this.getActivity().getSharedPreferences("User_signup", Context.MODE_PRIVATE);
            loginSharedPreferences = this.getActivity().getSharedPreferences("User_login", Context.MODE_PRIVATE);

            account_user_name = (TextView) contentView.findViewById(R.id.account_user_name);
            account_phone_number = (TextView) contentView.findViewById(R.id.account_phone_number);
            button_account_change_password = (Button) contentView.findViewById(R.id.button_account_change_password);
            button_account_logout = (Button) contentView.findViewById(R.id.button_account_logout);

            /*String account_name = sharedPreferences.getString("User_Name", null);
            String account_phone = sharedPreferences.getString("Phone_Number", null);
            String admin = loginSharedPreferences.getString("Admin", null);*/

            String account_name=PreferenceManager.getInstance().getString("User_Name");
            String account_phone=PreferenceManager.getInstance().getString("Phone_Number");
            boolean admin = PreferenceManager.getInstance().getBoolean("Admin");
            if (admin==true) {
                account_user_name.setText(account_name + " (" + "Admin" + ")");
            } else {
                account_user_name.setText(account_name);
            }
            account_phone_number.setText(account_phone);

            button_account_change_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                    View changePassword_view = layoutInflater.inflate(R.layout.popup_forgot_password, null);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Change Password");
                    builder.setView(changePassword_view);

                    TextInputLayout enter_phone_text_layout = changePassword_view.findViewById(R.id.enter_phone_text_layout);
                    TextInputLayout forgot_password_text_layout = changePassword_view.findViewById(R.id.enter_phone_text_layout);
                    final TextInputEditText change_new_password = changePassword_view.findViewById(R.id.change_new_password);
                    final TextInputEditText change_confirm_password = changePassword_view.findViewById(R.id.change_confirm_password);
                    Button change_password_btn = changePassword_view.findViewById(R.id.change_password_btn);

                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    change_password_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String new_password = change_new_password.getText().toString().trim();
                            String confirm_password = change_confirm_password.getText().toString().trim();
                            if (new_password.equals(confirm_password)) {
                                PreferenceManager.getInstance().addPreference("User_Password", new_password);
                            } else {
                                Snackbar.make(view, "Password not matching", Snackbar.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();
                        }
                    });
                }
            });

            button_account_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            PreferenceManager.getInstance().removePreference("Logged_in");
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            getActivity().finishAffinity();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
            });
        }
        return contentView;
    }

}
