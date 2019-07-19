package com.axelor.gst.web;

import com.axelor.gst.db.Country;
import com.axelor.gst.service.CountryService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class CountryController {

  @Inject private CountryService service;

  public void upperCaseCountryName(ActionRequest request, ActionResponse response) {

    Country country = request.getContext().asType(Country.class);
    Country valueOfName = service.upperCaseCountryName(country);
    response.setValue("name", valueOfName.getName());
  }
}
