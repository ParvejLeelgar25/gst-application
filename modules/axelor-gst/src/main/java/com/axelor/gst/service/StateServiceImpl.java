package com.axelor.gst.service;

import com.axelor.gst.db.State;

public class StateServiceImpl implements StateService {

  @Override
  public State upperCaseStateName(State state) {

    String name = state.getName();
    name = name.toUpperCase();
    state.setName(name);
    return state;
  }
}
