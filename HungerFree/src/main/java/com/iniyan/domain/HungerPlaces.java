package com.iniyan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="HUNGER_PLACES")
public class HungerPlaces implements Serializable {
		private int hid;
		private String type;
		private String location;
		private int peopleCount;
		private long mobile;
		private String email;
		private Date regDate;
		
		@Id
		@Column(length=10)
		@Type(type="int")
		@GenericGenerator(name="gen1",strategy="sequence",parameters=@Parameter(name="sequence_name",value="hunger_seq"))
//		@SequenceGenerator(name="gen1",sequenceName="hunger_seq",initialValue=1,allocationSize=1)
		@GeneratedValue(generator="gen1")
		public int getHid() {
			return hid;
		}
		public void setHid(int hid) {
			this.hid = hid;
		}
		
		@Column(length=10)
		@Type(type="string")
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		@Column(length=50)
		@Type(type="string")
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
		@Column(length=10)
		@Type(type="int")
		public int getPeopleCount() {
			return peopleCount;
		}
		public void setPeopleCount(int peopleCount) {
			this.peopleCount = peopleCount;
		}
		
		@Column(length=11)
		@Type(type="long")
		public long getMobile() {
			return mobile;
		}
		public void setMobile(long mobile) {
			this.mobile = mobile;
		}
		
		@Column(length=50)
		@Type(type="string")
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		@Column()
		@Type(type="date")
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}
	
}
