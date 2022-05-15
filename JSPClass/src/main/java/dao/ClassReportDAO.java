package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.*;
public class ClassReportDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/employeedB";
	private String jdbcUsername="root";
	private String jdbcPassword="Snegee@2122";
	private String jdbcDriver="com.mysql.jdbc.Driver";

	private static final String SUBJECT_TEACHER_OF_CLASS = "select sname,tname from subject right outer join teacher on subject.sid=teacher.sid where teacher.cid=?";
	private static final String STUDENTS_OF_CLASS = "select sname from student left outer join class on student.cid=class.cid where student.cid=?";
	public ClassReportDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public List<StudentPOJO> classReportStudent(int id) {
		List<StudentPOJO> slist = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(STUDENTS_OF_CLASS)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String sname = rs.getString("sname");
				slist.add(new StudentPOJO(sname));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return slist;

	}
	public List<classReportPOJO> classReportTeacherSubject(int id) {
		List<classReportPOJO> tlist=new ArrayList<classReportPOJO>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SUBJECT_TEACHER_OF_CLASS)) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String suname = rs.getString(1);
					String tname = rs.getString(2);
					//classReportPOJO classReportPOJO = new classReportPOJO();
					tlist.add(new classReportPOJO(suname,tname));
				}
				System.out.println("tlist sizq"+tlist.size());
			} catch (SQLException e) {
				printSQLException(e);
			}
			return tlist;
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	

	public void subjectsAndTeachers(int classId, int subId, int teaId) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("update teacher set cid= ?, sid=? where tid=?"); 
		preparedStatement.setInt(1, classId);	
		preparedStatement.setInt(2, subId);
		preparedStatement.setInt(3, teaId);
		System.out.println(preparedStatement);
		int result  = preparedStatement.executeUpdate();
		System.out.println("affected row "+result);
		
	}
}
	
