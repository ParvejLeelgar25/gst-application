package com.axelor.gst.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Country;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Wizard;
import com.axelor.gst.service.InvoiceService;
import com.axelor.gst.service.SequenceService;
import com.axelor.inject.Beans;
import com.axelor.meta.db.MetaModel;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceController {

  @Inject private InvoiceService service;
  @Inject private SequenceService sequenceService;

  public void setPartyData(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setPartyData(invoice);
    response.setValue("partyContact", invoice.getPartyContact());
    response.setValue("invoiceAddress", invoice.getInvoiceAddress());
    response.setValue("shippingAddress", invoice.getShippingAddress());
  }

  public void setShippingAddress(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setShippingAddress(invoice);
    response.setValue("shippingAddress", invoice.getShippingAddress());
  }

  public void setDetails(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    service.setDetails(invoice);
    response.setValue("netAmount", invoice.getNetAmount());
    response.setValue("netIgst", invoice.getNetIgst());
    response.setValue("netSgst", invoice.getNetSgst());
    response.setValue("netCgst", invoice.getNetCgst());
    response.setValue("grossAmount", invoice.getGrossAmount());
  }

  public void getReference(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    String model = request.getModel();
    if (invoice.getReference() == null) {
      String reference = sequenceService.getReference(model);
      if (reference == null) {
        response.setError("Please Configure Sequence For Invoice");
      } else {
        response.setValue("reference", reference);
      }
    }
  }

  public void setInvoiceData(ActionRequest request, ActionResponse response) {
    Invoice invoice = request.getContext().asType(Invoice.class);
    if (request.getContext().get("product_ids") != null
        && request.getContext().get("party_id") != null) {
      List<Integer> productIdList = (List<Integer>) request.getContext().get("product_ids");
      int partyId = (int) request.getContext().get("party_id");
      Invoice invoiceObject = service.setInvoiceData(invoice, productIdList, partyId);
      response.setValues(invoiceObject);
    }
  }

  public void reCalculation(ActionRequest request, ActionResponse response) {

    Invoice invoice = request.getContext().asType(Invoice.class);
    if (invoice.getCompany() != null
        && invoice.getParty() != null
        && invoice.getInvoiceAddress() != null) {
      service.reCalculation(invoice);
      response.setValue("invoiceItems", invoice.getInvoiceItems());
      response.setValue("netAmount", invoice.getNetAmount());
      response.setValue("netIgst", invoice.getNetIgst());
      response.setValue("netSgst", invoice.getNetSgst());
      response.setValue("netCgst", invoice.getNetCgst());
      response.setValue("grossAmount", invoice.getGrossAmount());
    } else {
      response.setError("Please fill Company, Party and Invoice Address field");
    }
  }
}
