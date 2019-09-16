package com.gia.billing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gia.billing.R;
import com.gia.billing.adapter.CartAdapter;
import com.gia.billing.helper.AppDataManager;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.model.Bill;
import com.gia.billing.model.Cart;
import com.gia.billing.model.Products;
import com.gia.billing.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    private ListView cartListView;
    private TextView txt_subtotal, subtotal;
    private Button save_cart, print_cart;
    private ArrayList<Cart> itemList = new ArrayList<Cart>();
    private View contentView;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_cart, container, false);

            cartListView = (ListView) contentView.findViewById(R.id.cartListView);
            txt_subtotal = (TextView) contentView.findViewById(R.id.txt_subtotal);
            subtotal = (TextView) contentView.findViewById(R.id.subtotal);
            save_cart = (Button) contentView.findViewById(R.id.save_cart);
            print_cart = (Button) contentView.findViewById(R.id.print_cart);

            save_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                    int tStamp = Integer.parseInt(timeStamp);

                    Bill bill = new Bill();
                    Cart cart = new Cart();

                    float tPrice = 0;
                    for (int i = 0; i < itemList.size(); i++) {
                        cart = itemList.get(i);
                        tPrice = tPrice + (float) cart.getPrice();
                    }
                    String billPrice = String.valueOf(tPrice);

                    float totalPrice = Float.parseFloat(billPrice);
                    Log.i("Test ", "Bill Cart Price ₹" + String.valueOf(billPrice));

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String current_date = dateFormat.format(new Date());
                    long billID = DatabaseHelper.getInstance(getActivity()).insertInvoice(totalPrice, current_date);
                    if (billID >= 0) ;
                    {
                        Log.d("Inserted Bill into DB", "Date : " + timeStamp);
                    }

                    for (int i = 0; i < itemList.size(); i++) {
                        cart = itemList.get(i);
                        Products products = cart.getProducts();
                        int invoice_no = Integer.parseInt(timeStamp);
                        String name = products.getName();
                        int quantity = cart.getQuantity();
                        String quantityType = products.getQuantityType();
                        float price = cart.getPrice();


                        boolean isInserted = DatabaseHelper.getInstance(getActivity()).insertProducts(billID, name, quantity, quantityType, price);
                        if (isInserted == true) {
                            Log.d("Inserting Data into DB", "Code : " + name + " Quantity :: " + quantity + quantityType + " Price :: " + price);
                        }
                    }
                    Toast.makeText(getActivity(), "Item Saved", Toast.LENGTH_SHORT).show();
                    Log.d("Date", "Date :: " + timeStamp + " Price :: " + totalPrice);


                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    itemList.clear();
                    getActivity().finish();
                }
            });

            itemList = AppDataManager.getInstance().getCartItems();
            CartAdapter cartAdapter = new CartAdapter(getActivity(), itemList);
            cartListView.setAdapter(cartAdapter);

            Cart cart = new Cart();
            float tPrice = 0;
            int total_item = 0;
            for (int i = 0; i < itemList.size(); i++) {
                total_item++;
                cart = itemList.get(i);
                tPrice = tPrice + (float) cart.getPrice();
            }
            String total_price = String.valueOf(tPrice);
            String total_cart_item = String.valueOf(total_item);

            txt_subtotal.append("(" + total_cart_item + " items)");
            subtotal.setText("₹ " + total_price);

//        total.setText("Total :: " + "\t" + "₹ " + total_price);
            Log.i("Test ", "Cart Price ₹" + total_price);
        }
        return contentView;
    }

}
