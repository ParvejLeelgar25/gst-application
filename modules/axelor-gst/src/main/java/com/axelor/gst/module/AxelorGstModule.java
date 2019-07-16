package com.axelor.gst.module;

import com.axelor.app.AxelorModule;
import com.axelor.gst.service.GstService;
import com.axelor.gst.service.GstServiceImpl;

public class AxelorGstModule extends AxelorModule{
	
	protected void configure() {

	    bind(GstService.class).to(GstServiceImpl.class);
	  }
	
}
