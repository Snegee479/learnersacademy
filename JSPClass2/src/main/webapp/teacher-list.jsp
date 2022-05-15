<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="dao.*" %>
<%@page import="pojo.*" %>
<%@page import="servlets.*" %>
<html> 
<head>
<title>LMS Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: gray">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Teacher
					Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listTeacher"
					class="nav-link">Teachers</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Teacher</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newTeacher" class="btn btn-success">Add
					New Teacher</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Teacher Id</th>
						<th>Teacher Name</th>
						<th>Designation</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="t" items="${listTeacher}">

						<tr>
							<td><c:out value="${t.studentId}" /></td>
							<td><c:out value="${t.teacherName}"/></td>
							<td><c:out value="${t.designation}"/></td>
							<td><a href="editTeacher?id=<c:out value='${t.teacherId}'/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="deleteTeacher?id=<c:out value='${t.teacherId}'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

