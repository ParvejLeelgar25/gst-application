package com.axelor.gst.service;

import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;

public class ProductServiceImpl implements ProductService{
	
	@Override
	public List<InvoiceLine> setInvoiceData(String productIds, Party party) {
		List<InvoiceLine> it =new ArrayList<InvoiceLine>();
		return it;
	}
}
