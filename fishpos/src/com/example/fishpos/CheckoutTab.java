package com.example.fishpos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
 
public class CheckoutTab extends Fragment implements OnClickListener {
	Button addButton;
	TextView bNameField, output;
	Intent addCustIntent;
	Spinner custSpinner, fishTypeSpinner;
	ArrayList<Customer> custList;
	ArrayList<SpinnerFishType> fishTypeList;
	FishTypeAdapter fishTypeAdapter;
	CustomerAdapter custAdapter;
	String fishTypeOutput = "";
	String custOutput = "";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.checkouttab, container, false);
        
        addButton = (Button) rootView.findViewById(R.id.addCustBtn);
        custSpinner = (Spinner) rootView.findViewById(R.id.custSpinner);
        fishTypeSpinner = (Spinner) rootView.findViewById(R.id.fishTypeSpinner);
        output = (TextView) rootView.findViewById(R.id.output);
        
        loadCustSpinnerData();
        loadFishTypeSpinnerData();
        
        custSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
                 
                // Get selected row data to show on screen
                /*String customer    = ((TextView) v.findViewById(R.id.cust_view)).getText().toString();
                 String customer = "";
                custOutput = "" + customer;
                output.setText(custOutput + "\t" + fishTypeOutput);*/
                 
                //Toast.makeText(getActivity().getBaseContext(),OutputMsg, Toast.LENGTH_LONG).show();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
 
        });
        
        fishTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
            	
                // Get selected row data to show on screen
                //String FishType    = ((TextView) v.findViewById(R.id.fishType)).getText().toString();
                SpinnerFishType ft = (SpinnerFishType) parentView.getItemAtPosition(position);
            	//String FishType = parentView.getItemAtPosition(position).getText().toString();
                 
                fishTypeOutput = "" + ft.getFishType();
                output.setText(custOutput + "\t" + fishTypeOutput);
                 
                //Toast.makeText(getActivity().getBaseContext(),OutputMsg, Toast.LENGTH_LONG).show();
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
 
        });
        
        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                
                addCustIntent = new Intent(activity, AddCustomer.class);
                startActivityForResult(addCustIntent, 101);
                //startActivity(addCustIntent);

                /*if (activity != null) {
                    Toast.makeText(activity, "test", Toast.LENGTH_LONG).show();
                }*/
            }

        });
        
        return rootView;
    }
    
    private void loadCustSpinnerData() {
    	
        // database handler
        DatabaseHandler db = new DatabaseHandler(getActivity());
 
        // Spinner Drop down elements
        custList = db.getAllCustomers();
        
        custAdapter = new CustomerAdapter(getActivity().getBaseContext(), R.layout.spinner_customer, custList);
        
        // Set adapter to spinner
        custSpinner.setAdapter(custAdapter);
    }
    
    public void loadFishTypeSpinnerData() {
            SpinnerFishType fishType1 = new SpinnerFishType();
            SpinnerFishType fishType2 = new SpinnerFishType();
            SpinnerFishType fishType3 = new SpinnerFishType();
                 
          	/******* Firstly take data in model object ******/
            // Images taken from http://www.charterboatsbc.com/fish.html
            
            fishType1.setFishType("Sockeye");
            fishType1.setImageFile(R.drawable.sockeye);
        	
            fishType2.setFishType("Pink");
            fishType2.setImageFile(R.drawable.pink_salmon);
        	
            fishType3.setFishType("Spring");
            fishType3.setImageFile(R.drawable.spring);
            
            /******** Take Model Object in ArrayList **********/
            fishTypeList = new ArrayList<SpinnerFishType>();
            fishTypeList.add(fishType1);
            fishTypeList.add(fishType2);
            fishTypeList.add(fishType3);
            
            // Resources passed to adapter to get image
            Resources res = getResources(); 
             
            // Creating adapter for spinners
            fishTypeAdapter = new FishTypeAdapter(getActivity().getBaseContext(), R.layout.spinner_fishtypes, fishTypeList,res);
            
            // Set adapter to spinner
            fishTypeSpinner.setAdapter(fishTypeAdapter);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		loadCustSpinnerData();
		loadFishTypeSpinnerData();
	}
 
}