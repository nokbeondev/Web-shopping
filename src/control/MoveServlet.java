package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 지정하기 MIME : text/html; charset=UTF-8
        response.setContentType("text/html;charset=UTF-8");
        
        // 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		
		// 요청 매개변수 값 얻기 (이름 : "opt")
		String opt = request.getParameter("opt");
		
		// 요청 매개변수가 없으면
		if(opt == null) {
			out.print("<a href='move.do?opt=redirect&id=test&pwd=test!'>리다이렉트</a>");
			out.print("<br>");
			out.print("<a href='move.do?opt=foward&id=test&pwd=test!'>포워드</a>");
		}else if(opt.equals("redirect")) {
			//response.sendRedirect("login.html");
			response.sendRedirect("login.do");

		}else if(opt.equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("login.do");
			rd.forward(request, response);
		}
	}

}
