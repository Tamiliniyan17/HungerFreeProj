package com.iniyan.dao;

import java.util.List;

import com.iniyan.domain.Volunteer;

public interface AdminDAO {
	
	public List<Integer> adminValidation(String email,String pwd);
	public List<Volunteer> getAllVolunteerDetails();

}
