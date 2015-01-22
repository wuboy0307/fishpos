package com.example.fishpos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends Activity implements OnClickListener{
	Button addNewCust, clearFields;
	EditText boatName, boatNumber, cptName, crewName1, crewName2, crewName3, cptSIN, cSIN1, cSIN2, cSIN3;
	Customer newCustomer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_customer);
		
		addNewCust = (Button) findViewById(R.id.addNewCustBtn);
		clearFields = (Button) findViewById(R.id.clearFieldsAddCustomer);
		boatName = (EditText) findViewById(R.id.addBoatNameTxt);
		boatNumber = (EditText) findViewById(R.id.addBoatNoTxt);
		cptName = (EditText) findViewById(R.id.addCptNameTxt);
		
		crewName1 = (EditText) findViewById(R.id.addCrewName1);
		crewName2 = (EditText) findViewById(R.id.addCrewName2);
		crewName3 = (EditText) findViewById(R.id.addCrewName3);
		cptSIN = (EditText) findViewById(R.id.cptSIN);
		cSIN1 = (EditText) findViewById(R.id.SIN1);
		cSIN2 = (EditText) findViewById(R.id.SIN2);
		cSIN3 = (EditText) findViewById(R.id.SIN3);
		
		addNewCust.setOnClickListener(new OnClickListener() {
			 
            @Override
            public void onClick(View v) {
                String newBoatName = boatName.getText().toString();
                String newBoatNo = boatNumber.getText().toString();
                String newCustName = custName.getText().toString();
                
                //Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                
 
                if (newBoatName.trim().length() > 0 && newCustName.trim().length() > 0 && newBoatNo.trim().length() > 0) {
                	newCustomer = new Customer(newCustName, newBoatName, newBoatNo);
                	
                    // database handler
                    DatabaseHandler db = new DatabaseHandler(
                            getApplicationContext());
 
                    // inserting new label into database
                    db.addCustomer(newCustomer);
 
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Entry!",
                            Toast.LENGTH_SHORT).show();
                }
 
            }
        });
		
		clearFields.setOnClickListener(new OnClickListener() {
			 
            @Override
            public void onClick(View v) {
            	
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_customer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
