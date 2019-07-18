package com.axelor.gst.service;

import com.axelor.gst.db.Invoice;

public interface InvoiceService {
	
	public void setPartyData(Invoice invoice);
	
	public void setShippingAddress(Invoice invoice);
}
