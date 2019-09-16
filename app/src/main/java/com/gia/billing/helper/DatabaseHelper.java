package com.gia.billing.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gia.billing.model.Bill;
import com.gia.billing.model.Invoice;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instanceHelper = null;

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Supermarket.db";
    private static final String PRODUCT_TABLE_NAME = "invoice_table";
    private static final String PRODUCT_ID = "ID";
    private static final String PRODUCT_BILL_NUMBER = "BILL_NO";
    private static final String PRODUCT_NAME = "NAME";
    private static final String PRODUCT_QUANTITY = "QUANTITY";
    private static final String PRODUCT_QUANTITY_TYPE = "QUANTITY_TYPE";
    private static final String PRODUCT_PRICE = "PRICE";

    private static final String INVOICE_TABLE_NAME = "invoice_info_table";
    private static final String INVOICE_ID = "_ID";
    //    private static final String INVOICE_NUMBER = "NUMBER";
    private static final String INVOICE_PRICE = "PRICE";
    private static final String INVOICE_DATE = "DATE";

    private static final String CREATE_INVOICE_TABLE = "CREATE TABLE IF NOT EXISTS " + INVOICE_TABLE_NAME + "(" + INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INVOICE_PRICE + " REAL, " + INVOICE_DATE + " TEXT)";
    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_BILL_NUMBER + " REAL, " + PRODUCT_NAME + " TEXT, " + PRODUCT_QUANTITY + " INTEGER, " + PRODUCT_QUANTITY_TYPE + " TEXT, " + PRODUCT_PRICE + " REAL)";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + INVOICE_TABLE_NAME);
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

    public boolean insertProducts(long bill_no, String name, int quantity, String quantityType, float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_BILL_NUMBER, bill_no);
        contentValues.put(PRODUCT_NAME, name);
        contentValues.put(PRODUCT_QUANTITY, quantity);
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
}