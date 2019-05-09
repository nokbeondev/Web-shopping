<%@page import="com.my.vo.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <% Map<Product, Integer> rcart = (Map)request.getAttribute("rcart");
%>
[
<% int i=0;
    for(Product p:rcart.keySet()){
	  int cnt = (Integer)rcart.get(p);%>
   <%=i>0?",":"" %>
   {"no": "<%=p.getProd_no() %>",
    "name": "<%=p.getProd_name() %>",
    "price":<%=p.getProd_price() %>,
    "cnt": <%=cnt %>
   }	    
   <% i++;
    }
 %>
] --%>


<c:set var="rcart" value="${requestScope.rcart}"/>
[
<c:forEach items="${requestScope.rcart}" var="e" varStatus="vst">
<c:set var="p" value="${e.key}"/>
<c:if test="${vst.index > 0 }">,</c:if>
{
"no": "${p.prod_no}",
"name": "${p.prod_name}",
"price":"${p.prod_price}",
"cnt": ${e.value}
}
</c:forEach>
]