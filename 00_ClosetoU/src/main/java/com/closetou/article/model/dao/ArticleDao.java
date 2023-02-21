package com.closetou.article.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.common.util.PageInfo;

public class ArticleDao {

	// 키워드로 Article을 검색하는 메소드(미완성)
	public List<Article> searchArticle(Connection connection, String keyword) {
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

	// 키워드로 TradeArticle을 검색하는 메소드(미완성)
	public List<TradeArticle> searchTradeArticle(Connection connection, String keyword, String[] attribute) {

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

	// article의 no값으로 tradeArticle을 찾아 객체로 반환하는 메소드
	public TradeArticle findTradeArticleByNo(Connection connection, int no) {
		TradeArticle trart = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM TRADE_ARTICLE WHERE ARTICLE_NO = ?";

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				trart = new TradeArticle();

				trart.setNo(rs.getInt("ARTICLE_NO"));
				trart.setClothNumber(rs.getInt("CLOTH_NO"));
				trart.setPrice(rs.getInt("PRICE"));
				trart.setClothInfo(rs.getString("CLOTH_INFO"));

				if (rs.getString("TRADE_ENDED").equals("N")) {
					trart.setTradeEnd(false);
				} else {
					trart.setTradeEnd(true);
				}
				if (rs.getString("FREE").equals("N")) {
					trart.setFree(false);
				} else {
					trart.setFree(true);
				}

				trart.setTradeMethod(rs.getString("TRADE_METHOD"));
				trart.setLocation(rs.getString("LOCATION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return trart;
	}

	// 종류가 '거래'인 Article을 가져오는 메소드
	public List<Article> findAllArticlesForTrade(Connection connection, PageInfo pageInfo) {

		System.out.println("findAllArticlesForTrade dao 실행");

		List<Article> artlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT"
				+ "    RNUM, NO, USER_NO, TYPE, TITLE, CONTENT, READCOUNT, VISABLE, POST_DATE, EDITED, EDIT_DATE\r\n"
				+ "FROM"
				+ "    (SELECT"
				+ "        ROWNUM AS RNUM,"
				+ "        NO,"
				+ "        USER_NO,"
				+ "        TYPE,"
				+ "        TITLE,"
				+ "        CONTENT,"
				+ "        READCOUNT,"
				+ "        VISABLE,"
				+ "        POST_DATE,"
				+ "        EDITED,"
				+ "        EDIT_DATE"
				+ "    FROM"
				+ "        ARTICLE"
				+ "    WHERE"
				+ "        TYPE IN ('거래')"
				+ "    ORDER BY"
				+ "        NO DESC)"
				+ "WHERE"
				+ "    RNUM BETWEEN ? AND ?";

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article tart = new Article();

				tart.setNo(rs.getInt("NO"));
				tart.setUserNo(rs.getInt("USER_NO"));
				tart.setType(rs.getString("TYPE"));
				tart.setTitle(rs.getString("TITLE"));
				tart.setContent(rs.getString("CONTENT"));
				tart.setReadCount(rs.getInt("READCOUNT"));
				tart.setVisable(rs.getString("VISABLE"));
				tart.setPostDate(rs.getDate("POST_DATE"));
				tart.setEdited(rs.getString("EDITED"));
				tart.setEditDate(rs.getDate("EDIT_DATE"));

				artlist.add(tart);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return artlist;
	}

}
