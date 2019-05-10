package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAOOracle implements OrderDAO {
	
	
	@Override
	public void insertOrder(OrderInfo info) throws AddException{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
  		String user = "hr";
  		String password = "hr";
  		java.sql.Connection  con = null;
  		try {
  			con =DriverManager.getConnection(url, user, password );
  			System.out.println("DB연결성공!");
  		
			//트랜잭션 자동완료를 해제
			con.setAutoCommit(false);
			
			insertInfo(con, info);
			insertLine(con, info);
			con.commit();//완료
		} catch (SQLException e) {
			e.printStackTrace();
			if(con != null) { 
				try {
					con.rollback();//복구 - InfoTable 혹은 LineTable에 자료 추가할 때 오류 나면 롤백
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			throw new AddException(e.getMessage());
		}finally {			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	private void insertInfo(Connection con, OrderInfo info) 
			throws SQLException {
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO orderInfo(order_no, order_id)  "
				+ "VALUES (order_seq.NEXTVAL,       ?)"; 
		
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, info.getCustomer().getId());
		pstmt.executeUpdate();		
	}
	
	private void insertLine(Connection con, OrderInfo info) 
			throws SQLException {
		PreparedStatement pstmt = null;
		String insertLineSQL = 
"INSERT INTO order_line(order_no, order_prod_no, order_quantity)"+
" VALUES (order_seq.CURRVAL, ?,  ?)";	
		pstmt = con.prepareStatement(insertLineSQL);
		
		List<OrderLine>lines = info.getOrder_lines();
		for(OrderLine line: lines) {
			pstmt.setString(1, line.getProduct().getProd_no());
			pstmt.setInt(2, line.getOrder_quantity());
			pstmt.addBatch();//일괄처리 하기 위해 쌓아두기
		}
		pstmt.executeBatch();//일괄처리 하기
	}
	
	private void insertLine(Connection con, OrderLine line) 
			throws SQLException {
		PreparedStatement pstmt = null;
		String insertLineSQL = 
"INSERT INTO order_line(order_no, order_prod_no, order_quantity)"+
" VALUES (order_seq.CURRVAL, ?,  ?)";
//"VALUES order_seq.CURRVAL, ?,  ?)";		
		
		pstmt = con.prepareStatement(insertLineSQL);
		pstmt.setString(1, line.getProduct().getProd_no());
		pstmt.setInt(2, line.getOrder_quantity());
		pstmt.executeUpdate();
		
	}
	

	@Override
	public List<OrderInfo> selectAll() {
		List<OrderInfo> all = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectALLSQL = 
"SELECT info.order_no, order_id, c.name" + 
", order_dt" + 
", order_prod_no, p.prod_name, p.prod_price" + 
", order_quantity" + 
" FROM orderInfo info " + 
" JOIN order_line line ON info.order_no = line.order_no" + 
" JOIN customer c ON info.order_id = c.id" + 
" JOIN product p ON line.order_prod_no = p.prod_no" + 
" ORDER BY order_no DESC, order_prod_no";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
  		String user = "hr";
  		String password = "hr";
  		try {
  			con =DriverManager.getConnection(url, user, password );
  			System.out.println("DB연결성공!");
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			OrderInfo info = null;
			List<OrderLine> order_lines = null;
			int  old_order_no = 0;
			while(rs.next()) {		
				int order_no = rs.getInt("order_no");
				String order_id = rs.getString("order_id");
				String name = rs.getString("name");//				
				java.sql.Date order_dt = rs.getDate("order_dt");//
				String order_prod_no = rs.getString("order_prod_no");//
				String prod_name = rs.getString("prod_name");//
				int prod_price = rs.getInt("prod_price");//
				int order_quantity = rs.getInt("order_quantity");//
				if(old_order_no != order_no) {
					info = new OrderInfo();
					info.setOrder_no(order_no);
					info.setOrder_date(order_dt);
					Customer c = new Customer();
					c.setId(order_id); 
					c.setName(name);
					info.setCustomer(c);
					
					order_lines = new ArrayList<>();
					info.setOrder_lines(order_lines);
					all.add(info);
					old_order_no = order_no;					
				}
				OrderLine line = new OrderLine();
				line.setOrder_no(order_no);
				line.setOrder_quantity(order_quantity);
				Product p = new Product();
				p.setProd_no(order_prod_no);
				p.setProd_name(prod_name);
				p.setProd_price(prod_price);
				line.setProduct(p);
				
				order_lines.add(line);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//6)DB연결닫기
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
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
		return all;
	}

	@Override
	public List<OrderInfo> selectById(String id) {
		List<OrderInfo> all = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectALLSQL = 
"SELECT info.order_no, order_id, c.name" + 
", order_dt" + 
", order_prod_no, p.prod_name, p.prod_price" + 
", order_quantity" + 
" FROM orderInfo info " + 
" JOIN order_line line ON info.order_no = line.order_no" + 
" JOIN customer c ON info.order_id = c.id" + 
" JOIN product p ON line.order_prod_no = p.prod_no" + 
" WHERE order_id=?" + 
" ORDER BY order_no DESC, order_prod_no";
		try {
			//3)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
	  		String user = "hr";
	  		String password = "hr";
	  		con =DriverManager.getConnection(url, user, password);
	  		System.out.println("DB연결성공!");
	  		
			pstmt = con.prepareStatement(selectALLSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			OrderInfo info = null;
			List<OrderLine> order_lines = null;
			int  old_order_no = 0;
			while(rs.next()) {		
				int order_no = rs.getInt("order_no");
				String order_id = rs.getString("order_id");
				String name = rs.getString("name");//				
				java.sql.Date order_dt = rs.getDate("order_dt");//
				String order_prod_no = rs.getString("order_prod_no");//
				String prod_name = rs.getString("prod_name");//
				int prod_price = rs.getInt("prod_price");//
				int order_quantity = rs.getInt("order_quantity");//
				if(old_order_no != order_no) {
					info = new OrderInfo();
					info.setOrder_no(order_no);
					info.setOrder_date(order_dt);
					Customer c = new Customer();
					c.setId(order_id); 
					c.setName(name);
					info.setCustomer(c);
					
					order_lines = new ArrayList<>();
					info.setOrder_lines(order_lines);
					all.add(info);
					old_order_no = order_no;					
				}
				OrderLine line = new OrderLine();
				line.setOrder_no(order_no);
				line.setOrder_quantity(order_quantity);
				Product p = new Product();
				p.setProd_no(order_prod_no);
				p.setProd_name(prod_name);
				p.setProd_price(prod_price);
				line.setProduct(p);
				
				order_lines.add(line);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//6)DB연결닫기
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
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
		return all;
	}
}