package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.my.dao.ProductDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Product;

public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductDetailServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		try {
			// 모델 호출
			ProductDAOOracle dao = new ProductDAOOracle();
			
			Product product = dao.selectByNo(no);		
			System.out.println(product);
			// 요청 속성으로 추가
			request.setAttribute("product", product); // JSP로 넘겨 줄 정보를 여기에 저장
			// 선택된 jsp 경로로 포워드
			String path = "productdetailresult.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}catch(Exception e) {
			
		}
	}
}
