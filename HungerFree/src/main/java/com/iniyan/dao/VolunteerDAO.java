package com.iniyan.dao;

import java.util.List;

import com.iniyan.domain.Donor;
import com.iniyan.domain.Volunteer;

public interface VolunteerDAO {
	
	public List<Integer> verifyVolunteer(String email,String pwd);
	public int addVolunteer(Volunteer v);
	public List<Donor> getRecentDonors();

}
