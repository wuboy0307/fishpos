package com.example.fishpos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "fishpos";
 
    // Customers table name
    private static final String TABLE_CUSTOMERS = "Customer";
 
    // Customer Table Columns names
    private static final String KEY_CUSTNO = "custNo";
    private static final String KEY_CUST_NAME = "customer_name";
    private static final String KEY_BOAT_NAME = "boat_name";
    private static final String KEY_BOAT_NO = "boat_number";
    
    // Orders table
    private static final String TABLE_ORDERS = "Sales";
    
    // Order Table Columns names
    private static final String KEY_RECEIPT_NO = "receiptNo";
    private static final String KEY_NAME = "bname";
    private static final String KEY_FISH_TYPE = "fishType";
    private static final String KEY_PRICE_CENTS = "price";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_AMOUNT_PAID = "amountPaid";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + KEY_CUSTNO + " INTEGER PRIMARY KEY," + KEY_CUST_NAME + " TEXT," + KEY_BOAT_NAME + " TEXT,"
                + KEY_BOAT_NO + " TEXT" + ")";
        
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "(" 
        		+ KEY_RECEIPT_NO + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_FISH_TYPE + " TEXT,"
        		+ KEY_PRICE_CENTS + " INTEGER," + KEY_WEIGHT + " REAL," + KEY_AMOUNT_PAID + " INTEGER" +  ")";
        db.execSQL(CREATE_CUSTOMERS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
 
        // Create tables again
        onCreate(db);
    }
    
    // Adding new contact
    public void addCustomer(Customer cust) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_CUST_NAME, cust.getCustName()); // Customer Name
        values.put(KEY_BOAT_NAME, cust.getBoatName()); // Boat Name
        values.put(KEY_BOAT_NO, cust.getBoatNo()); // Boat Number 
     
        // Inserting Row
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close(); // Closing database connection
    }
    
    // Adding new order
    public void addOrder(Order newOrder) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newOrder.getName()); // Customer Info
        values.put(KEY_FISH_TYPE, newOrder.getFishType()); // Fish Type
        values.put(KEY_PRICE_CENTS, newOrder.getPricePerPound()); // Price in cents
        values.put(KEY_WEIGHT, newOrder.getTotalWeight()); // Total weight
        values.put(KEY_AMOUNT_PAID, newOrder.getAmountPaid()); // Amount paid in cents
        
     
        // Inserting Row
        db.insert(TABLE_ORDERS, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single contact
    public Customer getCustomer(int custNo) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[] { KEY_CUSTNO,
                KEY_BOAT_NAME, KEY_BOAT_NO }, KEY_CUSTNO + "=?",
                new String[] { String.valueOf(custNo) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Customer newCust = new Customer(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return newCust;
    }
     
    // Getting All Customers
    public ArrayList<Customer> getAllCustomers() {
    	ArrayList<Customer> custList = new ArrayList<Customer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustNo(Integer.parseInt(cursor.getString(0)));
                customer.setCustName(cursor.getString(1));
                customer.setBoatName(cursor.getString(2));
                customer.setBoatNo(cursor.getString(3));
                // Adding contact to list
                custList.add(customer);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        return custList;
    }
    
    // Getting All Orders
    public ArrayList<Order> getAllOrders() {
    	ArrayList<Order> ordList = new ArrayList<Order>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order ord = new Order();
                ord.setReceiptNo(Integer.parseInt(cursor.getString(0)));
                ord.setName(cursor.getString(1));
                ord.setFishType(cursor.getString(2));
                ord.setPricePerPound(Integer.parseInt(cursor.getString(3)));
                ord.setTotalWeight(Double.parseDouble(cursor.getString(4)));
                ord.setAmountPaid(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                ordList.add(ord);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        return ordList;
    }
     
    // Getting contacts Count
    public int getCustomerCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    
    // Updating single contact
    public int updateContact(Customer cust) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_CUST_NAME, cust.getCustName());
        values.put(KEY_BOAT_NAME, cust.getBoatName());
        values.put(KEY_BOAT_NO, cust.getBoatNo());
     
        // updating row
        return db.update(TABLE_CUSTOMERS, values, KEY_BOAT_NO + " = ?",
                new String[] { String.valueOf(cust.getBoatNo()) });
    }
     
    // Deleting single contact
    public void deleteContact(String boatName) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, KEY_BOAT_NAME + " = ?",
                new String[] { boatName });
        db.close();
    }

}
