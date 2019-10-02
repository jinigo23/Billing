package com.gia.billing.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gia.billing.model.Bill;
import com.gia.billing.model.Employee;
import com.gia.billing.model.Invoice;
import com.gia.billing.model.ProductCategory;
import com.gia.billing.model.ProductSubCategory;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instanceHelper = null;

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "Supermarket.db";
    private static final String PRODUCT_TABLE_NAME = "invoice_table";
    private static final String PRODUCT_ID = "ID";
    private static final String PRODUCT_BILL_NUMBER = "BILL_NO";
    private static final String PRODUCT_CATEGORY = "CATEGORY";
    private static final String PRODUCT_SUB_CATEGORY = "SUB_CATEGORY";
    private static final String PRODUCT_NAME = "NAME";
    private static final String PRODUCT_QUANTITY = "QUANTITY";
    private static final String PRODUCT_NO_OF_QUANTITY = "NO_OF_QUANTITY";
    private static final String PRODUCT_QUANTITY_TYPE = "QUANTITY_TYPE";
    private static final String PRODUCT_PRICE = "PRICE";

    private static final String INVOICE_TABLE_NAME = "invoice_info_table";
    private static final String INVOICE_ID = "_ID";
    //    private static final String INVOICE_NUMBER = "NUMBER";
    private static final String INVOICE_PRICE = "PRICE";
    private static final String INVOICE_DATE = "DATE";

    private static final String EMPLOYEE_TABLE_NAME = "employee_info_table";
    private static final String EMPLOYEE_ID = "EMPLOYEE_ID";
    private static final String EMPLOYEE_NAME = "EMPLOYEE_NAME";
    private static final String EMPLOYEE_PHONE = "EMPLOYEE_PHONE";
    private static final String EMPLOYEE_PASSWORD = "EMPLOYEE_PASSWORD";

    private static final String PRODUCT_CATEGORY_TABLE_NAME = "product_category";
    private static final String PRODUCT_CATEGORY_ID = "CATEGORY_ID";
    private static final String PRODUCT_CATEGORY_NAME = "CATEGORY_NAME";

    private static final String PRODUCT_SUB_CATEGORY_TABLE_NAME = "product_sub_category";
    private static final String PRODUCT_SUB_CATEGORY_ID = "SUB_CATEGORY_ID";
    private static final String PRODUCT_SUB_CATEGORY_CATEGORY_ID = "CATEGORY_ID";
    private static final String PRODUCT_SUB_CATEGORY_NAME = "SUB_CATEGORY_NAME";


    //    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE_TABLE_NAME + "(" + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_NAME + " TEXT, " + EMPLOYEE_PHONE + " TEXT, " + EMPLOYEE_PASSWORD + " TEXT)";
    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE_TABLE_NAME + "(" + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_NAME + " TEXT, " + EMPLOYEE_PHONE + " TEXT, " + EMPLOYEE_PASSWORD + " TEXT)";
    private static final String CREATE_INVOICE_TABLE = "CREATE TABLE IF NOT EXISTS " + INVOICE_TABLE_NAME + "(" + INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INVOICE_PRICE + " REAL, " + INVOICE_DATE + " TEXT)";
    private static final String CREATE_PRODUCT_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " + PRODUCT_CATEGORY_TABLE_NAME + "(" + PRODUCT_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_CATEGORY_NAME + " TEXT)";
    private static final String CREATE_PRODUCT_SUB_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " + PRODUCT_SUB_CATEGORY_TABLE_NAME + "(" + PRODUCT_SUB_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_SUB_CATEGORY_CATEGORY_ID + " INTEGER, " + PRODUCT_SUB_CATEGORY_NAME + " TEXT)";
    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_BILL_NUMBER + " REAL, " + PRODUCT_CATEGORY + " TEXT, " + PRODUCT_SUB_CATEGORY + " TEXT, " + PRODUCT_NAME + " TEXT, " + PRODUCT_QUANTITY + " INTEGER, " + PRODUCT_QUANTITY_TYPE + " TEXT, " + PRODUCT_NO_OF_QUANTITY + " INTEGER, " + PRODUCT_PRICE + " REAL)";

    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DB", "Table created succesfully");
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instanceHelper == null) {
            instanceHelper = new DatabaseHelper(context);
        }
        return instanceHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INVOICE_TABLE);
        Log.d("Invoice Table :: ", "Invoice table created");
        db.execSQL(CREATE_PRODUCT_TABLE);
        Log.d("Product Table :", "Product table created");
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        Log.d("Employee Table :", "Employee table created");
        db.execSQL(CREATE_PRODUCT_CATEGORY_TABLE);
        Log.d("Category Table :", "Category table created");
        db.execSQL(CREATE_PRODUCT_SUB_CATEGORY_TABLE);
        Log.d("Sub category Table :", "Sub category table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INVOICE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_CATEGORY_TABLE_NAME);
        onCreate(db);
    }

    public long insertInvoice(float total_price, String invoice_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put (INVOICE_NUMBER, number);
        contentValues.put(INVOICE_PRICE, total_price);
        contentValues.put(INVOICE_DATE, invoice_date);
        long id = db.insert(INVOICE_TABLE_NAME, null, contentValues);
        return id;
    }

    public boolean insertProducts(long bill_no, String category, String sub_category, String name, int quantity, int noofquantity, String quantityType, float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_BILL_NUMBER, bill_no);
        contentValues.put(PRODUCT_CATEGORY, category);
        contentValues.put(PRODUCT_SUB_CATEGORY, sub_category);
        contentValues.put(PRODUCT_NAME, name);
        contentValues.put(PRODUCT_QUANTITY, quantity);
        contentValues.put(PRODUCT_NO_OF_QUANTITY, noofquantity);
        contentValues.put(PRODUCT_QUANTITY_TYPE, quantityType);
        contentValues.put(PRODUCT_PRICE, price);
        long id = db.insert(PRODUCT_TABLE_NAME, null, contentValues);
        return true;
    }

    public void updateProducts() {

    }

    public ArrayList<Bill> getBillList() {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + INVOICE_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Bill bill = new Bill();
            bill.setInvoice_id(cursor.getInt(cursor.getColumnIndex(INVOICE_ID)));
//            bill.setInvoice_number (cursor.getInt (cursor.getColumnIndex (INVOICE_NUMBER)));
            bill.setTotal_price(cursor.getFloat(cursor.getColumnIndex(INVOICE_PRICE)));
            bill.setInvoice_date(cursor.getString(cursor.getColumnIndex(INVOICE_DATE)));
            billList.add(bill);
//            Log.d ("Bill list ", "Bill list ::"+cursor.getInt (0));
        }
        return billList;
    }

