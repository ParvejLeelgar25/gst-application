package com.axelor.gst.service;

import java.util.List;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.meta.db.MetaModel;
import com.axelor.meta.db.repo.MetaModelRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class SequenceServiceImpl implements SequenceService {
  @Inject private MetaModelRepository metaModelRepo;
  @Inject private SequenceRepository sequenceRepo;

  @Override
  public void setNextNumber(Sequence sequence) {

    String nextNumber = sequence.getPrefix();
    for (int i = 0; i < sequence.getPadding(); i++) {
      nextNumber = nextNumber + "0";
    }
    if (sequence.getSuffix() != null) {
      nextNumber = nextNumber + sequence.getSuffix();
    }
    sequence.setNextNumber(nextNumber);
  }

  @Transactional
  @Override
  public String getReference(String model) {

    String modelArray[] = model.split("\\.");
    String modelName = modelArray[modelArray.length - 1];
    MetaModel metaModel = metaModelRepo.findByName(modelName);
    Sequence sequence = sequenceRepo.all().filter("self.model = ?1", metaModel).fetchOne();

    String reference = null;

    if (sequence != null) {
      String nextNumber = sequence.getNextNumber();
      int prefixLength = sequence.getPrefix().length();
      int nextNumberLength = sequence.getNextNumber().length();
      int suffixLength = 0;

      if (sequence.getSuffix() != null) {
        suffixLength = sequence.getSuffix().length();
      }

      String subString =
          sequence.getNextNumber().substring(prefixLength, nextNumberLength - suffixLength);

      int number = Integer.parseInt(subString);
      number++;
      String numberString = Integer.toString(number);

      nextNumber = sequence.getPrefix();

      for (int i = 0; i < subString.length() - numberString.length(); i++) {
        nextNumber = nextNumber + "0";
      }
      nextNumber = nextNumber + numberString;

      if (sequence.getSuffix() != null) {
        nextNumber = nextNumber + sequence.getSuffix();
      }

      reference = nextNumber;
      sequence.setNextNumber(nextNumber);
      sequenceRepo.save(sequence);
    }
    return reference;
  }
}
