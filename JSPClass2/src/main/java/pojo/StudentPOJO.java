package pojo;

public class StudentPOJO {
	protected int studentId;
	protected String studentName;
	public StudentPOJO() {}
	public StudentPOJO(String studentName) {
		super();
		this.studentName = studentName;
	}
	public StudentPOJO(int studentId, String studentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}
