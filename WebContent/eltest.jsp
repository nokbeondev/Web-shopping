<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eltest.jsp</title>
</head>
<body>
<h3>Script and Expression</h3>
<%String opt = request.getParameter("opt"); %>
<%=opt %>
<% int num = Integer.parseInt(request.getParameter("num")); %>
num+1:<%=num+1%>
<hr>
<h3>Expression Language</h3>
opt : ${param.opt}<br>
num+1 : ${param.num+1}
</body>
</html>