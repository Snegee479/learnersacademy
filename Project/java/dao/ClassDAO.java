package dao;
import pojo.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.StudentPOJO;
public class ClassDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/employeedB";
	private String jdbcUsername="root";
	private String jdbcPassword="123456";
	private String jdbcDriver="com.mysql.jdbc.Driver"; 

	private static final String INSERT_CLASSES_SQL = "INSERT INTO class" + "  (cname) VALUES "
			+ " (?);";

	private static final String SELECT_CLASS_BY_ID = "select cid,cname from users where cid =?";
	private static final String SELECT_ALL_CLASSES = "select * from class";
	private static final String DELETE_CLASSES_SQL = "delete from class where cid = ?;";
	private static final String UPDATE_CLASSES_SQL = "update users set cname = ? where cid = ?;";

	public ClassDAO() {	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertClass(ClassPOJO c) throws SQLException {
		System.out.println(INSERT_CLASSES_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASSES_SQL)) {
			preparedStatement.setString(1,c.getClassName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
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
	public List<ClassPOJO> selectAllClasses() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<ClassPOJO> clist = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("cid");
				String name = rs.getString("cname");
				clist.add(new ClassPOJO(id, name));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return clist;
	}
	public ClassPOJO selectClass(int id) {
		ClassPOJO c = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("cname");
				c = new ClassPOJO(id, name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return c;
	}
	public boolean deleteClass(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CLASSES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateClass(ClassPOJO c) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CLASSES_SQL);) {
			System.out.println("updated class : "+statement);
			statement.setString(1, c.getClassName());
			statement.setInt(2, c.getClassId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

}
