package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;

public class InvoiceLineServiceImpl implements InvoiceLineService{
	
	@Override
	public void calcNetAmount(InvoiceLine invoiceLine, Invoice invoice) {
		
		BigDecimal netAmount = BigDecimal.ZERO;
		BigDecimal grossAmount = BigDecimal.ZERO;
		BigDecimal igst = BigDecimal.ZERO;
		BigDecimal sgst = BigDecimal.ZERO;
		BigDecimal cgst = BigDecimal.ZERO;
		
		netAmount = invoiceLine.getPrice().multiply(BigDecimal.valueOf(invoiceLine.getQty()));	
		if(!(invoice.getCompany().getAddress().getState().equals(invoice.getInvoiceAddress().getState()))) {
			igst = netAmount.multiply(invoiceLine.getGstRate());
			grossAmount = netAmount.add(igst);	
		}
		
		else
		{
			sgst = (netAmount.multiply(invoiceLine.getGstRate())).divide(BigDecimal.valueOf(2));
			cgst = (netAmount.multiply(invoiceLine.getGstRate())).divide(BigDecimal.valueOf(2));
			grossAmount = netAmount.add(sgst).add(cgst);
		} 
		invoiceLine.setNetAmount(netAmount);
		invoiceLine.setIgst(igst);
		invoiceLine.setSgst(sgst);
		invoiceLine.setCgst(cgst);
		invoiceLine.setGrossAmount(grossAmount);
	}
	
/*	@Override
	public void setDetails(InvoiceLine invoiceLine,Invoice invoice) {
		
		BigDecimal netAmount = BigDecimal.ZERO;
		BigDecimal grossAmount = BigDecimal.ZERO;
		BigDecimal igst = BigDecimal.ZERO;
		BigDecimal sgst = BigDecimal.ZERO;
		BigDecimal cgst = BigDecimal.ZERO;
		
		netAmount = invoiceLine.getNetAmount();
		invoice.setNetAmount(netAmount);
	}*/

}
