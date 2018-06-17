package com.iniyan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iniyan.dao.AdminDAO;
import com.iniyan.domain.Volunteer;
import com.iniyan.dto.VolunteerDTO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	
	@Override
	public int validateAdmin(String email, String pass) {
		List<Integer> res=null;
		res=dao.adminValidation(email, pass);
			return res.get(0);
	}
	
	@Override
	public List<VolunteerDTO> fetchAllVolunteerDetails() {
		List<Volunteer> listVolunteers=null;
		List<VolunteerDTO> listDTO=new ArrayList<>();
		//get volunteers details from dao
		listVolunteers=dao.getAllVolunteerDetails();
		//convert domain object to dto object
		listVolunteers.forEach(vol->{
			VolunteerDTO dto=new VolunteerDTO();
			dto.setName(vol.getName());
			dto.setLocation(vol.getLocation());
			dto.setEmail(vol.getEmail());
			dto.setMobile(vol.getMobile());
			listDTO.add(dto);
		});
		
		return listDTO;
	}

}
