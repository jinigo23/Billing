package com.gia.billing.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gia.billing.R;
import com.gia.billing.model.Invoice;

import java.util.ArrayList;

public class InvoiceRecyclerAdapter extends RecyclerView.Adapter<InvoiceRecyclerAdapter.ViewHolder> {

    private ArrayList<Invoice> invoiceArrayList;
    private Context context;

    public InvoiceRecyclerAdapter(Context context, ArrayList<Invoice> invoiceArrayList) {
        this.context = context;
        this.invoiceArrayList = invoiceArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from (viewGroup.getContext ());
//        View view = layoutInflater.inflate (R.layout.invoice_list_item, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invoice_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Invoice invoice = invoiceArrayList.get(position);
        float invoice_total = 0;
        for (int i = 0; i < invoiceArrayList.size(); i++) {
            viewHolder.name.setText(invoice.getName());
            viewHolder.quantity.setText(invoice.getQuantity() + invoice.getQuantityType());
            String invoicePrice = String.format("%.02f", Float.valueOf(invoice.getPrice()));
            viewHolder.price.setText("₹ " + invoicePrice);
        }
    }

    @Override
    public int getItemCount() {
        return invoiceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, quantity, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.billed_product_name);
            quantity = itemView.findViewById(R.id.billed_quantity);
            price = itemView.findViewById(R.id.billed_price);
        }
    }
}
