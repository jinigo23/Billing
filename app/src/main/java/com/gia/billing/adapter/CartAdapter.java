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
import com.gia.billing.model.Cart;
import com.gia.billing.model.Products;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Cart> {

    private final Context mContext;
    private ArrayList<Cart> cartList;

    public CartAdapter(Context context, ArrayList<Cart> cartList) {
        super(context, 0, cartList);
        mContext = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        View cartItem = convertView;
        Cart currentCart = cartList.get(position);
        Products products = currentCart.getProducts();

        if (cartItem == null) {
            cartItem = LayoutInflater.from(mContext).inflate(R.layout.cart_list_item, parent, false);

            holder = new ViewHolder();

            holder.cart_product_image = cartItem.findViewById(R.id.cart_product_Image);
            holder.name = cartItem.findViewById(R.id.cart_item_name);
            holder.quantity = cartItem.findViewById(R.id.cart_item_quantity);
            holder.price = cartItem.findViewById(R.id.cart_item_price);
            /*holder.cart_quantity_increament = cartItem.findViewById(R.id.cart_quantity_increament);
            holder.cart_quantity_decreament = cartItem.findViewById(R.id.cart_quantity_decreament);
            holder.cart_quantity = cartItem.findViewById(R.id.cart_quantity);*/

            cartItem.setTag(holder);

        } else {
            holder = (ViewHolder) cartItem.getTag();
        }

        for (int i = 0; i < cartList.size(); i++) {
            holder.cart_product_image.setImageResource(currentCart.getProduct_Image());
            holder.name.setText(currentCart.getName());
            int itQuantity = currentCart.getQuantity()*currentCart.getNoOfQuantity();
            holder.quantity.setText(Integer.toString(itQuantity) + currentCart.getQuantityType());
            float itPrice = currentCart.getPrice();
            String proPrice = String.format("%.02f", itPrice);
            holder.price.setText("₹" + proPrice);
            /*String noOfQuantity = String.valueOf(currentCart.getNoOfQuantity());
            holder.cart_quantity.setText(noOfQuantity);

            holder.cart_quantity_increament.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String item_quantity=holder.cart_quantity.getText().toString();
                    int plus = Integer.valueOf(item_quantity);
                    if (plus <= 4) {
                        plus++;
                        holder.cart_quantity.setText(String.valueOf(plus));
                    } else {
                        Snackbar.make(view, "Limit reached", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });

            holder.cart_quantity_decreament.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String item_quantity=holder.cart_quantity.getText().toString();
                    int minus = Integer.valueOf(item_quantity);

                    if (minus >= 2) {
                        minus--;
                        holder.cart_quantity.setText(String.valueOf(minus));
                    }
                }
            });*/
        }
        return cartItem;
    }

    private class ViewHolder {
        private TextView name, quantity, price, cart_quantity;
        private ImageView cart_product_image, cart_quantity_increament, cart_quantity_decreament;
    }
}
