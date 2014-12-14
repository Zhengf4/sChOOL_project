package Beans;

import org.joda.time.DateTime;

public class Clearance {
	
	private String clearance;
	private DateTime dateTimeIssued;
	private DateTime dateTimeResolved;
	
	public Clearance(){}
	
	public Clearance(String clearance, DateTime dateTimeIssued, DateTime dateTimeResolved) {
		super();
		this.clearance = clearance;
		this.dateTimeIssued = dateTimeIssued;
		this.dateTimeResolved = dateTimeResolved;
	}
	
	public String getClearance() {
		return clearance;
	}
	public void setClearance(String clearance) {
		this.clearance = clearance;
	}
	public DateTime getDateTimeIssued() {
		return dateTimeIssued;
	}
	public void setDateTimeIssued(DateTime dateTimeIssued) {
		this.dateTimeIssued = dateTimeIssued;
	}
	public DateTime getDateTimeResolved() {
		return dateTimeResolved;
	}
	public void setDateTimeResolved(DateTime dateTimeResolved) {
		this.dateTimeResolved = dateTimeResolved;
	}

}
