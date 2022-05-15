package com.javatpoint.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.javatpoint.bean.ClassPojo;
public class ClassDao {
	
public static int save(ClassPojo u){
	int status=0;
	try{
		Connection con=DbConn.getConn();
		PreparedStatement ps=con.prepareStatement("insert into class (cid,cname) VALUES (?,?)");
		ps.setInt(1,u.getId());
		ps.setString(2,u.getName());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int update(ClassPojo u){
	int status=0;
	try{
		Connection con=DbConn.getConn();
		PreparedStatement ps=con.prepareStatement("update class set cname=? where cid=?");
		ps.setString(1,u.getName());
		ps.setInt(2,u.getId());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int delete(int id){
	int status=0;
	try{
		Connection con=DbConn.getConn();
		PreparedStatement ps=con.prepareStatement("delete from class where cid=?");
		ps.setInt(1,id);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}
public static List<ClassPojo> getAllRecords(){
	List<ClassPojo> list=new ArrayList<ClassPojo>();
	
	try{
		Connection con=DbConn.getConn();
		PreparedStatement ps=con.prepareStatement("select * from class");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			ClassPojo u=new ClassPojo();
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			list.add(u);
		}
	}catch(Exception e){System.out.println(e);}
	return list;
}
public static ClassPojo getRecordById(int id){
	ClassPojo u=null;
	try{
		Connection con=DbConn.getConn();
		PreparedStatement ps=con.prepareStatement("select * from class where cid=?");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			u=new ClassPojo();
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			}
	}catch(Exception e){System.out.println(e);}
	return u;
}
}
