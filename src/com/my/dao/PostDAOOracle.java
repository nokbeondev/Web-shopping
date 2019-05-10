package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.FindException;
import com.my.vo.Post;

public class PostDAOOracle implements PostDAO {
	
	@Override
	public void insert(Post post) {
		
	}

	@Override
	public List<Post> selectByDoro(String doro) throws FindException{
		List<Post> list = new ArrayList<>();
		Connection con = null;
		//3)SQL문 송신
		PreparedStatement pstmt = null;
		//4)결과 수신
		ResultSet rs = null;
		String sql = "SELECT zipcode"
				+ ",sido"
				+ ",sigungu"
				+ ",doro"
				+ ",building1"
				+ ",DECODE(building2, '0',' ', '-'||building2) building2"
				+ ",dong"
				+ ",NVL2(building, ', '|| building, ' ') building"
				+ ",buildingno"
				+ " FROM post"
				+ " WHERE  doro || building1 || building2   LIKE ?"
				+ " ORDER BY "
				+ " sido||' '||sigungu||' '||doro||" 
				+ " ' '||building1||building2"
				+ ",'('||dong||building ||')'";
		try {
			//3)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
	  		String user = "hr";
	  		String password = "hr";
	  		try {
	  			con =DriverManager.getConnection(url, user, password );
	  			System.out.println("DB연결성공!");
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  			throw new FindException(e.getMessage());
	  		}
	  		pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+doro+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Post p = new Post();
				p.setZipcode(rs.getString("zipcode"));
				p.setSido(rs.getString("sido"));
				p.setSigungu(rs.getString("sigungu"));
				p.setDoro(rs.getString("doro"));
				p.setBuilding1(rs.getString("building1"));
				p.setBuilding2(rs.getString("building2"));
				p.setDong(rs.getString("dong"));
				p.setBuilding(rs.getString("building"));
				p.setBuildingno(rs.getString("buildingno"));
				list.add(p);
			}
			if(list.size() == 0) {
				throw new FindException("검색결과가 없습니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
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
		return list;
	}
}
