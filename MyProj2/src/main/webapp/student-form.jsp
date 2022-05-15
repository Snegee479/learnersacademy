<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head> 
<title>Student Management Application</title>
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
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${es != null}">
					<form action="updateStudent" method="post">
				</c:if>
				<c:if test="${es == null}">
					<form action="insertStudent" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${es!= null}">
            			Edit student
            		</c:if>
						<c:if test="${es == null}">
            			Add New student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${ es!= null}">
					<input type="hidden" name="id" value="<c:out value='${ es.studentId}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Student id</label> <input type="text"
						value="<c:out value='${ ec.studentId}' />" class="form-control"
						name="id" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${ ec.studentName}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


