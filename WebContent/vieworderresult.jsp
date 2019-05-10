<%@page import="com.my.vo.OrderLine"%>
<%@page import="com.my.vo.OrderInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%int status = (Integer) request.getAttribute("status"); %>
{JSON 객체 시작
"status":<%=status%>
<%

if(status==1){
	List<OrderInfo> orderlist = (List) request.getAttribute("orderlist");
%>, "orderlist":[
<% int i=0;
	for(OrderInfo info:orderlist){
%> <%=i>0?",":"" %>	
	{
		"order_no":"<%=info.getOrder_no() %>",
		"order_dt":"<%=info.getOrder_date() %>",
		"order_lines":[
			<%
			List<OrderLine> lines = info.getOrder_lines();
			for(int j=0; j<lines.size(); j++){
				OrderLine line = lines.get(j);
			%> <%=j>0?",":"" %>
			{주문상세 JSON 객체 시작
				"no":"<%=line.getProduct().getProd_no() %>",
				"name":"<%=line.getProduct().getProd_name() %>",
				"price":<%=line.getProduct().getProd_price() %>,
				"quantity":<%=line.getOrder_quantity() %>,
			}주문상세 JSON 객체 끝
			<%}// end inner for%>
		]
	}
%>
<%
}//end for
%>
]
<%
}//end if
%>
}JSON 객체 끝 --%>

<c:set var="status" value="${requestScope.status}"/>
{
"status":${status}

<c:if test="${status==1}">
	<c:set var="orderlist" value="${requestScope.orderlist}"/>
	, "orderlist":[
		<c:forEach items="${requestScope.orderlist}" var="info" varStatus="vst">
			<c:if test="${vst.index>0}">,</c:if>
			{
				"order_no":"${info.order_no}",
				"order_dt":"${info.order_date}",
				"order_lines":[
					<c:forEach items="${info.order_lines}" var="line" varStatus="vst2">
						<c:if test="${vst2.index>0}">,</c:if>
						{"no":"${line.getProduct().prod_no}",
						"name":"${line.getProduct().prod_name}",
						"price":${line.getProduct().prod_price},
						"quantity":${line.order_quantity}
						}
					</c:forEach>
				]
			}
		</c:forEach>
	]	
</c:if>
}



