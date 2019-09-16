package com.gia.billing.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gia.billing.R;
import com.gia.billing.adapter.ViewPagerAdapter;
import com.gia.billing.fragment.AccountFragment;
import com.gia.billing.fragment.BillFragment;
import com.gia.billing.fragment.CartFragment;
import com.gia.billing.fragment.DashboardFragment;
import com.gia.billing.fragment.MainFragment;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.model.Products;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    public static final int itemID = 1;
    private Products selectedProduct;
    private TextInputEditText enter_customer_name, product_id;
    private Button add_cart_btn, view_cart_btn;
    private TextView txt_product_code, product_quantity, no_of_products, total_price;
    private ImageView product_quantity_decreament, product_quantity_increament;
    private ArrayList<Products> productsArrayList;
    private TabLayout tabLayout;
    private ViewPager tab_viewpager;
    private boolean doubleBackPressed;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper.getInstance(this);
        Log.d("Create Table : ", "Table created : " + DatabaseHelper.getInstance(this));


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tab_viewpager = (ViewPager) findViewById(R.id.tab_viewpager);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.pageFragment(new DashboardFragment(), "Dashboard");
        pagerAdapter.pageFragment(new MainFragment(), "Products");
        pagerAdapter.pageFragment(new BillFragment(), "Invoice");
        pagerAdapter.pageFragment(new AccountFragment(), "Account");
        tab_viewpager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(tab_viewpager);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed();
            return;
        }
        this.doubleBackPressed = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.report) {

        }*/
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.report) {
//            Intent intent = new Intent(MainActivity.this, BillNumber.class);
//            startActivity(intent);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }*/
}