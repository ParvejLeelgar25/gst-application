package com.axelor.gst.service;

import com.axelor.gst.db.City;

public class CityServiceImpl implements CityService{
	
	@Override
	public City upperCaseCityName(City city) {
		
		String name = city.getName();
		name = name.toUpperCase();
		city.setName(name);
		return city;
	}
}
