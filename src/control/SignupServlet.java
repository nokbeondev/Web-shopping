package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.my.dao.CustomerDAOOracle;
import com.my.exception.AddException;
import com.my.vo.Customer;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//요청전달데이터얻기
		String id,pwd,name,buildingno, address;
		id = request.getParameter("id");
        pwd = request.getParameter("pwd");
        name = request.getParameter("name");
        buildingno = request.getParameter("buildingno");
        address = request.getParameter("addr");
                
        String result = "가입 실패";
  		try {
	        CustomerDAOOracle dao = new CustomerDAOOracle();
	        Customer c = new Customer();
	        c.setId(id);
	        c.setPwd(pwd);
	        c.setName(name);
	        c.setAddress(address);
        	dao.insert(c);
			//out.print("가입 성공");
        	result = "가입 성공";
  		} catch(ClassNotFoundException e) {
  			e.printStackTrace();
  			//out.print("가입 실패");
		} catch (AddException e) {
			e.printStackTrace();
			//out.print("가입 실패");
		} 
  		request.setAttribute("result", result);
  		String path = "signupresult.jsp";
  		RequestDispatcher rd = request.getRequestDispatcher(path);
  		rd.forward(request, response);
	}
}