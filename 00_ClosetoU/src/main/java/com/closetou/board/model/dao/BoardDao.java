package com.closetou.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.closetou.article.model.vo.TradeArticle;
import static com.closetou.common.jdbc.JDBCTemplate.close;

public class BoardDao {

	@SuppressWarnings("null")
	public ArrayList<TradeArticle> searchTradeArticle(Connection connection, String keyword, String[] attribute) {

		// 변수 선언
		ArrayList<TradeArticle> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
//		String category = "";
		
		// 체크한 속성값에 따라 쿼리문을 변경
//		if ( attribute.length == 0 ) {
			// 키워드만 입력했을 때 제목에 키워드가 포함되는 게시글을 찾아줌
			query = "SELECT * FROM	ARTICLE WHERE TYPE=\"거래\" AND TITLE LIKE '%?%'";
//		}
			
		try {
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery(query);
			
			while(rs.next()) {
				
				TradeArticle ta = new TradeArticle();
				
				
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		
		
		
		
		
		
		
		
		return list;
	}

}
