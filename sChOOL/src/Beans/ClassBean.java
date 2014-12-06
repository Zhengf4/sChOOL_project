package Beans;

public class ClassBean {
	private String classId;
	private String level;
	private String section;
	
	public ClassBean(){}

	public ClassBean(String classId, String level, String section) {
		super();
		this.classId = classId;
		this.level = level;
		this.section = section;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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
