package control;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JDBCServlet
 */
public class JDBCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2) JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC 드라이버 로드 성공!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		// 3) DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("success!!");
		}catch(SQLException e) {
			e.printStackTrace();
			return;
		}
		
		// 4) SQL 송신
		String selectSQL = "SELECT * FROM customer";
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null; // 결과 수신 받는 변수
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSQL); // SQL 송신 작업 -> rs로 수신
			//System.out.println(rs);
			
			rs.next(); // 검색결과 행 이동
			while(rs.next()) {
				// DB 자료형 (char, varchar2) -> getString으로 찾아 올 수 있다.
				// DB 자료형 (number) -> getInt()로 찾음
				// DB 자료형 (number) -> getFloat()로 찾음
				// DB 자료형 (date) -> getDate()로 찾음
				String id = rs.getString("id"); 
				String pwd = rs.getString("pwd"); 
				String name = rs.getString("name"); 
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String buildingno = rs.getString("buildingno");
				System.out.println(id+":"+pwd+":"+name+":"+gender+":"+address+":"+buildingno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 6) DB 연결닫기 (try-catch는 각각 해주는 것이 좋다. 메모리 누수 발생 우려.)
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
