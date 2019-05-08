package control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ServletContextTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc; // ServletContext : 웹 컨텍스트별 만들어지는 객체
		sc = this.getServletContext();
		String realPath = sc.getRealPath("login.html");
		System.out.println(realPath);
		int major = sc.getMajorVersion();
		System.out.println(major); // 2
		System.out.println(sc.getMinorVersion()); // 1
		
		
	}

}
