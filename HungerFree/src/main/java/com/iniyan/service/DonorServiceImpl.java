package com.iniyan.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iniyan.dao.DonorDAO;
import com.iniyan.domain.Donor;
import com.iniyan.dto.DonorDTO;

@Service("donorService")
public class DonorServiceImpl implements DonorService {
	
	@Autowired
	private DonorDAO dao;


	@Override
	public String saveDonor(DonorDTO dto) {
		Donor donor=null;
		int result=0;
		
		//create domain class object
		donor=new Donor();
		//convert dto to domain clas object
		donor.setName(dto.getName());
		donor.setAddress(dto.getAddress());
		donor.setFoodItems(dto.getFoodItems());
		donor.setQty(dto.getQty());
		donor.setPreparedDate(new Date());
		donor.setPreparedTime(dto.getPreparedTime());
		donor.setMobile(dto.getMobile());
		
		//invoke method of dao
		result=dao.registerDonor(donor);
		
		//process result
		if(result==0)
			return "Your Request Not Accepted..Try Again..";
		else
			return "Your Request Accepted with reg id :: "+result+" <br> ..Thank u For Your Interest..<br>Our Volunteers Will get Back u soon";
		
	}

}
