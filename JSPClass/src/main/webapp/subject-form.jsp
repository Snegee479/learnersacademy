<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.*" %>
<%@page import="pojo.*" %>
<%@page import="servlets.*" %>

<html>
<head> 
<title>Subject Management Application</title>
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
				<a href="https://www.xadmin.net" class="navbar-brand"> LMS Management Application </a>
			</div>

			<ul class="navbar-nav" 
			style="color: white"> 
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
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${es != null}">
					<form action="updateSubject" method="post">
				</c:if>
				<c:if test="${es == null}">
					<form action="insertSubject" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${es!= null}">
            			Edit Subject
            		</c:if>
						<c:if test="${es == null}">
            			Add New Subject
            		</c:if>
					</h2>
				</caption>

				<c:if test="${es!= null}">
					<input type="hidden" name="id" value="<c:out value='${es.subjectId}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Subject Name</label> <input type="text"
						value="<c:out value='${es.subjectName}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


