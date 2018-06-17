package com.iniyan.service;

import java.util.List;

import com.iniyan.dto.DonorDTO;
import com.iniyan.dto.HungerPlacesDTO;

public interface HungerPlacesService {
	
	public String regHungerPlace(HungerPlacesDTO dto);
	public List<String> fetchAvailLocations();
	public List<HungerPlacesDTO> fetchHungerPlaceByLoc(String loc);


}
