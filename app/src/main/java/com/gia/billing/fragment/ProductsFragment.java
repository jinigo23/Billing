package com.gia.billing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.gia.billing.AddProductsActivity;
import com.gia.billing.R;
import com.gia.billing.adapter.ProductAdapter;
import com.gia.billing.helper.AppDataManager;
import com.gia.billing.model.Products;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {


    private View contentView;
    private ListView products_list_admin;
    private ArrayList<Products> product_list;
    private FloatingActionButton add_products_floatingActionButton;
    private ImageButton menu_search_button;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_products, container, false);
            init();
        }
        return contentView;
    }

    private void init() {
        menu_search_button = (ImageButton) contentView.findViewById(R.id.menu_search_button);
        add_products_floatingActionButton = (FloatingActionButton) contentView.findViewById(R.id.add_products_floatingActionButton);
        add_products_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddProductsActivity.class);
                startActivity(intent);
            }
        });
        menu_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText search_products_toolbar = (TextInputEditText) contentView.findViewById(R.id.search_products_toolbar);
                search_products_toolbar.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });
        products_list_admin = (ListView) contentView.findViewById(R.id.products_list_admin);
        Collection<Products> collection = AppDataManager.getInstance().getItemList().values();
        product_list = new ArrayList<Products>(collection);

//            long category_ID= DatabaseHelper.getInstance(getActivity()).insert_Category()

        ProductAdapter productAdapter = new ProductAdapter(getActivity(), product_list);
        products_list_admin.setAdapter(productAdapter);
    }

}
