<%@page import="com.my.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav>
  <ul>
  <!-- 로그인 성공 -->
  <%Customer c = (Customer) session.getAttribute("loginInfo");
  	if(c==null){%>
    <li><a href="login.html">로그인</a></li>
    <li><a href="signup.html">가입</a></li>
    <%}else{ // 로그인 성공된 경우%>
    <li><%=c.getId()%>님 안녕하세요~</a></li>
    <li><a href="logout.do">로그아웃</a></li>
    <%} %>
  </ul>
</nav>