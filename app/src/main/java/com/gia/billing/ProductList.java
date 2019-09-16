package com.gia.billing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.gia.billing.adapter.ProductAdapter;
import com.gia.billing.helper.AppDataManager;
import com.gia.billing.model.Products;

import java.util.ArrayList;
import java.util.Collection;

public class ProductList extends AppCompatActivity {

    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productList = findViewById(R.id.product_list_item);

        final Collection<Products> product = AppDataManager.getInstance().getItemList().values();
        final ArrayList<Products> itemList = new ArrayList<Products>(product);
        Log.d("List", "Item List" + itemList);

        /*final ProductAdapter adapter = new ProductAdapter(ProductList.this, itemList);
        productList.setAdapter(adapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Products products = adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("Code", products.getCode());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });*/
    }
}