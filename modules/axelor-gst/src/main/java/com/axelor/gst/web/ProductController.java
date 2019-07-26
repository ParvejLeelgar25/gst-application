package com.axelor.gst.web;

import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Wizard;
import com.axelor.gst.service.ProductService;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.meta.schema.actions.ActionView.ActionViewBuilder;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class ProductController {
	
	@Inject ProductService service;

  public void report(ActionRequest request, ActionResponse response) {

    List<Integer> ids;
    if ((ids = (List) request.getContext().get("_ids")) == null) {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    } else {
      String ids_str = ids.toString();
      String productId = ids_str.substring(1, ids_str.length() - 1);
      request.getContext().put("productId", productId);
    }
  }

  public void createInvoice(ActionRequest request, ActionResponse response) {

    List<Integer> ids;
    if ((ids = (List) request.getContext().get("_ids")) == null) {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    } else {
      String ids_str = ids.toString();
      String productId = ids_str.substring(1, ids_str.length() - 1);
      request.getContext().put("productId", productId);
    }
  }

  public void setInvoiceData(ActionRequest request, ActionResponse response) {

    String productIds = (String) request.getContext().get("productIds");
    Party party = (Party) request.getContext().get("party");
    
    
    
  }
}
