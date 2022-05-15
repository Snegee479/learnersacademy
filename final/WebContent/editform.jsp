<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Form</title>
</head>
<body>
<%@page import="com.javatpoint.dao.ClassDao,com.javatpoint.bean.ClassPojo"%>

<%
String id=request.getParameter("id");
ClassPojo u=ClassDao.getRecordById(Integer.parseInt(id));
%>

<h1>Edit Form</h1>
<form action="editclass.jsp" method="post">
<input type="hidden" name="id" value="<%=u.getId() %>"/>
<table>
<tr><td>Name:</td><td><input type="text" name="name" value="<%= u.getName()%>"/></td></tr>
<tr><td colspan="2"><input type="submit" value="Edit Class"/></td></tr>
</table>
</form>

</body>
</html>