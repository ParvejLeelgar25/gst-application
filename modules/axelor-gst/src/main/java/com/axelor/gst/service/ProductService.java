package com.axelor.gst.service;

import java.util.List;

import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;

public interface ProductService {
	
	public List<InvoiceLine> setInvoiceData(String productIds, Party party);
}
