<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head> 
<title>Teacher Management Application</title>
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
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${et != null}">
					<form action="updateTeacher" method="post">
				</c:if>
				<c:if test="${et == null}">
					<form action="insertTeacher" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${et!= null}">
            			Edit Teacher
            		</c:if>
						<c:if test="${et == null}">
            			Add New Teacher
            		</c:if>
					</h2>
				</caption>

				<c:if test="${ et!= null}">
					<input type="hidden" name="id" value="<c:out value='${et.teacherId}' />" />
				</c:if>
				
				<fieldset class="form-group">
					<label>Teacher Name</label> <input type="text"
						value="<c:out value='${et.teacherName}'/>" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Designation</label> <input type="text"
						value="<c:out value='${et.designation}'/>" class="form-control"
						name="designation" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>



