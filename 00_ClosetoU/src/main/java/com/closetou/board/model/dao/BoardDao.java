package com.closetou.board.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {

	// 쿼리문의 BOARD 자리를 손보면 될 것 같은데 조회되는 결과 값을 기준으로 페이징
	public int getBoardCount(Connection connection) {
		
		int count = 0;
		String query = "SELECT"
				+ "    COUNT(*)"
				+ "FROM"
				+ "    ARTICLE"
				+ "WHERE"
				+ "    TYPE = '거래'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return count;
	}

	
	

}
