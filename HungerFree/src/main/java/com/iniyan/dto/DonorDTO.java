package com.iniyan.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class DonorDTO implements Serializable {
	private String name;
	private String address;
	private String foodItems;
	private int qty;
	private int preparedTime;
	private long mobile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPreparedTime() {
		return preparedTime;
	}
	public void setPreparedTime(int preparedTime) {
		this.preparedTime = preparedTime;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	

}
