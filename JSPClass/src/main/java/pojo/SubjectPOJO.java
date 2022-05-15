package pojo;

public class SubjectPOJO {
	protected int subjectId;
	protected String subjectName;
	public SubjectPOJO() {}
	public SubjectPOJO(int subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
	public SubjectPOJO(String subjectName) {
		super();
		this.subjectName = subjectName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	}
