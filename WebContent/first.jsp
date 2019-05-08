<%@page import="com.my.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
<h1>JSP 구성요소</h1><!-- JSP는 수정하더라도 톰캣 서버를 재시작하지 않아도 된다.-->
<ul>
	<li>Scripting Element</li>
		<ol>
			<li>scriptlet : 요청시 마다 호출되는 _jspService() 내부에 기술될 구문
			<br>
				<% int i = 99;
					out.print(i);
					out.print(request.getParameter("a"));	
				%> <!-- int i가 _jspService() 내부에 들어간다.(자바 문법) -->
			
			</li>
			<li>expression : 요청 시마다 호출되는 _jspService() 내부에 기술될 구문. out.print() 자동 호출됨.
				<%=i%>, <%=request.getParameter("a") %>
			</li>
			<li>declaration : _jspService() 외부에 기술될 구문.
				<%! int i; // 인스턴스 변수로 선언 %>
				<%=i%>,
				<%= this.i %>
			</li>
		</ol>
	<li>Directive Element</li>
		<ol>
			<li>page directive : contentType - 응답형식 지정, import-클래스 임포트
				<%Product p; %>
			</li>
			<li>include directive</li>
			<li>taglib directive</li>
		</ol>
	<li>Action Tag Element</li>
</ul>
</body>
</html>