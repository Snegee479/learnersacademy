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
				<a href="https://www.xadmin.net" class="navbar-brand"> LMS
					Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Classes</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listStudent"
					class="nav-link">Students</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listTeacher"
					class="nav-link">Teachers</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listSubject"
					class="nav-link">Subjects</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Class Report</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr><th>Subject Name</th>
						<th>Teacher Name</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="c1" items="${c1}">
					<tr>
					<td><c:out value="${c1.subjectName}"/></td>
					<td><c:out value="${c1.teacherName}"/></td>
					</tr>
				</c:forEach>
				</tbody>
				<thead>
					<tr><th>Student Name</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="c" items="${c}">
					<tr>
					<td><c:out value="${c.studentName}"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>