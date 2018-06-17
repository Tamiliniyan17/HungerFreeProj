package com.iniyan.dao;

import java.util.List;

import com.iniyan.domain.HungerPlaces;

public interface HungerPlacesDAO {
	
	public int registerHungerPlace(HungerPlaces hp);
	public List<String> getAvailLocations();
	public List<HungerPlaces> getHungerPlaceByLoc(String loc);

}
