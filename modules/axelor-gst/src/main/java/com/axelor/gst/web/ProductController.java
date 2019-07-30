package com.axelor.gst.web;

import java.util.ArrayList;
import java.util.List;

import com.axelor.app.AppSettings;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Wizard;
import com.axelor.meta.schema.actions.ActionView;
import com.axelor.meta.schema.actions.ActionView.ActionViewBuilder;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class ProductController {

  public void report(ActionRequest request, ActionResponse response) {

    List<Integer> ids;
    if ((ids = (List) request.getContext().get("_ids")) == null) {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    } else {
      String ids_str = ids.toString();
      String productId = ids_str.substring(1, ids_str.length() - 1);
      request.getContext().put("productId", productId);
      String imagePath = AppSettings.get().getPath("file.upload.dir", "file.upload.dir");
      request.getContext().put("imagePath", imagePath);
    }
  }

  public void invoiceView(ActionRequest request, ActionResponse response) {

    if (request.getContext().get("productIds") == null) {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    } else {
      if (request.getContext().get("party") != null) {
        Party party = (Party) request.getContext().get("party");
        Long partyId = party.getId();
        request.getContext().put("partyId", partyId);
        response.setView(
            ActionView.define("Invoice")
                .model(Invoice.class.getName())
                .add("form", "invoice-form")
                .context("product_ids", request.getContext().get("productIds"))
                .context("party_id", request.getContext().get("partyId"))
                .map());
      }
      else {
    	  response.setError("Please select Party");
      }
    }
    response.setCanClose(true);
  }
}
