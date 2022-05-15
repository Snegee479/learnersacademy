package jspwithdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CRUD-C -creation of data -insert , R -retrival -select, U-update -update, D-delete -delete
public class EmployeeDAO {
	
	public int insertEmp(EmployeePOJO emp) throws ClassNotFoundException, SQLException {
		Connection con=EmployeeDbConn.getConn();
		String sql="insert into employee values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, emp.getEmpno());
		ps.setString(2,emp.getEmpname());
		ps.setString(3, emp.getEemail());
		return ps.executeUpdate();
	}
	
	public int updateEmp(EmployeePOJO emp) throws ClassNotFoundException, SQLException {
		//EmployeePOJO emp=getRecordById(empid);
		Connection con=EmployeeDbConn.getConn();
		String sql = "update employee set empname=? empemail=? where empid=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,emp.getEmpname());
		ps.setString(2, emp.getEemail());
		ps.setInt(3,emp.getEmpno());
		return ps.executeUpdate();
	}
	public int deleteEmp(int eid) throws ClassNotFoundException, SQLException {
		Connection con=EmployeeDbConn.getConn();		
		PreparedStatement ps=con.prepareStatement("delete from employee where empid=?");
		ps.setInt(1,eid);		
		return ps.executeUpdate();	
	}	
	public List<EmployeePOJO> display() throws ClassNotFoundException, SQLException{
		Connection con=EmployeeDbConn.getConn();
		List<EmployeePOJO> list=new ArrayList<EmployeePOJO>();
		String sql="select * from employee";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		    int id=rs.getInt(1);  
	        String name=rs.getString("empname");
	        String mail=rs.getString("empemail");
			EmployeePOJO pojo=new EmployeePOJO();
			pojo.setEmpno(id);
			pojo.setEmpname(name);
			pojo.setEemail(mail);
			list.add(pojo);
		}
		return list;
		
	}
	public EmployeePOJO getRecordById(int eid) throws ClassNotFoundException, SQLException  { 
		System.out.println("Entering in to Method");
		Connection con=EmployeeDbConn.getConn();
		System.out.println("Entering in to Method"+con);
		PreparedStatement ps=con.prepareStatement("select * from employee where empid=?");  
	    ps.setInt(1,eid);  
	    ResultSet rs=ps.executeQuery();
	    System.out.println(rs);
		EmployeePOJO emp=new EmployeePOJO();
		while(rs.next()) {
			emp.setEmpno(rs.getInt(1));
			emp.setEmpname(rs.getString("empname"));
			emp.setEemail(rs.getString("empemail"));
		}
		return emp;  }
} 





