package org.springframework.samples.yogogym.service.exceptions;

@SuppressWarnings("serial")
public class PeriodIncludingTrainingException extends Exception{
	
	private String initAssoc;
	private String EndAssoc;
	
	public PeriodIncludingTrainingException(String initAssoc, String endAssoc) {
		super();
		this.initAssoc = initAssoc;
		EndAssoc = endAssoc;
	}

	public String getInitAssoc() {
		return initAssoc;
	}
	
	public void setInitAssoc(String initAssoc) {
		this.initAssoc = initAssoc;
	}
	
	public String getEndAssoc() {
		return EndAssoc;
	}
	
	public void setEndAssoc(String endAssoc) {
		EndAssoc = endAssoc;
	}
	
}
