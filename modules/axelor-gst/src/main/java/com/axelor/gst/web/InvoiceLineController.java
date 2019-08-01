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

    if (invoice.getCompany() != null
        && invoice.getParty() != null
        && invoice.getInvoiceAddress() != null) {
      if (invoice.getCompany().getAddress() != null) {
        if (invoice.getCompany().getAddress().getState() != null) {
          if (invoice.getInvoiceAddress().getState() != null) {
            service.calcNetAmount(invoiceLine, invoice);
            response.setValue("netAmount", invoiceLine.getNetAmount());
            response.setValue("igst", invoiceLine.getIgst());
            response.setValue("sgst", invoiceLine.getSgst());
            response.setValue("cgst", invoiceLine.getCgst());
            response.setValue("grossAmount", invoiceLine.getGrossAmount());
          } else {
            response.setError("Please fill state of Invoice Address");
          }

        } else {
          response.setError("Please fill state of Company");
        }

      } else {
        response.setError("Please fill address of Company");
      }

    } else {
      response.setError("Please fill Required field");
    }
  }

  public void setProductDetails(ActionRequest request, ActionResponse response) {
    InvoiceLine invoiceLine = request.getContext().asType(InvoiceLine.class);
    Invoice invoice = request.getContext().getParent().asType(Invoice.class);
    if (invoice.getParty() != null
        && invoice.getInvoiceAddress() != null
        && invoice.getCompany() != null) {
      service.setProductDetails(invoiceLine, invoice);
      response.setValue("item", invoiceLine.getItem());
      response.setValue("gstRate", invoiceLine.getGstRate());
      response.setValue("price", invoiceLine.getPrice());

    } else {
      response.setError("Please fill Required Field of Invoice");
    }
  }
}
