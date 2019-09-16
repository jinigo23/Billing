package com.gia.billing.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.gia.billing.root.MyApplication;

public class PreferenceManager {
    private static PreferenceManager instanceManager = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static PreferenceManager getInstance() {
        if (instanceManager == null) {
            instanceManager = new PreferenceManager();
        }
        return instanceManager;
    }

    private PreferenceManager() {
        sharedPreferences = MyApplication.getApplication().getSharedPreferences("User_signup", Context.MODE_PRIVATE);
    }

    public synchronized void addPreferences(String key, String value) {
        if (key != null && value != null) {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
            Log.d("prefs", "Data stored : " + key + " " + value);

        } else {
            toastData("Unable to add");
            Log.d("prefs", "Unable to store the data");
        }
    }

    public synchronized void addPreferences(String key, Boolean value) {
        if (key != null) {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
            Log.d("prefs", "Data stored : " + key + " " + value);
        } else {
            toastData("Unable to add");
            Log.d("prefs", "Unable to store the data");
        }
    }

    public void toastData(String data) {
        Toast.makeText(MyApplication.getApplication().getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }
}
