<%@page import="com.my.vo.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
<h1>JSP구성요소</h1>
<ul>
 <li>Scripting Element
    <ol>
      <li>scriptlet: 요청시마다 호출되는 
          _jspService()내부에 기술될 구문
          <%int i;
            i=99;
            out.print(i);
            out.print(request.getParameter("a"));
          %>
      </li>
      <li>expression : 요청시마다 호출되는 
          _jspService()내부에 기술될 구문.
          out.print()자동호출됨
          <%=i%>,<%=request.getParameter("a") %>
      </li>
      <li>declaration : _jspService()외부에 기술될 구문.
          <%! int i;//인스턴스변수로 선언 %>
          <%= i%>,
          <%= this.i%>
      </li>
    </ol>
 </li>
 <li>Directive Element
    <ol>
      <li>page directive : 
          contentType-응답형식지정, 
          import-클래스임포트,
          buffer-응답버퍼크기 설정 (기본크기:8kb),
          errorPage-예외발생하면 포워드될 페이지설정,
          isErrorPage-예외처리전용페이지인 경우 true로 설정
                      (exception기본객체사용가능)
        <%Product p; %>
      </li>
      <li>include directive</li>
      <li>taglib directive</li>
    </ol>
 </li>
 <li>Action Tag Element</li>
</ul>
<div>
  <h3>기본객체(내장객체)</h3>
  _jspService()내부에 이미 선언되어있는 매개변수, 지역변수들
  <ul>
    <li>request : HttpServletRequest</li>
    <li>response : HttpServletResponse</li>
    <li>pageContext : javax.servlet.jsp.PageContext</li>
    <li>session : HttpSession</li>
    <li>application : ServletContext</li>
    <li>config : ServletConfig</li>
    <li>out : JspWriter</li>
    <li>page : Object</li>
  </ul>
</div>


</body>
</html>