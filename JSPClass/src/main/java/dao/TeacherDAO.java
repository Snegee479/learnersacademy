package dao;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.*;

public class TeacherDAO {
	public TeacherDAO() {}
	private String jdbcURL="jdbc:mysql://localhost:3306/employeedB";
	private String jdbcUsername="root";
	private String jdbcPassword="Snegee@2122";
	private String jdbcDriver="com.mysql.jdbc.Driver";
	
	private static final String INSERT_TEACHER_SQL = "insert into teacher (tname,designation) values (?,?)";
	private static final String SELECT_TEACHER_BY_ID = "select tid,tname,designation from teacher where tid =?";
	private static final String SELECT_ALL_TEACHERS = "select * from teacher";
	private static final String DELETE_TEACHER_SQL = "delete from teacher where tid =?";
	private static final String UPDATE_TEACHER_SQL = "update teacher set tname=?,designation=? where tid=?";
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

	public void insertTeacher(TeacherPOJO t) throws SQLException {
		System.out.println(INSERT_TEACHER_SQL);
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHER_SQL)) {
			//preparedStatement.setInt(1,t.getTeacherId());
			preparedStatement.setString(1,t.getTeacherName());
			preparedStatement.setString(2,t.getDesignation());			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public TeacherPOJO selectTeacher(int id) {
		TeacherPOJO s = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEACHER_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("tname");
				String designation=rs.getString("designation");
				s = new TeacherPOJO(name,designation);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return s;
	}

	public List< TeacherPOJO > selectAllTeachers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List< TeacherPOJO > tlist = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEACHERS)) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("tid");
				String name = rs.getString("tname");
				String designation=rs.getString("designation");
				tlist.add(new TeacherPOJO (id, name,designation));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tlist;
	}

	public boolean deleteTeacher(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTeacher(TeacherPOJO t) throws SQLException {
		boolean rowUpdated;
		//update teacher set tname=?,designation=? where tid=?
		System.out.println("inside update method "+t.getTeacherName());
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER_SQL)) {
			System.out.println("updated teacher:"+statement);
			statement.setString(1,t.getTeacherName());
			statement.setString(2,t.getDesignation());
			statement.setInt(3,t.getTeacherId());
			System.out.println("statement variables in sql "+statement.toString());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
}  
