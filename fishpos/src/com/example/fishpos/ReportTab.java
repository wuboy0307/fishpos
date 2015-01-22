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
 
public class ReportTab extends Fragment {
	TableLayout orderTable;
	ArrayList<Order> allOrdersList;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reporttab, container, false);
        
        orderTable = (TableLayout) rootView.findViewById(R.id.orderTable);
        
        buildTable();
        
        return rootView;
    }
    
    private void buildTable() {
    	
        // database handler
        DatabaseHandler db = new DatabaseHandler(getActivity());
 
        // Spinner Drop down elements
        allOrdersList = db.getAllOrders();
        
        TableRow tr_head2 = new TableRow(getActivity());
        tr_head2.setId(10);
        tr_head2.setBackgroundColor(Color.GRAY);
        
        TextView headReceiptNo = new TextView(getActivity());
        headReceiptNo.setId(30);
        headReceiptNo.setText("Receipt Number");
        headReceiptNo.setTextColor(Color.WHITE);
        headReceiptNo.setTextSize(25);
        headReceiptNo.setPadding(5, 5, 5, 5);
        tr_head2.addView(headReceiptNo);// add the column to the table row here
        
        TextView headName = new TextView(getActivity());
        headName.setId(31);
        headName.setText("Name");
        headName.setTextColor(Color.WHITE);
        headName.setTextSize(25);
        headName.setPadding(5, 5, 5, 5);
        tr_head2.addView(headName);// add the column to the table row here

        TextView headFishType = new TextView(getActivity());
        headFishType.setId(32);// define id that must be unique
        headFishType.setText("Fish Type"); // set the text for the header 
        headFishType.setTextColor(Color.WHITE); // set the color
        headFishType.setTextSize(25);
        headFishType.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head2.addView(headFishType); // add the column to the table row here
        
        TextView headPricePerPound = new TextView(getActivity());
        headPricePerPound.setId(33);// define id that must be unique
        headPricePerPound.setText("Price/lb"); // set the text for the header 
        headPricePerPound.setTextColor(Color.WHITE); // set the color
        headPricePerPound.setTextSize(25);
        headPricePerPound.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head2.addView(headPricePerPound); // add the column to the table row here
        
        TextView headTotalWeight = new TextView(getActivity());
        headTotalWeight.setId(34);// define id that must be unique
        headTotalWeight.setText("Weight (lbs)"); // set the text for the header 
        headTotalWeight.setTextSize(25);
        headTotalWeight.setTextColor(Color.WHITE); // set the color
        headTotalWeight.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head2.addView(headTotalWeight); // add the column to the table row here
        
        TextView headAmountPaid = new TextView(getActivity());
        headAmountPaid.setId(35);// define id that must be unique
        headAmountPaid.setText("Amount Paid"); // set the text for the header 
        headAmountPaid.setTextSize(25);
        headAmountPaid.setTextColor(Color.WHITE); // set the color
        headAmountPaid.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head2.addView(headAmountPaid); // add the column to the table row here
        
        orderTable.addView(tr_head2);
        
        int count = 0;
        
        for(int i=0;i < allOrdersList.size(); i++)
        {
        	TableRow row = new TableRow(getActivity());
        	
        	if(count % 2 != 0) {
        		row.setBackgroundColor(Color.GRAY);
        	}
        	
            int receiptNo = allOrdersList.get(i).getReceiptNo();
            String name = allOrdersList.get(i).getName();
            String fishType = allOrdersList.get(i).getFishType();
            double pricePerPound = allOrdersList.get(i).getPricePerPound() / 100;
            double weight = allOrdersList.get(i).getTotalWeight();
            double amountPaid = allOrdersList.get(i).getAmountPaid() / 100;
            TextView tvReceiptNo = new TextView(getActivity());
            tvReceiptNo.setText("" + receiptNo);
            TextView tvName = new TextView(getActivity());
            tvName.setText(name);
            TextView tvFishType = new TextView(getActivity());
            tvFishType.setText(fishType);
            TextView tvPricePerPound = new TextView(getActivity());
            tvPricePerPound.setText("$" + pricePerPound);
            TextView tvWeight = new TextView(getActivity());
            tvWeight.setText(String.valueOf(weight));
            TextView tvAmountPaid = new TextView(getActivity());
            tvAmountPaid.setText("$" + amountPaid);
            
            row.addView(tvReceiptNo);
            row.addView(tvName);
            row.addView(tvFishType);
            row.addView(tvPricePerPound);
            row.addView(tvWeight);
            row.addView(tvAmountPaid);
            
            orderTable.addView(row);
            count++;
        }
 
    }
 
}