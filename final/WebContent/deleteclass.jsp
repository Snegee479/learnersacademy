<%@page import="com.javatpoint.dao.ClassDao"%>
<jsp:useBean id="u" class="com.javatpoint.bean.ClassPojo"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
ClassDao.delete(Integer.parseInt(request.getParameter("id")));
response.sendRedirect("viewclasses.jsp");
%>