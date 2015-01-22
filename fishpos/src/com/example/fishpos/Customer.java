package com.example.fishpos;

public class Customer {
	int custNo;
	String boatName;
	String custName;
	String boatNo;
	
	public Customer() {
		
	}
	
	public Customer(String custName, String boatName, String boatNo) {
		this.custNo = 0;
		this.custName = custName;
		this.boatName = boatName;
		this.boatNo = boatNo;
	}
	
	public Customer(int custNo, String custName, String boatName, String boatNo) {
		this.custNo = custNo;
		this.custName = custName;
		this.boatName = boatName;
		this.boatNo = boatNo;
	}
	
	public String getBoatNo() {
		return this.boatNo;
	}
	
	public void setBoatNo(String boatNo) {
		this.boatNo = boatNo;
	}
	
	public String getBoatName() {
		return this.boatName;
	}
	
	public void setBoatName(String name) {
		this.boatName = name;
	}
	
	public String getCustName() {
		return this.custName;
	}
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public int getCustNo() {
		return this.custNo;
	}
	
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	
	@Override
    public String toString () {
        return this.custNo + " : " + this.custName + " - " + this.boatName + "(" + this.boatNo + ")";
    }
}
