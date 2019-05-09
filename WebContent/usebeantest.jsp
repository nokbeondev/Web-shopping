<%@page import="com.my.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebeantest.jsp</title>
</head>
<body>
<%--
// A 시작
Customer c = (Customer) request.getAttribute("c");

if(c==null){
	c = new Customer();
	request.setAttribute("c", c);
}
// A 끝
--%>
<%-- 위의 A 시작~끝까지 코드를 한방에 해결할 수 있다. - usebean 사용 --%>

<jsp:useBean id="c" class="com.my.vo.Customer" scope="request"/>


<%-- //B시작
c.setName(request.getParameter("n")); 
//B끝
--%>
<%--B 시작~끝은 아래와 같이 작성할 수 있다. --%>

<jsp:setProperty name="c" property="name" param="n"/>
<jsp:setProperty name="c" property="name" value="오징어"/>

<%--=c.getName() --%>
<jsp:getProperty name="c" property="name"/>
</body>
</html>