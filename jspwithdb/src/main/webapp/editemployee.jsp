<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="jspwithdb.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int empno=Integer.parseInt(request.getParameter("id"));
String empname=request.getParameter("name");
String empemail=request.getParameter("email");
EmployeePOJO emp=new EmployeePOJO();
emp.setEmpno(empno);
emp.setEmpname(empname);
emp.setEemail(empemail);
session.setAttribute("e", emp);
%>
<% 
EmployeePOJO ep=(EmployeePOJO)session.getAttribute("e");
EmployeeDAO dao=new EmployeeDAO();
int row=dao.updateEmp(ep);
if(row>0){
	response.sendRedirect("display.jsp");
}
else{
	response.sendRedirect("fail.jsp");
}

%>

</body>
</html>
