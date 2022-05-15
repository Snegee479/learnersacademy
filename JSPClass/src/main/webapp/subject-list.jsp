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
				<li><a href="<%=request.getContextPath()%>/listSubject"
					class="nav-link">Subjects</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Subjects</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newSubject" class="btn btn-success">Add
					New Subject</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Subject Id</th>
						<th>Subject Name</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="s" items="${listSubject}">

						<tr>
							<td><c:out value="${s.subjectId }" /></td>
							<td><c:out value="${s.subjectName}" /></td>
							<td><a href="editSubject?id=<c:out value='${s.subjectId}'/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="deleteSubject?id=<c:out value='${s.subjectId }'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
