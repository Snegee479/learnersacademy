<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Class Success</title>
</head>
<body>
<%
PrintWriter pw=response.getWriter();
//pw.println("inside addclass-error jsp"+request.getParameter("i"));
%>
<p>Sorry, an error occurred!</p>
<jsp:include page="classform.html"></jsp:include>

</body>
</html>