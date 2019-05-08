<%@page import="com.my.vo.Product"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String sessionId = session.getId();
	long t = session.getLastAccessedTime(); // 최종 사용 시간
	Date d = new Date(t);
	String pattern = "yyyy-MM-dd hh:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	String strD = sdf.format(d);
	Map<Product, Integer> c = new HashMap<>();
	Product p = new Product();
	p.setProd_no("C001");
	p.setProd_name("부가티");
	int cnt = 1;
	c.put(p, cnt);
	session.setAttribute("cart", c);
	Map c1 = (Map) session.getAttribute("cart");
	int cnt1 = (Integer)c1.get(p);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessiontest.jsp</title>
</head>
<body>
세션ID : <%=sessionId %>><br>
세션 최종 사용 시간 : <%=strD %>




</body>
</html>