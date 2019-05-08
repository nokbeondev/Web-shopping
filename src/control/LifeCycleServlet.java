package control;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
    public LifeCycleServlet() {
    	System.out.println("1.LifecycleServlet생성자호출됨");
		/*
		 * String dev = this.getServletContext().getInitParameter("dev");
		 * System.out.println("ServletContext매개변수 dev:" + dev);
		 */
    	
    	/*
		 * String charset = this.getInitParameter("charset");
		 * //request.setCharacterEncoding(charset);
		 * System.out.println("서블릿 매개변수 charset:" + charset);
		 */	   
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);//ServletContext객체와 연결
    	System.out.println("2.LifecycleServlet init()호출됨");


		String dev = this.getServletContext().getInitParameter("Nokbeondev");
		System.out.println("ServletContext매개변수 dev:" + dev);
 
    	
		String charset = this.getInitParameter("charset");
		//request.setCharacterEncoding(charset);
		System.out.println("서블릿 매개변수 charset:" + charset);
		 
	}

	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException {
	   	System.out.println("3.LifecycleServlet doGet()호출됨");

	   		
	   	//1)http://localhost:8888/day52/lifecycle.do?msg=hello 
 	    //2)http://localhost:8888/day52/lifecycle.do?msg=
	   	//3)http://localhost:8888/day52/lifecycle.do
	   	String msg = request.getParameter("msg");//1)hello, 2)'', 3)null
	   	if(msg != null) {
	   		System.out.println("요청전달데이터(요청매개변수)msg:" + msg +", 길이:" + msg.length());
	   	}
	}
	public void destroy() {
	   	System.out.println("4.LifecycleServlet destroy()호출됨");

	}

}