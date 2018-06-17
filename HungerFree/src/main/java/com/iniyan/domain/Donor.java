package com.iniyan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="HUNGER_DONORS")
public class Donor implements Serializable{
	private int dId;
	private String name;
	private String address;
	private String foodItems;
	private int qty;
	private Date preparedDate;
	private int preparedTime;
	private long mobile;
	
	@Id
	@GenericGenerator(name="gen1",strategy="increment")
	@GeneratedValue(generator="gen1")
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
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
	@Column(name="FOODQTY")
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getPreparedDate() {
		return preparedDate;
	}
	public void setPreparedDate(Date preparedDate) {
		this.preparedDate = preparedDate;
	}
	public void setPreparedTime(int preparedTime) {
		this.preparedTime = preparedTime;
	}
	
	public int getPreparedTime() {
		return preparedTime;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
}