//    public Cursor getInvoice(String number) {
//        SQLiteDatabase db = this.getReadableDatabase ();
//        Cursor cursor = db.rawQuery ("SELECT * FROM " + INVOICE_TABLE_NAME + " WHERE " + INVOICE_ID + " = ' " + INVOICE_ID , new String[]{number}, null);
//        return cursor;
//    }

    public ArrayList<Invoice> getAllReportList(int bill_id) {
        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_BILL_NUMBER + " = ? ";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(bill_id)});
        Log.d("Select query :: ", "Query " + query);
//        Cursor cursor = db.query (" SELECT * FROM " + PRODUCT_TABLE_NAME, new String[]{PRODUCT_NAME, PRODUCT_QUANTITY, PRODUCT_QUANTITY_TYPE, PRODUCT_PRICE}, INVOICE_ID + " =? ", new String[]{String.valueOf (bill_id)}, null, null, null, null);
        while (cursor.moveToNext()) {
            Invoice invoice = new Invoice();
            invoice.setItemNo(cursor.getInt(cursor.getColumnIndex(PRODUCT_ID)));
            invoice.setProduct_invoice_no(cursor.getInt(cursor.getColumnIndex(PRODUCT_BILL_NUMBER)));
            invoice.setName(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
            invoice.setQuantity(cursor.getInt(cursor.getColumnIndex(PRODUCT_QUANTITY)));
            invoice.setQuantityType(cursor.getString(cursor.getColumnIndex(PRODUCT_QUANTITY_TYPE)));
            invoice.setPrice(cursor.getFloat(cursor.getColumnIndex(PRODUCT_PRICE)));
            invoiceList.add(invoice);
//                Log.d ("DB arraylist items :: ", cursor.getString (0));
//                Log.d ("DB arraylist items :: ", cursor.getInt (0)+ "\t" + cursor.getInt (1) + "\t" + cursor.getString (2) + "\t" + "Quantity :: " + cursor.getInt (3) + cursor.getString (4) + "\t" + "Price :: ₹" + cursor.getFloat (5));
        }
        return invoiceList;
    }

    public boolean insert_Employee(String emp_name, String emp_phone, String emp_password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, emp_name);
        contentValues.put(EMPLOYEE_PHONE, emp_phone);
        contentValues.put(EMPLOYEE_PASSWORD, emp_password);
        long emp_id = db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Employee> get_emp_list() {
        ArrayList<Employee> empl_List = new ArrayList<Employee>();
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "Select * From " + EMPLOYEE_TABLE_NAME;
//        Cursor cursor = db.rawQuery("SELECT * FROM " + EMPLOYEE_TABLE_NAME, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + EMPLOYEE_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setEmp_name(cursor.getString(cursor.getColumnIndex(EMPLOYEE_NAME)));
            employee.setEmp_phone(cursor.getString(cursor.getColumnIndex(EMPLOYEE_PHONE)));
            employee.setEmp_password(cursor.getString(cursor.getColumnIndex(EMPLOYEE_PASSWORD)));
            empl_List.add(employee);
        }
        return empl_List;
    }

    public long insert_Category(String category_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_CATEGORY_NAME, category_name);
        long category_id = db.insert(PRODUCT_CATEGORY_TABLE_NAME, null, values);
        return category_id;
    }

    public ArrayList<ProductCategory> get_category_list() {
        ArrayList<ProductCategory> categoryArrayList = new ArrayList<ProductCategory>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PRODUCT_CATEGORY_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ProductCategory category = new ProductCategory();
            category.setCategory_name(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY_NAME)));
            categoryArrayList.add(category);
        }
        return categoryArrayList;
    }

    public long insert_subcategory(int category_id, String subcategory_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_SUB_CATEGORY_CATEGORY_ID, category_id);
        values.put(PRODUCT_SUB_CATEGORY_NAME, subcategory_name);
        long susbcategory_id = db.insert(PRODUCT_SUB_CATEGORY_TABLE_NAME, null, values);
        return susbcategory_id;
    }

    public ArrayList<ProductSubCategory> get_subCategories_List(long category_id) {
        ArrayList<ProductSubCategory> subCategoriesList = new ArrayList<ProductSubCategory>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PRODUCT_SUB_CATEGORY_TABLE_NAME + " WHERE " + PRODUCT_SUB_CATEGORY_CATEGORY_ID + " =? ";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(category_id)});
        while (cursor.moveToNext()) {
            ProductSubCategory subCategory = new ProductSubCategory();
            subCategory.setCategory_id(cursor.getInt(cursor.getColumnIndex(PRODUCT_SUB_CATEGORY_CATEGORY_ID)));
            subCategory.setSubcategory_name(cursor.getString(cursor.getColumnIndex(PRODUCT_SUB_CATEGORY_NAME)));
            subCategoriesList.add(subCategory);
        }
        return subCategoriesList;
    }
}