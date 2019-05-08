<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>includetest.jsp</title>
</head>
<body>
<h3>include 지시자를 이용한 포함 (정적 포함)</h3>
<%@include file="login.html"%>
<hr> <!-- 수평선 긋기 -->
<h3>태그를 이용한 포함(동적 포함, 결과값만 포함)</h3>
<jsp:include page="login.html"/>
</body>
</html>