package com.example.fishpos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Fragment;
import android.graphics.Color;
 
public class CustomerTab extends Fragment {
	TableLayout customerTable;
	ArrayList<Customer> allCustomersList;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customertab, container, false);
        
        customerTable = (TableLayout) rootView.findViewById(R.id.customerTable);
        
        buildTable();
        
        return rootView;
    }
    
    private void buildTable() {
    	
        // database handler
        DatabaseHandler db = new DatabaseHandler(getActivity());
 
        // Spinner Drop down elements
        allCustomersList = db.getAllCustomers();
        
        TableRow tr_head = new TableRow(getActivity());
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        
        TextView headCustNo = new TextView(getActivity());
        headCustNo.setId(20);
        headCustNo.setText("Number");
        headCustNo.setTextColor(Color.WHITE);
        headCustNo.setTextSize(25);
        headCustNo.setPadding(5, 5, 5, 5);
        tr_head.addView(headCustNo);// add the column to the table row here
        
        TextView headCustName = new TextView(getActivity());
        headCustName.setId(20);
        headCustName.setText("Customer Name");
        headCustName.setTextColor(Color.WHITE);
        headCustName.setTextSize(25);
        headCustName.setPadding(5, 5, 5, 5);
        tr_head.addView(headCustName);// add the column to the table row here

        TextView headBoatName = new TextView(getActivity());
        headBoatName.setId(21);// define id that must be unique
        headBoatName.setText("Boat Name"); // set the text for the header 
        headBoatName.setTextColor(Color.WHITE); // set the color
        headBoatName.setTextSize(25);
        headBoatName.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(headBoatName); // add the column to the table row here
        
        TextView headBoatNo = new TextView(getActivity());
        headBoatNo.setId(22);// define id that must be unique
        headBoatNo.setText("Boat #"); // set the text for the header 
        headBoatNo.setTextSize(25);
        headBoatNo.setTextColor(Color.WHITE); // set the color
        headBoatNo.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(headBoatNo); // add the column to the table row here
        
        customerTable.addView(tr_head);
        
        int count = 0;
        
        for(int i=0;i < allCustomersList.size(); i++)
        {
        	TableRow row = new TableRow(getActivity());
        	
        	if(count % 2 != 0) {
        		row.setBackgroundColor(Color.GRAY);
        	}
        	
            int custNo = allCustomersList.get(i).getCustNo();
            String custName = allCustomersList.get(i).getCustName();
            String boatName = allCustomersList.get(i).getBoatName();
            String boatNo = allCustomersList.get(i).getBoatNo();
            TextView tvCustNo = new TextView(getActivity());
            tvCustNo.setText("" + custNo);
            TextView tvCustName = new TextView(getActivity());
            tvCustName.setText(custName);
            TextView tvBoatName = new TextView(getActivity());
            tvBoatName.setText(boatName);
            TextView tvPhoneNo = new TextView(getActivity());
            tvPhoneNo.setText(boatNo);
            
            row.addView(tvCustNo);
            row.addView(tvCustName);
            row.addView(tvBoatName);
            row.addView(tvPhoneNo);
            
            customerTable.addView(row);
            count++;
        }
 
    }
 
}