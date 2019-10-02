package com.gia.billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.gia.billing.helper.AppDataManager;
import com.gia.billing.helper.PreferenceManager;
import com.gia.billing.model.Products;
import com.gia.billing.root.MyApplication;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddProductsActivity extends AppCompatActivity {

    private TextInputEditText product_code_admin, product_name_admin, product_category_admin, product_sub_category_admin, quantity_admin, product_price_admin;
    private Button add_product_button_admin;
    private Spinner quantity_type_admin_spinner;
    private String quantity_type_list[] = {"g", "ml", "pc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        product_code_admin = (TextInputEditText) findViewById(R.id.product_code_admin);
        product_name_admin = (TextInputEditText) findViewById(R.id.product_name_admin);
        product_category_admin = (TextInputEditText) findViewById(R.id.product_category_admin);
        product_sub_category_admin = (TextInputEditText) findViewById(R.id.product_sub_category_admin);
        quantity_admin = (TextInputEditText) findViewById(R.id.quantity_admin);
        product_price_admin = (TextInputEditText) findViewById(R.id.product_price_admin);
        quantity_type_admin_spinner = (Spinner) findViewById(R.id.quantity_type_admin_spinner);
        add_product_button_admin = (Button) findViewById(R.id.add_product_button_admin);

        product_category_admin.setClickable(true);
        product_category_admin.setFocusable(false);
        product_sub_category_admin.setClickable(true);
        product_sub_category_admin.setFocusable(false);

        init();
    }

    private void init() {
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        spinner_adapter.add("g");
        spinner_adapter.add("ml");
        spinner_adapter.add("pc");
        quantity_type_admin_spinner.setAdapter(spinner_adapter);

        product_category_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup(view, product_category_admin, R.menu.category);
            }
        });

        product_sub_category_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Products> productItems = AppDataManager.getInstance().getProductItems();
                for (int i = 0; i < productItems.size(); i++) {
//                    String category=productItems.get()
                }
            }
        });

        add_product_button_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_code = product_code_admin.getText().toString().trim();
                String product_name = product_name_admin.getText().toString().trim();
                String product_category = product_category_admin.getText().toString().trim();
                String product_quantity = quantity_admin.getText().toString().trim();
                String product_quantity_type = quantity_type_admin_spinner.getSelectedItem().toString();
                String product_price = product_price_admin.getText().toString().trim();

                if (product_code.length() != 0 && product_name.length() != 0 && product_quantity.length() != 0 && product_price.length() != 0) {
                    AppDataManager.getInstance().add(product_code, new Products(product_category, "Sub category", product_name, 0, product_quantity_type, Integer.valueOf(product_quantity), Float.valueOf(product_price)));
                    PreferenceManager.getInstance().toastData("Product added successfully");
                    finish();
                }
            }
        });
    }

    private void popup(View view, final TextInputEditText inputEditText, int menu_id) {
        PopupMenu popupMenu = new PopupMenu(MyApplication.getApplication(), view);
        popupMenu.getMenuInflater().inflate(menu_id, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                inputEditText.setText(menuItem.getTitle());
                return true;
            }
        });
        popupMenu.show();
    }
}
