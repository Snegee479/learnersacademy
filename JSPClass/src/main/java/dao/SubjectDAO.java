package dao;
import pojo.SubjectPOJO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class SubjectDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/employeedB";
	private String jdbcUsername="root";
	private String jdbcPassword="Snegee@2122";
	private String jdbcDriver="com.mysql.jdbc.Driver";
	
	private static final String INSERT_SUBJECT_SQL = "insert into subject (sname) VALUES (?)";
	private static final String SELECT_SUBJECT_BY_ID = "select sid,sname from subject where sid =?";
	private static final String SELECT_ALL_SUBJECTS = "select * from subject";
	private static final String DELETE_SUBJECT_SQL = "delete from subject where sid = ?";
	private static final String UPDATE_SUBJECT_SQL = "update subject set sname = ? where sid = ?";

	public SubjectDAO() {
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

	public void insertSubject(SubjectPOJO s) throws SQLException {
		System.out.println(INSERT_SUBJECT_SQL);
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT_SQL)) {
			preparedStatement.setString(1,s.getSubjectName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public SubjectPOJO selectSubject(int id) {
		SubjectPOJO s = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("sname");
				s = new SubjectPOJO(name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return s;
	}

	public List<SubjectPOJO> selectAllSubjects() {
		List<SubjectPOJO> slist = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECTS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				slist.add(new SubjectPOJO(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return slist;
	}

	public boolean deleteSubject(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateSubject(SubjectPOJO s) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SQL)) {
			System.out.println("updated Subject:"+statement);
			statement.setString(1, s.getSubjectName());
			statement.setInt(2,s.getSubjectId());

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