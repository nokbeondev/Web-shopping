<%@page import="com.my.vo.Product"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%
	Product product = (Product) request.getAttribute("product");
%>
{
	"no" : "<%=product.getProd_no()%>",
	"name" : "<%=product.getProd_name()%>",
	"price" : <%=product.getProd_price()%>,
	"mfd" : "<%=product.getProd_mfd()%>"
} --%>

<c:set var="prodcut" value="${requestScope.product}"/>
{
"no" : "${product.prod_no}",
"name" : "${product.prod_name}",
"price" : ${product.prod_price},
"mfd" : "${product.prod_mfd}"
}