package com.axelor.gst.web;

import com.axelor.gst.db.Country;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.service.InvoiceService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceController {

  @Inject private InvoiceService service;

  public void setPartyData(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setPartyData(invoice);
    response.setValue("partyContact", invoice.getPartyContact());
    response.setValue("invoiceAddress", invoice.getInvoiceAddress());
  }

  public void setShippingAddress(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    System.out.println("ddd");
    service.setShippingAddress(invoice);
    response.setValue("shippingAddress", invoice.getShippingAddress());
    System.out.println("hii");
  }
}
