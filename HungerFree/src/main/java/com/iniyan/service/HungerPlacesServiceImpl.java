package com.iniyan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iniyan.dao.HungerPlacesDAO;
import com.iniyan.domain.Donor;
import com.iniyan.domain.HungerPlaces;
import com.iniyan.dto.DonorDTO;
import com.iniyan.dto.HungerPlacesDTO;

@Service("hungerService")
public class HungerPlacesServiceImpl implements HungerPlacesService {
	@Autowired
	private HungerPlacesDAO dao;
	
	@Override
	public String regHungerPlace(HungerPlacesDTO dto) {
		System.out.println("hunger service");
		HungerPlaces hp=null;
		int idVal=0;
		
		hp=new HungerPlaces();
		hp.setType(dto.getType());
		hp.setLocation(dto.getLocation());
		hp.setPeopleCount(dto.getPeopleCount());
		hp.setEmail(dto.getEmail());
		hp.setMobile(dto.getMobile());
		hp.setRegDate(new Date());
		
		//use dao
		idVal=dao.registerHungerPlace(hp);
		
		if(idVal==0)
			return "Registration failed...Try Again..";
		else
			return "Your Request is Accepted with Reg ID : "+idVal+" <br>Our Volunteers Will get Back u soon";
	}//regHungerPlace()
	
	@Override
	public List<String> fetchAvailLocations() {
		List<String> list=null;
		list=dao.getAvailLocations();
		return list;
	}//fetchAvailLocations

	@Override
	public List<HungerPlacesDTO> fetchHungerPlaceByLoc(String loc) {
		List<HungerPlacesDTO> listDto=new ArrayList<>();
		List<HungerPlaces> listHp=null;
	
		listHp=dao.getHungerPlaceByLoc(loc);
		
		//convert listHp to listDto
		listHp.forEach(hp->{
			HungerPlacesDTO dto=new HungerPlacesDTO();
			dto.setType(hp.getType());
			dto.setLocation(hp.getLocation());
			dto.setPeopleCount(hp.getPeopleCount());
			dto.setMobile(hp.getMobile());
			dto.setEmail(hp.getEmail());
			
			//add dto to listdto
			listDto.add(dto);
		});
		
		return listDto;
	}
	

}
