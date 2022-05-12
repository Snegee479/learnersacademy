<%@page import="jspwithdb.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DISPLAY</title>
</head>
<body>
<h1>Employee Details</h1>
<table border="1">
<%EmployeeDAO dao=new EmployeeDAO();
List<EmployeePOJO> emp=(List<EmployeePOJO>)dao.display();
session.setAttribute("emp",emp);  
%>
<tr><th>Empno</th><th>Empname</th><th>Empemail</th><th>Edit Name</th><th>Delete person</th></tr>
<c:forEach items="${emp}" var="e">  
<tr><td>${e.getEmpno()}</td><td>${e.getEmpname()}</td><td>${e.getEemail()}</td>
<td><a href="edit.jsp?id=${e.getEmpno()}">Edit</a></td>
<td><a href="delete.jsp?id=${e.getEmpno()}">Delete Person</a></td>
</c:forEach>  
</table>
 
</body>
</html>


