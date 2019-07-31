package com.axelor.gst.db.repo;

import java.util.Map;

import javax.persistence.PersistenceException;

import com.axelor.gst.db.Party;
import com.axelor.gst.db.Product;
import com.axelor.gst.service.SequenceService;
import com.axelor.inject.Beans;
import com.beust.jcommander.Strings;
import com.google.inject.Inject;

public class PartyManageRepository extends PartyRepository {

  @Inject SequenceService sequenceService;

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

  @Override
  public Party save(Party entity) {

    String reference = null;
    if (entity.getReference() == null) {
      String model = entity.getClass().getName();
      reference = sequenceService.getReference(model);
      if (reference == null) {
        throw new PersistenceException("Please Configure Sequence");
      }
    } else {
      reference = entity.getReference();
    }

    entity.setReference(reference);
    return super.save(entity);
  }
}
