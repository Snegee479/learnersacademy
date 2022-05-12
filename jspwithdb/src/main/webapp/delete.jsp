<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jspwithdb.*"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
EmployeeDAO dao=new EmployeeDAO();
PrintWriter pw=response.getWriter();
pw.println(Integer.parseInt(request.getParameter("id")));
int row=dao.deleteEmp(Integer.parseInt(request.getParameter("id")));
pw.println(row);
if(row>0){
 	response.sendRedirect("display.jsp");
}
else{
	response.sendRedirect("fail.jsp");
}
%>
</body>
</html>