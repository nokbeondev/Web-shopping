<%@page import="com.my.vo.Product"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Map<Product, Integer>rcart = (Map)request.getAttribute("rcart");
%>
[<% int i=0;
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
]  