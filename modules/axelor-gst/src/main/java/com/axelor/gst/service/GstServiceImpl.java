package com.axelor.gst.service;

import com.axelor.gst.db.City;
import com.axelor.gst.db.Company;
import com.axelor.gst.db.Country;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.State;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class GstServiceImpl implements GstService{
	
	@Override
	public Country upperCaseCountryName(Country country) {
		
		String name = country.getName();
		name = name.toUpperCase();
		country.setName(name); 
		return country;
	}
	
	@Override
	public State upperCaseStateName(State state) {
		
		String name = state.getName();
		name = name.toUpperCase();
		state.setName(name);
		return state;
	}
	
	@Override
	public City upperCaseCityName(City city) {
		
		String name = city.getName();
		name = name.toUpperCase();
		city.setName(name);
		return city;
	}
	
	@Override
	public Invoice setDefaultCompany(Invoice invoice) {
		System.out.println("ffff" ); 
		Int invoice.getCompany().getId();
		System.out.println("gggg" ); 
		System.out.println(company.getId()); 
		System.out.println("vvvv" ); 
		return invoice;
	}
}
