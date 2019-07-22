package com.axelor.gst.service;

import java.util.List;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.axelor.meta.db.MetaModel;
import com.axelor.meta.db.repo.MetaModelRepository;
import com.google.inject.Inject;

public class SequenceServiceImpl implements SequenceService{
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
	
	
	
	@Override
	public void giveReference(String model) {
		
		String modelArray[] = model.split("\\.");
		String modelName = modelArray[modelArray.length-1];
		MetaModel metaModel = metaModelRepo.findByName(modelName);
		List<Sequence> sequence = (List<Sequence>) sequenceRepo.all().filter("self.model= : metaModel.getName()").fetch();
		System.out.println(sequence.get(0)); 
		
	}
}
