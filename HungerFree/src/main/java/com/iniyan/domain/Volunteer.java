package com.iniyan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="HUNGER_VOLUNTEERS")
public class Volunteer implements Serializable{
	private int vid;
	private String name;
	private String location;
	private String email;
	private long mobile;
	private String password;
	private Date joinedDate;
	
	@Id
	@Column(length=10)
	@Type(type="int")
	@GenericGenerator(name="gen1",strategy="increment")
	@GeneratedValue(generator="gen1")
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	
	@Column(length=50)
	@Type(type="string")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=50)
	@Type(type="string")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(length=50)
	@Type(type="string")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=11)
	@Type(type="long")
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	@Column(length=20)
	@Type(type="string")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column()
	@Type(type="date")
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	
}
