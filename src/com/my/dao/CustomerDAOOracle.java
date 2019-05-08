package com.my.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;

public class CustomerDAOOracle implements CustomerDAO {
	public CustomerDAOOracle() throws ClassNotFoundException {
		//2)JDBC드라이버로드
  		Class.forName("oracle.jdbc.driver.OracleDriver");
  		System.out.println("JDBC드라이버 로드 성공");
	}
	
	@Override
	public void insert(Customer c)  throws AddException {
 		//3)DB연결
  		String url = "jdbc:oracle:thin:@localhost:1521:xe";
  		String user = "hr";
  		String password = "hr";
  		java.sql.Connection con = null;
  		try {
  			con =DriverManager.getConnection(url, user, password );
  			System.out.println("DB연결성공!");
  		} catch (SQLException e) {
  			e.printStackTrace();
  			throw new AddException(e.getMessage());
  		}
  		
  		String insertSQL = "INSERT INTO customer(id,pwd,name,buildingno,address) "
			        + " VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			//pstmt.setString(4, buildingno);
			pstmt.setString(4, "");
			pstmt.setString(5, c.getAddress());
			
			int rowcnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} 
		  		
		//6)DB연결닫기
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

	@Override
	public Customer selectById(String id) throws FindException{
				
		//3)DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		java.sql.Connection  con = null;
		try {
			con =DriverManager.getConnection(url, user, password );
			System.out.println("DB연결성공!");
		} catch (SQLException e) {
			e.printStackTrace();
			//return;
			throw new FindException(e.getMessage());
		}				
			
		String selectSQL = "SELECT * FROM customer"
				         + " WHERE id=?";
		
		PreparedStatement pstmt = null; // 송신 할 것 담는 변수
		java.sql.ResultSet rs = null;// 결과 수신한 것 담는 변수
		
		try {
			pstmt = con.prepareStatement(selectSQL);			
			pstmt.setString(1, id); // 첫번째물음표에 대입될 값 설정
			rs = pstmt.executeQuery(); // executeQuery() : 송신하고 수신 값을 리턴
			
			
			if(rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				Customer c = new Customer();
				c.setId(id);
				c.setPwd(pwd);
				c.setName(name);
				return c;
			}else {
				throw new FindException("아이디에 해당하는 고객이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//5)DB연결닫기
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@Override
	public List<Customer> selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}