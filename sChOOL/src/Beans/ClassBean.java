package Beans;

public class ClassBean {
	private String classCode;
	private String level;
	private String section;
	private String subjectCode;
	
	public ClassBean(){}

	public ClassBean(String classCode, String level, String section, String subjectCode) {
		super();
		this.classCode = classCode;
		this.level = level;
		this.section = section;
		this.subjectCode = subjectCode;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
}
