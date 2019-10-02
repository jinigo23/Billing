package com.gia.billing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.gia.billing.AddStaffActivity;
import com.gia.billing.R;
import com.gia.billing.adapter.EmployeeAdapter;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    private View contentView;
    private ListView staff_list_view;
    private FloatingActionButton add_staff_floatingActionButton;
    //    private String ;
    Intent intent;
    private Toolbar dashboard_toolbar;
    private ListView emp_listview;
    private ArrayList<Employee> emp_list;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
            dashboard_toolbar = (Toolbar) contentView.findViewById(R.id.dashboard_toolbar);
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(dashboard_toolbar);
            dashboard_toolbar.setTitle("Staff list");

            add_staff_floatingActionButton = contentView.findViewById(R.id.add_emp_floatingActionButton);
            add_staff_floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getActivity(), AddStaffActivity.class);
                    getActivity().startActivity(intent);
                }
            });

            emp_list = DatabaseHelper.getInstance(getActivity()).get_emp_list();
            emp_listview = (ListView) contentView.findViewById(R.id.emp_list_view);
            EmployeeAdapter employeeAdapter = new EmployeeAdapter(getActivity(), emp_list);
            emp_listview.setAdapter(employeeAdapter);

        }

        return contentView;
    }

}
