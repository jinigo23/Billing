package com.gia.billing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.gia.billing.InvoiceReport;
import com.gia.billing.R;
import com.gia.billing.adapter.BillAdapter;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.model.Bill;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends Fragment {

    private Toolbar bill_toolbar;
    private ListView billNumber;
    private ArrayList<Bill> billList;


    public BillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        bill_toolbar = view.findViewById(R.id.bill_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(bill_toolbar);
        activity.setTitle("Invoice");

        billNumber = view.findViewById(R.id.invoiceItemsList);
        billList = DatabaseHelper.getInstance(getActivity()).getBillList();

        final BillAdapter adapter = new BillAdapter(getActivity(), billList);
        billNumber.setAdapter(adapter);

        billNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bill bill = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), InvoiceReport.class);
                intent.putExtra("Bill_Number", bill.getInvoice_id());
                intent.putExtra("Bill_Price", bill.getTotal_price());
                startActivity(intent);
            }
        });

        Log.d("Bill list", "Bill items " + billList);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
