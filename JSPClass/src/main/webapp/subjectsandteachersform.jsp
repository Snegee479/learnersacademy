<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="dao.*" %>
<%@page import="pojo.*" %>
<%@page import="servlets.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int id=Integer.parseInt(request.getParameter("id"));
SubjectDAO s= new SubjectDAO();
List<SubjectPOJO> sublist=s.selectAllSubjects();
TeacherDAO t= new TeacherDAO();
List<TeacherPOJO> tlist=t.selectAllTeachers();
ClassReportDAO crdao=new ClassReportDAO();
%>
<form action="submitsubandteacher" method="post">
<table>
<tr><td>SUBJECT:</td><td>
<select name="subject" style="width:155px">
<%for(int i=0;i<sublist.size();i++){%>
	<option value=<%=sublist.get(i).getSubjectId()%>><%=sublist.get(i).getSubjectName()%>
	</option>
<%}%>
</select>
</td></tr>

<tr><td>TEACHER:</td><td>
<select name="teacher" style="width:155px">
<%for(int y=0;y<tlist.size();y++){%>
	<option value=<%=tlist.get(y).getTeacherId()%>><%=tlist.get(y).getTeacherName()%></option>
<%}%>
</select>
</td></tr>
<tr><td colspan="2"><input type="submit" value="Save"/></td></tr>
</table>
</form>

</body>
</html>