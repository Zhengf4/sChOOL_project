package Beans;

public class Subject {
	private String subjectCode;
	private Integer firstQuarterGrade;
	private Integer secondQuarterGrade;
	private Integer thirdQuarterGrade;
	private Integer fourthQuarterGrade;
	
	public Subject(){}
	
	public Subject(String subjectCode, Integer firstQuarterGrade, 
			Integer secondQuarterGrade, Integer thirdQuarterGrade, Integer fourthQuarterGrade) {
		super();
		this.subjectCode = subjectCode;
		this.firstQuarterGrade = firstQuarterGrade;
		this.secondQuarterGrade = secondQuarterGrade;
		this.thirdQuarterGrade = thirdQuarterGrade;
		this.fourthQuarterGrade = fourthQuarterGrade;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public Integer getFirstQuarterGrade() {
		return firstQuarterGrade;
	}
	public void setFirstQuarterGrade(Integer firstQuarterGrade) {
		this.firstQuarterGrade = firstQuarterGrade;
	}
	public Integer getSecondQuarterGrade() {
		return secondQuarterGrade;
	}
	public void setSecondQuarterGrade(Integer secondQuarterGrade) {
		this.secondQuarterGrade = secondQuarterGrade;
	}
	public Integer getThirdQuarterGrade() {
		return thirdQuarterGrade;
	}
	public void setThirdQuarterGrade(Integer thirdQuarterGrade) {
		this.thirdQuarterGrade = thirdQuarterGrade;
	}
	public Integer getFourthQuarterGrade() {
		return fourthQuarterGrade;
	}
	public void setFourthQuarterGrade(Integer fourthQuarterGrade) {
		this.fourthQuarterGrade = fourthQuarterGrade;
	}

}
