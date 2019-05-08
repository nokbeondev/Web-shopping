<%@ page import="com.my.vo.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Product product = (Product) request.getAttribute("product");
%>
{
	"no" : "<%=product.getProd_no()%>",
	"name" : "<%=product.getProd_name()%>",
	"price" : <%=product.getProd_price()%>,
	"mfd" : "<%=product.getProd_mfd()%>"
}