package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public LogoutServlet() {
        super();
        System.out.println("LogoutServlet() 생성자 호출!");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LogoutServlet()의 doPost() 호출!");
        HttpSession session = request.getSession();
        session.removeAttribute("loginInfo");
        
        String result = "Logout 되었습니다.";
        String path = "logoutresult.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
        
        
        
        
        
	}

}
