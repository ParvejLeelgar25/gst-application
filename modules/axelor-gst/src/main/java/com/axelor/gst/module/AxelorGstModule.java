package com.axelor.gst.module;

import com.axelor.app.AxelorModule;
import com.axelor.gst.service.GstService;
import com.axelor.gst.service.GstServiceImpl;
import com.axelor.gst.service.InvoiceLineService;
import com.axelor.gst.service.InvoiceLineServiceImpl;
import com.axelor.gst.service.InvoiceService;
import com.axelor.gst.service.InvoiceServiceImpl;

public class AxelorGstModule extends AxelorModule{
	
	protected void configure() {

	    bind(GstService.class).to(GstServiceImpl.class);
	    bind(InvoiceService.class).to(InvoiceServiceImpl.class);
	    bind(InvoiceLineService.class).to(InvoiceLineServiceImpl.class);
	  }
	
}
