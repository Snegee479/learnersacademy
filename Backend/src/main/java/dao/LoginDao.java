//package dao;
//import pojo.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginDao {
//	public static Connection getConnection(){  
//	    Connection con=null;  
//	    try{  
//	        Class.forName("com.mysql.jdbc.Driver");  
//	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedB","root","Snegee@2122");  
//	    }catch(Exception e){System.out.println(e);}  
//	    return con;  
//	}
//
//    public boolean validate(LoginPOJO loginBean) throws ClassNotFoundException {
//        boolean status = false;
//        try {	        
//        	Connection con=getConnection();  
//    	    PreparedStatement ps=con.prepareStatement("select * from login where username = ? and password = ? ");
//            ps.setString(1, loginBean.getUsername());
//            ps.setString(2, loginBean.getPassword());
//
//            System.out.println(ps);
//            ResultSet rs = ps.executeQuery();
//            status = rs.next();
//
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return status;
//    }
//
//    private void printSQLException(SQLException ex) {
//        for (Throwable e: ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }
//}