package com.gia.billing.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.gia.billing.R;
import com.gia.billing.root.MyApplication;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreferenceManager {
    private static PreferenceManager instanceManager = new PreferenceManager();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static PreferenceManager getInstance() {
        if (instanceManager == null) {
            instanceManager = new PreferenceManager();
        }
        return instanceManager;
    }

    private PreferenceManager() {
        sharedPreferences = MyApplication.getApplication().getSharedPreferences("User_details", Context.MODE_PRIVATE);
    }

    public synchronized void addPreference(String key, ArrayList value) {
        if (key != null && value != null) {
            ArrayList<String> stringArrayList = new ArrayList<String>();
            Set<String> set = new HashSet<String>();
            set.addAll(stringArrayList);
            editor = sharedPreferences.edit();
            editor.putStringSet(key, set);
            editor.apply();
        }
    }

    public synchronized void addPreference(String key, String value) {
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

    public synchronized void addPreference(String key, Boolean value) {
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

    public synchronized void addPreference(String key, int value) {
        if (key != null) {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
            Log.d("prefs", "Data stored : " + key + " " + value);
        } else {
            toastData("Unable to add");
            Log.d("prefs", "Unable to store the data");
        }
    }

    public synchronized String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public synchronized boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public synchronized int getInteger(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public synchronized void removePreference(String key) {
        if (key != null) {
            editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        } else {
            Log.d("Remove prefernce", "Unable to remove " + key);
        }
    }

    public void toastData(String data) {
        Toast.makeText(MyApplication.getApplication().getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }

    public void popupMenu(View view, final TextInputEditText inputEditText, int menu_id) {
        PopupMenu popupMenu = new PopupMenu(MyApplication.getApplication(), view);
        popupMenu.getMenuInflater().inflate(menu_id, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                inputEditText.setText(menuItem.getTitle());
                return true;
            }
        });
    }

    public void popupMenu(View view, final TextView textView, int menu_id) {
        PopupMenu popupMenu = new PopupMenu(MyApplication.getApplication(), view);
        popupMenu.getMenuInflater().inflate(menu_id, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                textView.setText(menuItem.getTitle());
                return true;
            }
        });
    }
    public void popupMenu(View view, final ImageButton imageButton, int menu_id) {
        PopupMenu popupMenu = new PopupMenu(MyApplication.getApplication(), view);
        popupMenu.getMenuInflater().inflate(menu_id, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                imageButton.setText(menuItem.getTitle());
                return true;
            }
        });
    }

}
