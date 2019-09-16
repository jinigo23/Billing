package com.gia.billing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.R;
import com.gia.billing.model.Invoice;

import java.util.ArrayList;

public class InvoiceAdapter extends ArrayAdapter<Invoice> {

    DatabaseHelper databaseHelper;
    private Context context;
    private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

    public InvoiceAdapter(@NonNull Context context, ArrayList<Invoice> invoiceList) {
        super(context, 0, invoiceList);
        this.context = context;
        this.invoiceList = invoiceList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View invoiceItem = convertView;
        ViewHolder holder;

        Invoice invoice = invoiceList.get(position);
        if (invoiceItem == null) {
            invoiceItem = LayoutInflater.from(context).inflate(R.layout.invoice_list_item, parent, false);
            holder = new ViewHolder();
            holder.name = invoiceItem.findViewById(R.id.product_name);
            holder.quantity = invoiceItem.findViewById(R.id.quantity);
            holder.price = invoiceItem.findViewById(R.id.price);
            invoiceItem.setTag(holder);
        } else {
            holder = (ViewHolder) invoiceItem.getTag();
        }

        for (int i = 0; i < invoiceList.size(); i++) {
            String invoice_no = String.valueOf(invoice.getProduct_invoice_no());
            holder.name.setText(invoice.getName());
            holder.quantity.setText(invoice.getQuantity() + invoice.getQuantityType());
            String invoicePrice = String.format("%.02f", Float.valueOf(invoice.getPrice()));
            holder.price.setText("₹." + invoicePrice);
        }
        return invoiceItem;
    }

    private class ViewHolder {
        private TextView name, quantity, price;
    }
}
