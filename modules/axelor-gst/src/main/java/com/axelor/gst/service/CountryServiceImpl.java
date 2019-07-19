package com.axelor.gst.service;

import com.axelor.gst.db.Country;

public class CountryServiceImpl implements CountryService{
	
	@Override
	public Country upperCaseCountryName(Country country) {
		
		String name = country.getName();
		name = name.toUpperCase();
		country.setName(name); 
		return country;
	}
}
