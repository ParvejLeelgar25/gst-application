package com.axelor.gst.web;

import com.axelor.gst.service.SequenceService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class PartyController {
	
	@Inject private SequenceService service;
	
	public void getReference(ActionRequest request, ActionResponse response) {
		
		String model = request.getModel();
		String reference = service.getReference(model);
		response.setValue("reference",reference);
	}
}
