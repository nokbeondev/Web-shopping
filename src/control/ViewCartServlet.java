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

import com.my.dao.ProductDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Product;

public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession( );
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		Map<Product, Integer> rcart = new HashMap<>();
		
		request.setAttribute("rcart", rcart);
		try {
		ProductDAOOracle dao = 
		                  new ProductDAOOracle( );
			for(Product p: cart.keySet()){
			      try {
					Product p1 = dao.selectByNo(p.getProd_no());
					int cnt = (Integer)cart.get(p);
					rcart.put(p1,  cnt);
			      } catch (FindException e) {
					e.printStackTrace();
				}			      
			}
			String path = "viewcartresult.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}catch(ClassNotFoundException e) {
			
		}
	}

}
