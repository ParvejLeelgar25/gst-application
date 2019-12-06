package com.axelor.gst.web;

import com.axelor.data.csv.CSVImporter;
import com.axelor.gst.db.City;
import com.axelor.gst.db.Country;
import com.axelor.gst.db.State;
import com.axelor.gst.db.repo.CityRepository;
import com.axelor.gst.db.repo.CountryRepository;
import com.axelor.gst.service.CityService;
import com.axelor.inject.Beans;
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
  
  public void dataImport(ActionRequest request, ActionResponse response) {

	    CSVImporter importer =
	        new CSVImporter(
	            "/home/axelor/eclipse-workspace/Axelor-gst-demo/axelor-gst-app/modules/axelor-gst/src/main/resources/data-init/input-config.xml",
	            "/home/axelor/eclipse-workspace/Axelor-gst-demo/axelor-gst-app/modules/axelor-gst/src/main/resources/data-init/input");
	    importer.run();
	  }
  
  public Country computeCountry(String city) {
	  System.out.println(city);
	  City country = Beans.get(CityRepository.class).findByName(city);
	  System.out.println(country);
	  Country cr = Beans.get(CountryRepository.class).all().fetchOne();
	  return cr;
  }
}
