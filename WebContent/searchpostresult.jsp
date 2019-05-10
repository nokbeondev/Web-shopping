<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
{"status":${requestScope.status}
<c:if test="${requestScope.status==1}">
,"posts":

[
<c:forEach items="${requestScope.postlist}" var="post" varStatus="vst">
	<c:if test="${vst.index>0}">,</c:if>
   	{	
   		"zipcode":"${post.zipcode}",
    	"buildingno":"${post.buildingno}",
    	<c:set var="addr" value="${post.sido} ${post.sigungu} ${post.doro} ${post.building1} ${post.building2} (${post.dong}, ${post.building})"/>
    	"addr":"${addr}"
   	}
	
</c:forEach>
]
}
</c:if>