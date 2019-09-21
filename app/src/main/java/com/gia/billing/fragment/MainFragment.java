package com.gia.billing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.gia.billing.R;
import com.gia.billing.adapter.CartAdapter;
import com.gia.billing.adapter.ProductAdapter;
import com.gia.billing.helper.AppDataManager;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.helper.PreferenceManager;
import com.gia.billing.model.Bill;
import com.gia.billing.model.Cart;
import com.gia.billing.model.Products;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private static final int REQUEST_CODE = 10;
    private TextInputEditText enter_customer_name, product_id;
    private ImageView product_quantity_decreament, product_quantity_increament;
    private TextView txt_product_code, product_quantity, no_of_products, total_price, mrp_price, product_price;
    private Button add_cart_btn, view_cart_btn;
    private ArrayList<Products> productsArrayList;
    private ArrayList<Cart> cartArrayList;
    private TabLayout tabLayout;
    private View contentView;
    private int no_of_items_added = 0;
    private Toolbar main_toolbar;
    Products products;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_main, container, false);

            DatabaseHelper.getInstance(getActivity());

            cartArrayList = AppDataManager.getInstance().getCartItems();

            main_toolbar = (Toolbar) contentView.findViewById(R.id.main_toolbar);
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(main_toolbar);
            activity.setTitle("");

            product_id = (TextInputEditText) contentView.findViewById(R.id.select_product_id);
            product_quantity_decreament = (ImageView) contentView.findViewById(R.id.product_quantity_decreament);
            product_quantity_increament = (ImageView) contentView.findViewById(R.id.product_quantity_increament);
            txt_product_code = (TextView) contentView.findViewById(R.id.txt_product_code);
            product_quantity = (TextView) contentView.findViewById(R.id.product_quantity);
            mrp_price = (TextView) contentView.findViewById(R.id.mrp_price);
            product_price = (TextView) contentView.findViewById(R.id.product_price);
            no_of_products = (TextView) contentView.findViewById(R.id.no_of_products);
            total_price = (TextView) contentView.findViewById(R.id.total_price);
            add_cart_btn = (Button) contentView.findViewById(R.id.add_cart_btn);
            view_cart_btn = (Button) contentView.findViewById(R.id.view_cart_btn);

            add_cart_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id_product = product_id.getText().toString().trim();

                    if (id_product.length() == 0) {
                        product_id.setError("Select the product");
                    } else {
                        no_of_items_added=no_of_items_added+1;
                        Intent intent = new Intent();
                        String product_code = txt_product_code.getText().toString();
                        intent.putExtra("Code", product_code);
                        String item_quantity = product_quantity.getText().toString();
                        int entered_quantity = Integer.valueOf(item_quantity);
                        if (AppDataManager.getInstance().addKart(product_code, entered_quantity)) {
                            Snackbar.make(view, "Product added", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else {
                            Snackbar.make(view, "Product not added", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                        product_id.setText("");
                        product_quantity.setText("0");
                        product_price.setText("");
                        mrp_price.setText("");

                        float tPrice = 0;
                        for (int i = 0; i < cartArrayList.size(); i++) {
                            Cart cart = cartArrayList.get(i);
                            tPrice = tPrice + (float) cart.getPrice();
                        }
                        no_of_products.setText(String.valueOf(no_of_items_added));
                        total_price.setText(String.valueOf(tPrice));
                    }
                }
            });

            product_quantity_increament.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (products==null){
                            return;
                        }
                        String item_quantity = product_quantity.getText().toString();
                        int plus = Integer.valueOf(item_quantity);

                        if (plus <= 4) {
                            plus = plus + 1;

                            String mrp_item_price = mrp_price.getText().toString();
//                            String mrp_item = mrp_item_price.replaceAll("[^0-9]", "");
                            float item_price = products.getPrice() * plus;

                            product_quantity.setText(String.valueOf(plus));
                            product_price.setText("₹ " + String.valueOf(item_price));
                        }
                     /*else{
                        Snackbar.make(view, "Limit reached", Snackbar.LENGTH_SHORT).show();
                    }*/
                    }
                });

            product_quantity_decreament.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (products==null){
                        return;
                    }
                    String item_quantity = product_quantity.getText().toString();
                    int minus = Integer.valueOf(item_quantity);

                    if (minus >= 2) {
                        minus--;
                        float item_price = products.getPrice() * minus;
                        product_quantity.setText(String.valueOf(minus));
                        product_price.setText("₹ " + String.valueOf(item_price));
                    }
                }
            });

            view_cart_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                    View cartView = layoutInflater.inflate(R.layout.fragment_cart, null);

                    ListView cart_listview = cartView.findViewById(R.id.cartListView);
                    TextView txt_subtotal = cartView.findViewById(R.id.txt_subtotal);
                    TextView subtotal = cartView.findViewById(R.id.subtotal);
                    Button print_cart = cartView.findViewById(R.id.print_cart);
                    Button cancel_cart = cartView.findViewById(R.id.cancel_cart);

                    String id_product = product_id.getText().toString().trim();
                    if (cartArrayList.size() != 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setView(cartView).setTitle("Cart");

                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        CartAdapter adapter = new CartAdapter(getActivity(), cartArrayList);
                        cart_listview.setAdapter(adapter);

                        int total_items = cartArrayList.size();
                        float tPrice = 0;
                        for (int i = 0; i < cartArrayList.size(); i++) {
                            Cart cart = cartArrayList.get(i);
                            tPrice = tPrice + (float) cart.getPrice();
                        }
                        txt_subtotal.setText("No. of items : " + String.valueOf(total_items));
                        subtotal.setText("Total : ₹" + String.valueOf(tPrice));

                        print_cart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                printCart();
                                no_of_products.setText("");
                                total_price.setText("");
                                alertDialog.dismiss();
                            }
                        });

                        cancel_cart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });

                    } else {
                        PreferenceManager.getInstance().toastData("Cart is empty");
                    }
                }
            });

            product_id.setClickable(true);
            product_id.setFocusable(false);
            product_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    View product_view = layoutInflater.inflate(R.layout.fragment_product, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(product_view);

                    final ListView productItems_list = product_view.findViewById(R.id.productItems_list);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.button_default);

                    Collection<Products> collection = AppDataManager.getInstance().getItemList().values();
                    productsArrayList = new ArrayList<Products>(collection);
                    final ProductAdapter productAdapter = new ProductAdapter(getActivity(), productsArrayList);
                    productItems_list.setAdapter(productAdapter);
                    productItems_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            String clicked_item = productItems_list.getItemAtPosition(position).toString();
                            products = productAdapter.getItem(position);
                            product_id.setText(products.getName());
                            txt_product_code.setText(products.getCode());
                            String mrp = String.valueOf(products.getPrice());
                            product_quantity.setText("1");
                            mrp_price.setText("₹ "+mrp);
                            product_price.setText("₹ " + mrp);
                            alertDialog.dismiss();
                        }
                    });
