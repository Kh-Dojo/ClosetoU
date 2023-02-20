package com.closetou.board.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;

public class BoardDao {

	@SuppressWarnings("null")
	public ArrayList<TradeArticle> searchTradeArticle(Connection connection, String keyword, String[] attribute) {

		// 변수 선언
		ArrayList<TradeArticle> trlist = new ArrayList<>();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "";
//		String category = "";

		// 체크한 속성값에 따라 쿼리문을 변경
//		if ( attribute.length == 0 ) {
		// 키워드만 입력했을 때 제목에 키워드가 포함되는 게시글을 찾아줌
//		query = "SELECT * FROM	ARTICLE WHERE TYPE=\"거래\" AND TITLE LIKE '%?%'";
//
//
//		try {
//			pstmt.setString(1, keyword);
//
//			rs = pstmt.executeQuery(query);
//
//			while (rs.next()) {
//
//				TradeArticle ta = new TradeArticle();
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//

		for (int i = 0; i < 3; i++) {

			TradeArticle rs = new TradeArticle();

			rs.setNo(i);
			rs.setPrice(0);
			rs.setClothInfo("의류정보");
			rs.setTradeEnd(false);
			rs.setFree(true);
			rs.setTradeMethod("직거래");
			rs.setLocation("서울");

			trlist.add(rs);

		}

		return trlist;
	}

	public ArrayList<Article> searchArticle(Connection connection, String keyword) {
		ArrayList<Article> result = new ArrayList<>();
		
		System.out.println("searchArticle dao 실행");
		

		// DB와 연결해서 쿼리문, 결과값(rs)을 가져오는 메소드 필요

		// 임의의 rs 생성

		for (int i = 0; i < 3; i++) {

			Article rs = new Article();

			rs.setNo(i);
			rs.setUserNo(1);
			rs.setType("거래");
			rs.setTitle("거래ex" + i);
			rs.setContent("거래내용" + i);
			rs.setReadCount(0);
			rs.setVisable("Y");
			rs.setPostDate(new Date());
			rs.setEdited(null);
			rs.setEditDate(null);

			result.add(rs);

		}

		return result;
	}

}
