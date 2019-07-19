package com.axelor.gst.module;

import com.axelor.app.AxelorModule;
import com.axelor.gst.db.repo.ProductManageRepository;
import com.axelor.gst.db.repo.ProductRepository;
import com.axelor.gst.service.CityService;
import com.axelor.gst.service.CityServiceImpl;
import com.axelor.gst.service.CountryService;
import com.axelor.gst.service.CountryServiceImpl;
import com.axelor.gst.service.InvoiceLineService;
import com.axelor.gst.service.InvoiceLineServiceImpl;
import com.axelor.gst.service.InvoiceService;
import com.axelor.gst.service.InvoiceServiceImpl;
import com.axelor.gst.service.SequenceService;
import com.axelor.gst.service.SequenceServiceImpl;
import com.axelor.gst.service.StateService;
import com.axelor.gst.service.StateServiceImpl;

public class AxelorGstModule extends AxelorModule {

  protected void configure() {

    bind(InvoiceService.class).to(InvoiceServiceImpl.class);
    bind(InvoiceLineService.class).to(InvoiceLineServiceImpl.class);
    bind(CityService.class).to(CityServiceImpl.class);
    bind(StateService.class).to(StateServiceImpl.class);
    bind(CountryService.class).to(CountryServiceImpl.class);
    bind(SequenceService.class).to(SequenceServiceImpl.class);
    bind(ProductRepository.class).to(ProductManageRepository.class);
  }
}