//"₹ " +
                }
            });
        }
        return contentView;
    }

    private void printCart() {
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        int tStamp = Integer.parseInt(timeStamp);

        Bill bill = new Bill();
        Cart cart = new Cart();

        float tPrice = 0;
        for (int i = 0; i < cartArrayList.size(); i++) {
            cart = cartArrayList.get(i);
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

        for (int i = 0; i < cartArrayList.size(); i++) {
            cart = cartArrayList.get(i);
            Products products = cart.getProducts();
            int invoice_no = Integer.parseInt(timeStamp);
            String name = products.getName();
            int quantity = cart.getQuantity();
            String quantityType = products.getQuantityType();
            float price = cart.getPrice();
            int noof_quantity = Integer.valueOf(product_quantity.getText().toString());

            boolean isInserted = DatabaseHelper.getInstance(getActivity()).insertProducts(billID, name, quantity, noof_quantity, quantityType, price);
            if (isInserted == true) {
                Log.d("Inserting Data into DB", "Code : " + name + " Quantity :: " + quantity + quantityType + " Price :: " + price);
            }
        }
        Toast.makeText(getActivity(), "Item Saved", Toast.LENGTH_SHORT).show();
        Log.d("Date", "Date :: " + timeStamp + " Price :: " + totalPrice);

        cartArrayList.clear();
    }

}
