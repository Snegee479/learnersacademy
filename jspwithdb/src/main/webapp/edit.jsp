<%@page import="jspwithdb.EmployeeDAO"%>
<%@page import="jspwithdb.EmployeePOJO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
int eid=Integer.parseInt(request.getParameter("id"));	
PrintWriter pw=response.getWriter();
EmployeePOJO e=dao.getRecordById(eid);
pw.println(eid+" from request");
pw.println(e.getEmpno());

%>
<h1>Update form</h1>  
<form action="editemployee.jsp" method="post">  
<input type="text" name="id" value="<%=request.getParameter("id")%>"/>  
<table>  
<tr><td>Name:</td><td>  
<input type="text" name="name" value="<%=e.getEmpname()%>"/></td></tr>  
<tr><td>Email:</td><td>  
<input type="text" name="email" value="<%=e.getEemail()%>"/></td></tr>  

<tr><td colspan="2"><input type="submit" value="Update Employee"/></td></tr>  
</table>  
</form> 

</body>
</html>