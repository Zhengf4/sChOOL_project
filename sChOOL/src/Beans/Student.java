package Beans;

import java.util.ArrayList;

public class Student {
	
	private String studentId;
	private String name;
	private ArrayList<Subject> subjectList;
	private ArrayList<Clearance> clearanceList;
	
	public Student() {}
	
	public ArrayList<Clearance> getClearanceList() {
		return clearanceList;
	}

	public void setClearanceList(ArrayList<Clearance> clearanceList) {
		this.clearanceList = clearanceList;
	}

	public Student(String studentId, String name, ArrayList<Subject> subjectList, ArrayList<Clearance> clearanceList) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.subjectList = subjectList;
		this.clearanceList = clearanceList;
	}
	
	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
