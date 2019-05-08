package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.my.dao.CustomerDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class Dupchkservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Dupchkservlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 데이터베이스로부터 데이터 얻기
		String id, pwd;
		id = request.getParameter("id");
		
		int result = 1;
		
		try {
			CustomerDAOOracle dao = new CustomerDAOOracle();
			Customer c = new Customer();
			
			c.setId(id);
			dao.selectById(id);
			result = -1;
			//out.print();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("result", result);
		String path = "dupchkresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
