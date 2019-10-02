package com.gia.billing.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gia.billing.fragment.ProductsFragment;
import com.gia.billing.R;
import com.gia.billing.adapter.ViewPagerAdapter;
import com.gia.billing.fragment.AccountFragment;
import com.gia.billing.fragment.BillFragment;
import com.gia.billing.fragment.DashboardFragment;
import com.gia.billing.fragment.MainFragment;
import com.gia.billing.helper.DatabaseHelper;
import com.gia.billing.helper.PreferenceManager;
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
    ViewPagerAdapter pagerAdapter;


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

        boolean admin = PreferenceManager.getInstance().getBoolean("Admin");

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (admin == true) {
            pagerAdapter.pageFragment(new DashboardFragment(), "Dashboard");
            pagerAdapter.pageFragment(new ProductsFragment(), "Products");
            pagerAdapter.pageFragment(new BillFragment(), "Invoice");
            pagerAdapter.pageFragment(new AccountFragment(), "Account");
        } else {
            pagerAdapter.pageFragment(new MainFragment(), "Products");
            pagerAdapter.pageFragment(new BillFragment(), "Invoice");
            pagerAdapter.pageFragment(new AccountFragment(), "Account");
        }
        tab_viewpager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(tab_viewpager);

    }

    @Override
    public void onBackPressed() {
        int current_position = tab_viewpager.getCurrentItem();
        if (current_position == 0) {
            if (doubleBackPressed) {
                super.onBackPressed();
                return;
            }
        } else {
            tab_viewpager.setCurrentItem(0);
        }
        this.doubleBackPressed = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filter) {
//            
        }
        return super.onOptionsItemSelected(item);
    }*/
}
