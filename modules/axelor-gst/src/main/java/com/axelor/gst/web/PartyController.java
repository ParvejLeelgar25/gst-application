package com.axelor.gst.web;

import com.axelor.gst.db.Party;
import com.axelor.gst.service.SequenceService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class PartyController {

  @Inject private SequenceService service;

  public void getReference(ActionRequest request, ActionResponse response) {

    Party party = request.getContext().asType(Party.class);
    String model = request.getModel();
    if (party.getReference() == null) {
      String reference = service.getReference(model);
      if (reference == null) {
        response.setError("Generate Sequence");
      } else {
        response.setValue("reference", reference);
      }
    }
  }
}
