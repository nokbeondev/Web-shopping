<%@page import="com.my.vo.Product" %>
<%@page import="com.my.dao.ProductDAOOracle" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Product> list = (List)request.getAttribute("all"); // Servlet에서 넘겨준 것 받아오기
%>
<%-- <table>
	<tr><th>상품번호</th><th>상품명</th><th>가격</th><th>날짜</th></tr>
		<%for(int i=0; i<all.size(); i++){
		%>
		<tr>
		<td><%out.print(all.get(i).getProd_no());%></td>
		<td><%out.print(all.get(i).getProd_name());%></td>
		<td><%out.print(all.get(i).getProd_price());%></td>
		<td><%out.print(all.get(i).getProd_mfd());%></td>
		</tr>
		<%}%>
</table> --%>
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
]