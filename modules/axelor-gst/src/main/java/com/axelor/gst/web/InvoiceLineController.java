package com.axelor.gst.web;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.service.InvoiceLineService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceLineController {

  @Inject private InvoiceLineService service;

  public void calcNetAmount(ActionRequest request, ActionResponse response) {

    InvoiceLine invoiceLine = request.getContext().asType(InvoiceLine.class);
    Invoice invoice = request.getContext().getParent().asType(Invoice.class);
    service.calcNetAmount(invoiceLine, invoice);
    response.setValue("netAmount", invoiceLine.getNetAmount());
    response.setValue("igst", invoiceLine.getIgst());
    response.setValue("sgst", invoiceLine.getSgst());
    response.setValue("cgst", invoiceLine.getCgst());
    response.setValue("grossAmount", invoiceLine.getGrossAmount());
  }
}
