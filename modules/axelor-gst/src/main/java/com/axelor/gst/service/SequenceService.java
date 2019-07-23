package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;

public interface SequenceService {
	
	public void setNextNumber(Sequence sequence);
	
	public String getReference(String model);
}
