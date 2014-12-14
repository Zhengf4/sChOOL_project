package Beans;

import java.util.HashMap;

public class Subject {
	private String subjectCode;
	private HashMap<String, Integer> gradeMap;
	
	public Subject(){}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public HashMap<String, Integer> getGradeMap() {
		return gradeMap;
	}

	public void setGradeMap(HashMap<String, Integer> gradeMap) {
		this.gradeMap = gradeMap;
	}

}
