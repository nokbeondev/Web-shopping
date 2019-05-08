<%@page import="java.io.FileInputStream"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page buffer="none"%><!-- 버퍼의 크기에 따라 응답 결과가 제대로 보일 수도 있고 안 보일 수도 있다. -->
<%@page errorPage="err.jsp"%>
<%--현재 JSP에서 예외 발생하면 버퍼를 클리어하고, 그 즉시 err.jsp로 포워드하라라는 의미 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagedirective.jsp</title>
</head>
<body>

<%
String pattern = "yyy-MM-dd"; // 서버 시간이 표시된다.
SimpleDateFormat sdf = new SimpleDateFormat(pattern);
%>
<h3>오늘 날짜 : <%=sdf.format(new Date()) %></h3>

<%for(int i=1; i<=100; i++){%>
	<%=i%>,
<%}
FileInputStream fis = null;
fis = new FileInputStream("a.txt");
%>
파일내용 : <%=fis.read()%>

<div id="div1" style="border:1px solid"></div>
<script>
var divObj = document.getElementById("div1");
divObj.innerHTML = '';
for(var i=1; i<=100; i+=2){
	divObj.innerHTML += i + ', ';
}
divObj.innerHTML += '<br>';
var now = new Date();
var y = now.getFullYear();
var m = now.getMonth()+1;
var d = now.getDate();
divObj.innerHTML += '<h3>'+y+'/'+m+'/'+d+'</h3>';// 클라이언트 시간이 표시된다.

</script>
</body>
</html>