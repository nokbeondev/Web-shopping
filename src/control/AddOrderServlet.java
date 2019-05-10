package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.OrderDAOOracle;
import com.my.exception.AddException;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주문 추가하기
		HttpSession session = request.getSession();
		// 0) 로그인 했는지 확인!
		Customer c = (Customer) session.getAttribute("loginInfo");
		
		if(c==null) { // 로그인 안 한경우
			request.setAttribute("status", 0);
		}else {// 로그인 한 경우
			//1) 장바구니 내용 가져오기
			Map<Product, Integer> cart = (Map) session.getAttribute("cart");
			
			//2) DB에 저장
			OrderInfo info = new OrderInfo();
			
			//info객체에 장바구니 내용 채우기
			info.setCustomer(c); //주문자ID
			List<OrderLine> order_lines = new ArrayList<>();
			
			for(Product product:cart.keySet()) {
				int order_quantity = cart.get(product);
				OrderLine line = new OrderLine();
				line.setProduct(product); //주문 상품 정보
				line.setOrder_quantity(order_quantity); // 주문 수량
				order_lines.add(line);
			}
			
			info.setOrder_lines(order_lines);
			OrderDAOOracle dao = new OrderDAOOracle();
			try {
				dao.insertOrder(info);
				//3_1) 저장 성공
				request.setAttribute("status", 1);
				//4) 장바구니 비우기
				session.removeAttribute("cart");
				
			} catch (AddException e) {
				e.printStackTrace();
				//3_2) 저장 실패!
				request.setAttribute("status", -1);
			}
			
			
		
		
		}
		
		
		
		String path = "addorderresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		// 1) 장바구니 내용 가져오기
	}

}
