package com.gia.billing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gia.billing.R;
import com.gia.billing.model.Products;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Products> {

    private Context context;
    private ArrayList<Products> productsList;
//    private ArrayList<String> product_name, price, vendor;

    /*public ProductAdapter(Context context, ArrayList<String> product_name, ArrayList<String> price, ArrayList<String> vendor) {
        super(context, 0);
        this.context = context;
        this.product_name = product_name;
        this.price = price;
        this.vendor = vendor;
    }*/

    public ProductAdapter(Context context, ArrayList<Products> productsList) {
        super(context, 0, productsList);
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View productItem = convertView;
        ViewHolder holder;

        Products products = productsList.get(position);
        if (productItem == null) {
            productItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.product_image = productItem.findViewById(R.id.product_Image);
            holder.name = productItem.findViewById(R.id.item_name);
            holder.quantity = productItem.findViewById(R.id.item_quantity);
            holder.price = productItem.findViewById(R.id.item_price);
            productItem.setTag(holder);
        } else {
            holder = (ViewHolder) productItem.getTag();
        }
        for (int i = 0; i < productsList.size(); i++) {
            holder.product_image.setImageResource(products.getImage());
            holder.name.setText(products.getName());
            holder.quantity.setText(String.valueOf(products.getQuantity() + products.getQuantityType()));
            String itPrice = String.format("%.02f", products.getPrice());
            holder.price.setText("₹ " + itPrice);
        }
            /*holder.product_image.setImageResource(products.getImage());
            holder.name.setText(products.getName());
            holder.quantity.setText(products.getQuantity()+" "+products.getQuantityType());
            holder.price.setText(String.valueOf(products.getPrice()));*/

        return productItem;
    }

    private class ViewHolder {
        private TextView name, quantity, price;
        private ImageView product_image;
    }
}
