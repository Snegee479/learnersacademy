package dao;
import pojo.StudentPOJO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/employeedB";
	private String jdbcUsername="root";
	private String jdbcPassword="Snegee@2122";
	private String jdbcDriver="com.mysql.jdbc.Driver";
	
	private static final String INSERT_STUDENTS_SQL = "insert into student (sname) VALUES (?)";
	private static final String SELECT_STUDENT_BY_ID = "select sid,sname from student where sid =?";
	private static final String SELECT_ALL_STUDENTS = "select sid,sname from student";
	private static final String DELETE_STUDENTS_SQL = "delete from student where sid = ?";
	private static final String UPDATE_STUDENTS_SQL = "update student set sname = ? where sid = ?";

	public StudentDAO() {
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

	public void insertStudent(StudentPOJO s) throws SQLException {
		System.out.println(INSERT_STUDENTS_SQL);
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			//preparedStatement.setInt(1,s.getStudentId());
			preparedStatement.setString(1,s.getStudentName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public StudentPOJO selectStudent(int id) {
		StudentPOJO s = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("sname");
				int sid=rs.getInt("sid");
				s = new StudentPOJO(sid,name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return s;
	}

	public List<StudentPOJO> selectAllStudents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<StudentPOJO> slist = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
//			ClassDAO cdao=new ClassDAO();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				slist.add(new StudentPOJO(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return slist;
	}

	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(StudentPOJO s) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL)) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, s.getStudentName());
			statement.setInt(2,s.getStudentId());

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