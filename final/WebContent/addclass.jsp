<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<jsp:useBean id="u" class="com.javatpoint.bean.ClassPojo"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<%@page import="com.javatpoint.dao.ClassDao"%>

<%
PrintWriter pw=response.getWriter();
int i=ClassDao.save(u);
if(i>0){
	response.sendRedirect("addclass-success.jsp");
}else{
	response.sendRedirect("addclass-error.jsp");
}
%>