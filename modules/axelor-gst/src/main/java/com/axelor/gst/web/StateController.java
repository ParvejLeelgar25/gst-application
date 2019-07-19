package com.axelor.gst.web;

import com.axelor.gst.db.State;
import com.axelor.gst.service.StateService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class StateController {

  @Inject private StateService service;

  public void upperCaseStateName(ActionRequest request, ActionResponse response) {

    State state = request.getContext().asType(State.class);
    State valueOfName = service.upperCaseStateName(state);
    response.setValue("name", valueOfName.getName());
  }
}
