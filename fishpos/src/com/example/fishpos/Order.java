package com.example.fishpos;

public class Order {
	int receiptNo;
	String name;
	String fishType;
	long pricePerPound;
	double totalWeight;
	long amountPaid;
	
	public Order() {
		
	}
	
	public Order(String name, String fishType, long pricePerPound, double totalWeight, long amountPaid) {
		this.receiptNo = 0;
		this.name = name;
		this.fishType = fishType;
		this.pricePerPound = pricePerPound;
		this.totalWeight = totalWeight;
		this.amountPaid = amountPaid;
	}
	
	public Order(int receiptNo, String name, String fishType, long pricePerPound, double totalWeight, long amountPaid) {
		this.receiptNo = receiptNo;
		this.name = name;
		this.fishType = fishType;
		this.pricePerPound = pricePerPound;
		this.totalWeight = totalWeight;
		this.amountPaid = amountPaid;
	}
	
	public long getAmountPaid() {
		return this.amountPaid;
	}
	
	public void setAmountPaid(long amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public int getReceiptNo() {
		return this.receiptNo;
	}
	
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFishType() {
		return this.fishType;
	}
	
	public void setFishType(String fishType) {
		this.fishType = fishType;
	}
	
	public double getPricePerPound() {
		return this.pricePerPound;
	}
	
	public void setPricePerPound(long pricePerPound) {
		this.pricePerPound = pricePerPound;
	}
	
	public double getTotalWeight() {
		return this.totalWeight;
	}
	
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
}