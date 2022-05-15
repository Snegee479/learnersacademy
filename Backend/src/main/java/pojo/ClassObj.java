package pojo;

public class ClassObj {
	protected int classId;
	protected String className;
	
	public ClassObj() {
	}
	public ClassObj(int classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}
	public ClassObj(String className) {
		super();
		this.className = className;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}
