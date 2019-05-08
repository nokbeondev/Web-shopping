package com.my.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.FindException;
import com.my.vo.Product;

public class ProductDAOOracle implements ProductDAO { // DB와의 연결은 DAO에서 끝내야한다.(Servlet으로 DB와의 일을 넘기지말아야함!)

	public ProductDAOOracle() throws ClassNotFoundException{
		// 1) JDBC 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	
	@Override
	public Product selectByNo(String no) throws FindException {
		// 2) DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectByNoSQL = "SELECT * FROM product WHERE prod_no = ?";
		PreparedStatement pstmt = null;
		java.sql.ResultSet rs = null;
		try {
	
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			Product product;
			if(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_mfd = rs.getString("prod_mfd");
				product = new Product(prod_no, prod_name, prod_price, prod_mfd); // 해당 번호의 상품을 담는다.
				return product;
			}
			throw new FindException("상품이 존재하지 않습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Product> selectByName(String s) throws FindException {
		return null;
	}

	@Override
	public List<Product> selectAll() throws FindException {
 		//2)DB연결
  		String url = "jdbc:oracle:thin:@localhost:1521:xe";
  		String user = "hr";
  		String password = "hr";
  		java.sql.Connection con = null;
  		try {
  			con = DriverManager.getConnection(url, user, password);
  			System.out.println("DB연결성공!");
  		} catch (SQLException e) {
  			e.printStackTrace();
  			throw new FindException(e.getMessage());
  		}
  		
  		String selectAllSQL = "SELECT * FROM product ORDER BY prod_no ASC"; // 문자열 안에 세미콜론 들어가면 안된다! 		
  		PreparedStatement pstmt = null; // 송신 할 것 담는 변수
  		java.sql.ResultSet rs = null; // 수신 한 것 담는 변수

		try {
			pstmt = con.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery(); // 여기서 송수신 다 함 : 3)SQL 송신 -> 4) 결과 수신
			List<Product> productList = new ArrayList();

			while(rs.next()){ // rs.next() -> 커서를 다음 행으로 내린다. 한 줄 씩 내려가며 스캔, 다음 행이 있으면 true 리턴
				String prod_no = rs.getString("prod_no");//getString("컬럼명(대소문자 구분 없음)");은 해당 컬럼의 값을 가져온다.
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_mfd = rs.getString("prod_mfd");
				productList.add(new Product(prod_no, prod_name, prod_price, prod_mfd));
			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally { // 예외가 발생하던 안 하던 닫아야하므로 finally에 넣는다.
			//5)DB연결닫기
			try {
				pstmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}
}
