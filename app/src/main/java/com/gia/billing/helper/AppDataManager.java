package com.gia.billing.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.gia.billing.R;
import com.gia.billing.model.Cart;
import com.gia.billing.model.Invoice;
import com.gia.billing.model.Products;
import com.gia.billing.root.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AppDataManager {
    private static final AppDataManager ourInstance = new AppDataManager();
    private Context context;

    public static AppDataManager getInstance() {
        return ourInstance;
    }

    private Map<String, Products> itemList = new HashMap<>();
    private ArrayList<Products> productItems = new ArrayList<>();
    private ArrayList<Cart> cartItems = new ArrayList<>();
    private ArrayList<Invoice> billItem = new ArrayList<>();

    DatabaseHelper helper;


    private AppDataManager() {
        add("SGEO", new Products("Saffola Gold Edible Oil", R.drawable.saffola_oil, "ml", 1000, 139));
        add("LPR", new Products("Loose Ponni Boiled Rice", R.drawable.ponni_rice, "g", 1000, 60));
        add("FMO", new Products("Fortune Kachi Ghani Mustard Oil", R.drawable.fortune_mustard_oil, "ml", 1000, 125));
        add("NCR", new Products("Namdharis Chiroti Rava", R.drawable.chiroti_rava, "g", 1000, 55));
        add("ACG", new Products("Aashirvaad Svasti Cow Ghee", R.drawable.cow_ghee, "g", 200, 124));
        add("NDC", new Products("Namdhari's Dairy Curd", R.drawable.nd_plain_curd, "ml", 500, 25));
        add("DTNM", new Products("Dairy Tales Namdhari Farm Fresh Cow Milk", R.drawable.namdhari_bottle_milk, "ml", 1000, 75));
        add("DTGY", new Products("Dairy Tales Greek Yogurt Alphonso Mango", R.drawable.mango_greek_yogurt, "g", 100, 40));

        /*add("PR", new Products("Ponni Rice", "g", 1000, 40));
        add("TR", new Products("Toppi Rice", "g", 1000, 30));
        add("MDA", new Products("Maida", "g", 1000, 35));
        add("BSN", new Products("Besan", "g", 1000, 30));
        add("AT", new Products("Atta", "g", 1000, 45));
        add("RV", new Products("Rava", "g", 1000, 33));
        add("GF", new Products("Gram Flour", "g", 1000, 100));
        add("DA", new Products("Diabetic Atta", "g", 1000, 62));
        add("RF", new Products("Ragi Flour", "g", 1000, 120));
        add("RIF", new Products("Rice Flour", "g", 1000, 40));
        add("SUG", new Products("Sugar", "g", 1000, 35));
        add("LS", new Products("Loose Sonamasoori", "g", 1000, 36));
        add("JSR", new Products("Jeera Sambha Rice", "g", 1000, 85));
        add("LKR", new Products("Loose Kolam Raw", "g", 1000, 62));
        add("LPM", new Products("Loose Palakkad Matta Boiled Rice", "g", 1000, 55));
        add("SBR", new Products("Loose Value Sella Basmati Rice", "g", 1000, 59));
        add("WA", new Products("White Aval", "g", 1000, 75));
        add("BGD", new Products("Britannia Good Day Choco Chip Cookies", "pc", 1, 30));
        add("SMM", new Products("Sunfeast Moms Magig Cashew & Almond Cookies", "pc", 1, 20));
        add("DWC", new Products("Dukes Waffy Cheese Wafer", "pc", 1, 25));
        add("UCC", new Products("Unibic Choco Chips Biscuit", "pc", 1, 25));
        add("SG", new Products("Sunfeast Glocose Biscuts", "pc", 1, 75));
        add("OREO", new Products("Cadbury Oreo Orange Creme Chocolatey Sandwich", "pc", 1, 30));
        add("PHS", new Products("Parle Hide & Seek Orange Creme Biscuits", "pc", 1, 24));
        add("SSMT", new Products("Sunfeast Snacky Masala Twist", "pc", 1, 20));
        add("BND", new Products("Britannia Nutrichoice Digestive Biscuit Combi", "pc", 1, 54));
        add("UB", new Products("Unibic Butter Cookies", "pc", 1, 25));
        add("KSSS", new Products("Kissan Sweet and Spicy Sauce", "pc", 1, 103));
        add("CCO", new Products("KLF Coconad Coconut Oil", "ml", 500, 135));
        add("ARSO", new Products("Aadhaar Refined Sunflower Oil", "ml", 1000, 25));
        add("SSLA", new Products("Sundrop Super Lite Advanced Refined Sunflower Oil", "ml", 1000, 87));
        add("OMD", new Products("Organic Mandya Desi Cow Ghee", "ml", 300, 488));
        add("SAO", new Products("Saffola Activ Oil", "ml", 1000, 132));
        add("FVO", new Products("Fortune Vivo Oil Daibetes Care", "ml", 1000, 145));
        add("OMDF", new Products("Organic Mandya Desi Buffalo Ghee", "ml", 300, 300));
        add("SGO", new Products("Saffola Gold Oil", "ml", 1000, 149));
        add("OMS", new Products("Organic Mandya Sunflower Oil", "ml", 500, 153));
        add("OMSO", new Products("Organic Mandya Sesame Oil", "ml", 200, 98));
        add("FPM", new Products("Fortune Pure Mustard Oil", "ml", 1000, 135));
        add("NDTM", new Products("Namdhari's Dairy Pasterurised Toned Milk", "ml", 1000, 40));*/

    }


    private void add(String code, Products product) {
        product.setCode(code);
        itemList.put(code, product);
    }

    public Map<String, Products> getItemList() {
        return itemList;
    }

    public Products getProduct(String code) {
        return itemList.get(code);
    }

    public boolean addKart(String itemCode, int noOfQuantity) {

        Products pro = getProduct(itemCode);
        if (pro != null) {
            Cart k = new Cart();
            k.setCode(itemCode);
            k.setName(pro.getName());
            k.setProduct_Image(pro.getImage());

//            Quantity * no of items
            k.setNoOfQuantity(noOfQuantity);
            int total_Quantity = pro.getQuantity();
            k.setQuantity(total_Quantity);
            k.setQuantityType(pro.getQuantityType());
            k.setProducts(getProduct(itemCode));
            float pri = (pro.getPrice() / pro.getQuantity()) * k.getQuantity();
            k.setPrice(pri);
            cartItems.add(k);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Products> getProductItems() {
        return productItems;
    }

    public ArrayList<Cart> getCartItems() {
        return cartItems;
    }

    public ArrayList<Invoice> getBillItem() {
        return billItem;
    }

}
