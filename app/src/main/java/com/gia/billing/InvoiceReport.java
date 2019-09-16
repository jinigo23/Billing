package com.gia.billing;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gia.billing.adapter.InvoiceRecyclerAdapter;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.model.Bill;
import com.gia.billing.model.Cart;
import com.gia.billing.model.Invoice;

import java.util.ArrayList;

public class InvoiceReport extends AppCompatActivity {

    private TextView name, quantity, price;
    private RecyclerView billList;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_report);

        billList = (RecyclerView) findViewById(R.id.product_list_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        billList.setLayoutManager(layoutManager);

        int bill_id = getIntent().getExtras().getInt("Bill_Number");

        final ArrayList<Invoice> list = DatabaseHelper.getInstance(this).getAllReportList(bill_id);
        InvoiceRecyclerAdapter invoiceRecyclerAdapter = new InvoiceRecyclerAdapter(InvoiceReport.this, list);
        billList.setAdapter(invoiceRecyclerAdapter);

        Bill bill = new Bill();

        Cart cart = new Cart();
        Log.d("Cart Total price :: ", String.valueOf(cart.getTotal_price()));
        Log.d("Total price :: ", String.valueOf(bill.getTotal_price()));
        Log.d("BillList", "Bill Array List :: " + list);
    }
}
