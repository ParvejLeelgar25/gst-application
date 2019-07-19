package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;

public class SequenceServiceImpl implements SequenceService{
	
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
}
