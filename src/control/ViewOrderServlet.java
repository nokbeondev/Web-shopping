package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOracle;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0) 로그인된 경우 아래 진행 함
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("loginInfo");
		
		if(c==null) {
			request.setAttribute("status", 0);
		}
		//1) DB에서 주문내역 얻기
		OrderDAOOracle dao = new OrderDAOOracle();
		List<OrderInfo> list = dao.selectById(c.getId());
		
		//2) request 속성으로 1)번 추가
		if(list.size()==0) {//주문내역 없는 경우
			request.setAttribute("status", -1);
		}else {// 주문내역 있는 경우
			request.setAttribute("status", 1);
			request.setAttribute("orderlist", list);
		}
		
		//3) view로 이동
		String path = "vieworderresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
