
//insert,update,delete
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	//DBURL-oracle-     jdbc:oracle:thin:@localhost:1521:xe
public class switchCRUD {
		public static final String DB_URL="jdbc:mysql://localhost:3306/company";
		public static final String USERNAME="root";
		public static final String PASSWORD="Snegee@2122";
		public static Connection con=null;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Load driver class in order to register the vendor driver 
		Class.forName("com.mysql.jdbc.Driver");
		//establish the connection
		con=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		if(con!=null) {	
			System.out.println("connection is established");
		}
		else {
			System.out.println("check the details of the connection");
		}
		
		//create a statement ->1.Statement    2.PreparedStatement
		Statement st=con.createStatement();
		//execute query
		//CRUD-insert,select,update,delete
		String sql="insert into department values(100,'eee',1)";
		String sql2 = "update department set dname='bme' where did=106";
		String sql3 = "delete from department where did=103";

		//executeUpdate(sql)-insert,update,delete

		int row=st.executeUpdate(sql);
		if(row>0) {
			System.out.println("insertion is successful");
		}
		else {
			System.out.println("not inserted ");
		}
		int row1=st.executeUpdate(sql2);
		if(row1>0) {
			System.out.println("update is successful");
		}
	    else {
			System.out.println("not updated ");
		}
		int row2=st.executeUpdate(sql3);
		if(row2>0) {
			System.out.println("delete is successful");
		}
		else {
			System.out.println("not deleted ");
		}
		//retrieve-->select
			String sql1="select * from department";
			ResultSet rs=st.executeQuery(sql1);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			}

		
		con.close();
		
	}
	}

