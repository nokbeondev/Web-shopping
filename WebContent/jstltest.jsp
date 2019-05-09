<%@page import="com.my.vo.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstltest.jsp</title>
</head>
<body>
<c:set var="num" value="100"/>
<c:if test="${num%2==0}"/>
<hr>
<c:choose>
	<c:when test="${num%2==0}">
		${num}은 짝수 입니다.
	</c:when>
	<c:otherwise>
		${num}은 홀수입니다.
	</c:otherwise>
</c:choose>
<hr>
<c:set var="opt"/>
<c:choose>
	<c:when test="${empty param=='view'}">
		선택된 작업이 없습니다. 선택하세요!
		<ul>
			<li><a href="jstltest.jsp?opt=view">조회</a></li>
			<li><a href="jstltest.jsp?opt=add">추가</a></li>
			<li><a href="jstltest.jsp?opt=update">수정</a></li>						
		</ul>
	</c:when>
	<c:when test="${param.opt=='view'}">
		조회작업을 선택했습니다.
	</c:when>
	<c:when test="${param.opt=='add'}">
		추가작업을 선택했습니다.
	</c:when>
	<c:when test="${param.opt=='update'}">
		수정작업을 선택했습니다.
	</c:when>
</c:choose>
<hr>
<c:forEach begin="1" end="10" var="i">
	<c:if test="${i>1}">,</c:if>
	${i}
</c:forEach>
<hr>
<%
List<Customer> list = new ArrayList<>();
list.add(new Customer("a", null)); list.add(new Customer("b", null)); list.add(new Customer("c", null));
request.setAttribute("list", list);
%>

<c:forEach items="${requestScope.list}" var="c">
	${c.id}<br>
</c:forEach>
</body>
</html>