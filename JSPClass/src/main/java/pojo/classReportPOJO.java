package pojo;

public class classReportPOJO {
	int classId;
	String subjectName,	teacherName, studentName;
	public classReportPOJO(String subjectName, String teacherName) {
		super();
		this.subjectName = subjectName;
		this.teacherName = teacherName;
	}
	public classReportPOJO(String studentName) {
		super();
		this.studentName = studentName;
	}
	public classReportPOJO(int classId, String subjectName, String teacherName) {
		super();
		this.classId = classId;
		this.subjectName = subjectName;
		this.teacherName = teacherName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
	
}
