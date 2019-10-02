package com.gia.billing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gia.billing.R;
import com.gia.billing.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private final ArrayList<Employee> emp_list;
    private final Context context;

    public EmployeeAdapter(@NonNull Context context, ArrayList<Employee> emp_list) {
        super(context, 0, emp_list);
        this.emp_list = emp_list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View emp_item = convertView;
        ViewHolder holder;
        Employee employee = emp_list.get(position);
        if (emp_item == null) {
            emp_item = LayoutInflater.from(context).inflate(R.layout.employee_item_view, parent, false);
            holder = new ViewHolder();
            holder.emp_index = emp_item.findViewById(R.id.emp_index);
            holder.emp_name = emp_item.findViewById(R.id.emp_name);
            holder.emp_phone = emp_item.findViewById(R.id.emp_phone);
            emp_item.setTag(holder);
        } else {
            holder = (ViewHolder) emp_item.getTag();
        }
        int index = 0;
        for (int i = 0; i < emp_list.size(); i++) {
            index=position+1;
            holder.emp_index.setText(String.valueOf(index));
            holder.emp_name.setText("Name : "+employee.getEmp_name());
            holder.emp_phone.setText("Phone : "+employee.getEmp_phone());
        }
        return emp_item;
    }

    private class ViewHolder {
        private TextView emp_index, emp_name, emp_phone;
    }
}
