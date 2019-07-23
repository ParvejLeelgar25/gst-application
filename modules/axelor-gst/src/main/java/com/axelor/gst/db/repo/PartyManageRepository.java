package com.axelor.gst.db.repo;

import java.util.Map;

import com.axelor.gst.db.Party;
import com.axelor.gst.db.Product;
import com.axelor.gst.service.SequenceService;
import com.beust.jcommander.Strings;
import com.google.inject.Inject;

public class PartyManageRepository extends PartyRepository {

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    if (!context.containsKey("json-enhance")) {
      return json;
    }

    try {
      Long id = (Long) json.get("id");
      Party party = find(id);
      json.put("address", party.getAddressList().get(0));
      json.put("contact", party.getContactList().get(0));
    } catch (Exception e) {
    }

    return json;
  }
}
