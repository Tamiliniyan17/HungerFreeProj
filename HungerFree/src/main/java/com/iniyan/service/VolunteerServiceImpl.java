package com.iniyan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iniyan.dao.VolunteerDAO;
import com.iniyan.domain.Donor;
import com.iniyan.domain.Volunteer;
import com.iniyan.dto.DonorDTO;
import com.iniyan.dto.VolunteerDTO;

@Service("vService")
public class VolunteerServiceImpl implements VolunteerService {

	@Autowired
	private VolunteerDAO dao;
	
	@Override
	public int validateVolunteer(String email, String pwd) {
		List<Integer> res=null;
		res=dao.verifyVolunteer(email, pwd);
		
		return res.get(0);
	}
	
	@Override
	public String regVolunteer(VolunteerDTO dto) {
		Volunteer v=null;
		int idVal=0;
		
		//convert dto to domain cls object
		v=new Volunteer();
		v.setName(dto.getName());
		v.setLocation(dto.getLocation());
		v.setEmail(dto.getEmail());
		v.setMobile(dto.getMobile());
		v.setPassword(dto.getPassword());
		v.setJoinedDate(new Date());
		
		//use dao
		idVal=dao.addVolunteer(v);
		
		if(idVal==0) 
				return "Your Request Not Accepted..Try Again..";
			else
				return "Your Request Accepted with reg id :: "+idVal+" ..<br>Thank u For Your Interest..<br>Happy to have u as part of our success";
		
		}

	
	
	@Override
	public List<DonorDTO> getRecentDonors() {
		List<Donor> listDonor=null;
		List<DonorDTO> listDto=new ArrayList<>();
		listDonor=dao.getRecentDonors();
		//convert listDonor to listDTO
		listDonor.forEach(donor->{
			DonorDTO dto=new DonorDTO();
			dto.setName(donor.getName());
			dto.setAddress(donor.getAddress());
			dto.setFoodItems(donor.getFoodItems());
			dto.setMobile(donor.getMobile());
			dto.setQty(donor.getQty());
			dto.setPreparedTime(donor.getPreparedTime());
			
			listDto.add(dto);
		});
	
		return listDto;
	}
	

}
