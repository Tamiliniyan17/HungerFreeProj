package com.iniyan.service;

import java.util.List;

import com.iniyan.dto.VolunteerDTO;

public interface AdminService {
	
	public int validateAdmin(String email,String pass);
	public List<VolunteerDTO> fetchAllVolunteerDetails();

}
