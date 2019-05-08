package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.ProductDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Product;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductListServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 모델 호출
			ProductDAOOracle dao = new ProductDAOOracle();
			List<Product> all = dao.selectAll();
			
			// 요청 속성으로 추가
			request.setAttribute("all", all); // JSP로 넘겨줄 정보를 여기에 저장
			// 선택된 jsp 경로로 포워드
			// 아래 주석 부분은 JSP에게 넘길 것이다!
			String path = "productlistresult.jsp";
			RequestDispatcher rd =  request.getRequestDispatcher(path);
			rd.forward(request, response); 
			
			/*아래 주석 처리 된 것은 JSP가 해야 할 일이다.*/
//			out.print("<table>");
//			out.print("<tr><th>상품번호</th><th>상품명</th><th>가격</th><th>날짜</th></tr>");	
//			// 이것을 localhost:8888/day62/productlist.do로 하면 안된다. 이것은 get방식의 요청이다.
//			for(int i=0; i<all.size(); i++) {
//				//System.out.println(all.get(i));
//				//out.print(all.get(i));
//				out.print("<tr>");
//				out.print("<td>");	out.print(all.get(i).getProd_no());	out.print("</td>");
//				out.print("<td>");	out.print(all.get(i).getProd_name());	out.print("</td>");
//				out.print("<td>");	out.print(all.get(i).getProd_price());	out.print("</td>");
//				out.print("<td>");	out.print(all.get(i).getProd_mfd());	out.print("</td>");
//				out.print("</tr>");
//			}
//			out.print("</table>");
			
		}catch(ClassNotFoundException e) {
			
		}catch(FindException e) {
			
		}
		
	}

}
