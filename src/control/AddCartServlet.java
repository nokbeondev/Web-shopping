package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.vo.Product;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String cnt = request.getParameter("cnt");
		
		HttpSession session = request.getSession();
		// 세션의 속성 찾기 (속성명 : cart)
		Map <Product, Integer> cart = (Map) session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		Product p = new Product();
		p.setProd_no(no);
		//cart.put(p, Integer.parseInt(cnt));
		//수량 누적 작업
		//cart에서 상품에 해당 수량 얻기
		
		/* 이렇게 오토 언박싱으로 oldCnt를 받아올 수도 있겠지만 만일 가져온 값이 null일 경우 에러 난다.
		 * 따라서 오토 언박싱보다는 직접 언박싱해야한다.
		int oldCnt = (Integer) cart.get(p);
		*/
		
		int iCnt = Integer.parseInt(cnt);
		Integer inte = (Integer) cart.get(p);
		if(inte != null) {
			iCnt += inte; // 수량누적
		}
		cart.put(p, iCnt);
		
		String path = "addcartresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
		
	}
}
