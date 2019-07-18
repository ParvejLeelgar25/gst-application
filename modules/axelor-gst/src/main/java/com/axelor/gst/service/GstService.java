package com.axelor.gst.service;

import com.axelor.gst.db.City;
import com.axelor.gst.db.Company;
import com.axelor.gst.db.Country;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.State;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public interface GstService {
	
	public Country upperCaseCountryName(Country country);
	
	public State upperCaseStateName(State state);
	
	public City upperCaseCityName(City city);
	
}
