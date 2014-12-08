package Beans;

import java.util.ArrayList;

public class Student {
	
	private String studentId;
	private String name;
	private ArrayList<Subject> subjectList;
	
	public Student() {}
	
	public Student(String studentId, String name, ArrayList<Subject> subjectList) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.subjectList = subjectList;
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
