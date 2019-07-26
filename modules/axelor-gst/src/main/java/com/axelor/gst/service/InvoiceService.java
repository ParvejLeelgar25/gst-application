package com.axelor.gst.service;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Wizard;

public interface InvoiceService {

  public void setPartyData(Invoice invoice);

  public void setShippingAddress(Invoice invoice);

  public void setDetails(Invoice invoice);
}
