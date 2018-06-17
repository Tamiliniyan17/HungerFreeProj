package com.iniyan.service;

import java.util.List;

import com.iniyan.dto.DonorDTO;
import com.iniyan.dto.VolunteerDTO;

public interface VolunteerService {
	
	public int validateVolunteer(String email,String pwd);
	public String regVolunteer(VolunteerDTO dto);
	public List<DonorDTO> getRecentDonors();
}
