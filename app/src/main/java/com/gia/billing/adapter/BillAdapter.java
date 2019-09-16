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
import com.gia.billing.model.Bill;

import java.util.ArrayList;

public class BillAdapter extends ArrayAdapter<Bill> {

    private ArrayList<Bill> billList;
    private Context context;

    public BillAdapter(Context context, ArrayList<Bill> billList) {
        super(context, 0, billList);
        this.billList = billList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View billItem = convertView;
        ViewHolder holder;

        Bill bill = billList.get(position);
        if (billItem == null) {
            billItem = LayoutInflater.from(context).inflate(R.layout.bill_item, parent, false);
            holder = new ViewHolder();
            holder.bill_id = billItem.findViewById(R.id.invoice_item_name);
            holder.bill_total_price = billItem.findViewById(R.id.invoice_item_price);
            holder.bill_date = billItem.findViewById(R.id.invoice_item_date);
            billItem.setTag(holder);
        } else {
            holder = (ViewHolder) billItem.getTag();
        }
        for (int i = 0; i < billList.size(); i++) {
            holder.bill_id.setText("Invoice No. "+String.valueOf(bill.getInvoice_id()));
            String itPrice = String.format("%.02f", bill.getTotal_price());
            holder.bill_total_price.setText("₹ " + itPrice);
            holder.bill_date.setText(bill.getInvoice_date());
        }
        return billItem;
    }

    private class ViewHolder {
        private TextView bill_id, bill_total_price, bill_date;
    }
}
