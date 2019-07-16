package com.axelor.gst.web;

import com.axelor.gst.db.City;
import com.axelor.gst.db.Country;
import com.axelor.gst.db.State;
import com.axelor.gst.service.GstService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class GstController {
	
	@Inject private GstService service;
	
	public void upperCaseCountryName(ActionRequest request, ActionResponse response) {
		
		Country country =  request.getContext().asType(Country.class);
		Country valueOfName = service.upperCaseCountryName(country);
		response.setValue("name", valueOfName.getName());
	}
	
	public void upperCaseStateName(ActionRequest request, ActionResponse response) {
		
		State state =  request.getContext().asType(State.class);
		State valueOfName = service.upperCaseStateName(state);
		response.setValue("name", valueOfName.getName());
	}
	
	public void upperCaseCityName(ActionRequest request, ActionResponse response) {
		
		City city =  request.getContext().asType(City.class);
		City valueOfName = service.upperCaseCityName(city);
		response.setValue("name", valueOfName.getName());
	}
}
