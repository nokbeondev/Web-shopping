<%@page import="com.my.vo.Product" %>
<%@page import="com.my.dao.ProductDAOOracle" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <%
	List<Product> list = (List)request.getAttribute("all"); // Servlet에서 넘겨준 것 받아오기
%>

[
<%for(int i=0; i<list.size(); i++) {
	Product p = list.get(i);
%><%=i>0?",":""%>
  {
     "no": "<%=p.getProd_no()%>",
     "name": "<%=p.getProd_name()%>",
     "price": <%=p.getProd_price()%>
  }
<%} %>
] --%>


<%--아래처럼 EL(Expressoin Language)를 사용하면 간결하게 표현 가능하다 --%>
<c:set var="all" value="${requestScope.all}"/>
[
<c:forEach items="${requestScope.all}" var="p" varStatus="vst">
<c:if test="${vst.index > 0}">,</c:if>
{
"no":"${p.prod_no}",
"name":"${p.prod_name}",
"price":${p.prod_price}
}
</c:forEach>
]




