package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.PostDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Post;

/**
 * Servlet implementation class SearchPostServlet
 */
public class SearchPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		//out.print("3611011000103070019000001, 30114, 세종특별자치시 도움5로 19 (어진동, 우정사업본부)");
		String doro = request.getParameter("doro");
		System.out.println(doro);
		
		PostDAOOracle dao = new PostDAOOracle();
		try {
			List <Post> list = dao.selectByDoro(doro);
			for(Post p : list) {
				//System.out.println(p); // p.toString() 자동 호출 된다.
			}
			request.setAttribute("status", 1);
			request.setAttribute("postlist", list);
			
		} catch (FindException e) {
			request.setAttribute("status", -1);
			e.printStackTrace();
		}
		String path = "searchpostresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}


}














