<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@page import="jspwithdb.*"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CLASS LIST</title>
</head>
<body>
<body>
<h1>Class Details</h1>
<table border="1">
<%
PrintWriter pw=response.getWriter();
pw.println("Printing class list");
ClassDAO dao=new ClassDAO();
List<ClassObj> classs=(List<ClassObj>)dao.selectAllClasses();
session.setAttribute("classs",classs); 

%>
<tr><th>Classno</th><th>Classname</th><th>Edit Class</th><th>Delete Class</th></tr>
<c:forEach items="${classs}" var="e">  
<tr><td>${e.getClassId()}</td><td>${e.getClassName()}</td>
<td><a href="edit.jsp?id=${e.getClassId()}">Edit</a></td>
<td><a href="delete.jsp?id=${e.getClassId()}">Delete Person</a></td>
</c:forEach>  
</table>

</body>
</html>