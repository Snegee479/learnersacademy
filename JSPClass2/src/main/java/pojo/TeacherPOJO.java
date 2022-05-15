package pojo;

public class TeacherPOJO {
	protected int teacherId;
	protected String teacherName;
	protected String designation;
	public TeacherPOJO(){}
	public TeacherPOJO(String teacherName, String designation) {
		super();
		this.teacherName = teacherName;
		this.designation = designation;
	}
	public TeacherPOJO(int teachertId, String teacherName, String designation) {
		super();
		this.teacherId = teachertId;
		this.teacherName = teacherName;
		this.designation = designation;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teachertId) {
		this.teacherId = teachertId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
