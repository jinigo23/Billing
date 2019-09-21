package com.gia.billing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
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
    private TextView bill_print_price;
    private Button bill_print_button;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_report);

        billList = (RecyclerView) findViewById(R.id.product_list_item);
        bill_print_button = findViewById(R.id.bill_print_button);
        bill_print_price = findViewById(R.id.bill_print_price);

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

        float invoice_price=0;

        for (int i = 0; i < list.size(); i++) {
            Invoice invoice = list.get(i);
            invoice_price = invoice_price + (float) invoice.getPrice();
        }
        Log.d("Invoice Total price :: ", "invoice : " + invoice_price);

//₹
        bill_print_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(InvoiceReport.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.print_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        bill_print_price.setText("Total price : ₹ "+invoice_price);
    }
}
