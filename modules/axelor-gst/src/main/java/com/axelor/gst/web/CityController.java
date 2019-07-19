package com.axelor.gst.web;

import com.axelor.gst.db.City;
import com.axelor.gst.service.CityService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class CityController {
	
	@Inject private CityService service;
	
	public void upperCaseCityName(ActionRequest request, ActionResponse response) {

	    City city = request.getContext().asType(City.class);
	    City valueOfName = service.upperCaseCityName(city);
	    response.setValue("name", valueOfName.getName());
	  }
}
