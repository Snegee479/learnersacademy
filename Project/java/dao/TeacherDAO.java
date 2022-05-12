//package dao;
//import java.sql.Connection;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import pojo.TeacherPOJO;
//public class TeacherDAO {
//	public static Connection getConnection(){  
//	    Connection con=null;  
//	    try{  
//	        Class.forName("com.mysql.jdbc.Driver");  
//	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedB","root","Snegee@2122");  
//	    }catch(Exception e){System.out.println(e);}  
//	    return con;  
//	}  
//	public static int save(TeacherPOJO p){  
//	    int status=0;  
//	    try{  
//	        Connection con=getConnection();  
//	        PreparedStatement ps=con.prepareStatement(  
//	"insert into teacher(id,name..) values(?,?,?,?,?)");  
//	         
//	        status=ps.executeUpdate();  
//	    }catch(Exception e){System.out.println(e);}  
//	    return status;  
//	}  
//	public static int update(TeacherPOJO p){  
//	    int status=0;  
//	    try{  
//	        Connection con=getConnection();  
//	        PreparedStatement ps=con.prepareStatement(  
//	"update register set name=?,password=?,email=?,sex=?,country=? where id=?");  
//	       
//	        status=ps.executeUpdate();  
//	    }catch(Exception e){System.out.println(e);}  
//	    return status;  
//	}  
//	public static int delete(TeacherPOJO p){  
//	    int status=0;  
//	    try{  
//	        Connection con=getConnection();  
//	        PreparedStatement ps=con.prepareStatement("delete from teacher where tid=?");  
//	        ps.setInt(1,p.getTeachertId());  
//	        status=ps.executeUpdate();  
//	    }catch(Exception e){System.out.println(e);}  
//	  
//	    return status;  
//	}  
//	public static List<TeacherPOJO> getAllRecords(){  
//	    List<TeacherPOJO> list=new ArrayList<TeacherPOJO>();  
//	      
//	    try{  
//	        Connection con=getConnection();  
//	        PreparedStatement ps=con.prepareStatement("select * from teacher");  
//	        ResultSet rs=ps.executeQuery();  
//	        while(rs.next()){  
//	        	TeacherPOJO p=new TeacherPOJO();  
//	            
//	            list.add(p);  
//	        }  
//	    }catch(Exception e){System.out.println(e);}  
//	    return list;  
//	}  
//	public static TeacherPOJO getRecordById(int id){  
//		TeacherPOJO p=null;  
//	    try{  
//	        Connection con=getConnection();  
//	        PreparedStatement ps=con.prepareStatement("select * from teacher where id=?");  
//	        ps.setInt(1,id);  
//	        ResultSet rs=ps.executeQuery();  
//	        while(rs.next()){  
//	            p=new TeacherPOJO();  
//	            
//	             
//	        }  
//	    }catch(Exception e){System.out.println(e);}  
//	    return u;  
//	}  
//	}  