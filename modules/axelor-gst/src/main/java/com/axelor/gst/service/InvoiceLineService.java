package com.axelor.gst.service;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;

public interface InvoiceLineService {

  public void calcNetAmount(InvoiceLine invoiceLine, Invoice invoice);
  
  public void setProductDetails(InvoiceLine invoiceLine, Invoice invoice);
}
