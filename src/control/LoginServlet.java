
package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.CustomerDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Customer;
/**
 * Servlet : 웹서버에서 실행되는 특수 어플리케이션
 *           main()효과없음
 * @author KITRI
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        System.out.println("LoginServlet()생성자호출됨!");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet의 doPost()호출됨!");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("사용자가 입력해준 ID:" + id);
		System.out.println("사용자가 입력해준 pwd:" + pwd);
		
		String result = "로그인 실패";
		HttpSession session = request.getSession();
		try {
			CustomerDAOOracle dao = new CustomerDAOOracle();
			Customer c = dao.selectById(id);
			if(c.getPwd().equals(pwd)) {
				result = "로그인 성공!!";
				session.setAttribute("loginInfo", c);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FindException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
  		String path = "loginresult.jsp";
  		RequestDispatcher rd = request.getRequestDispatcher(path);
  		rd.forward(request, response);
		
		
	}

}