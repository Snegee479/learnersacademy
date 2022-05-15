<%@page import="com.javatpoint.dao.ClassDao"%>
<jsp:useBean id="u" class="com.javatpoint.bean.ClassPojo"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
int i=ClassDao.update(u);
response.sendRedirect("viewclasses.jsp");
%>