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
			rs.setTradeEnd("N");
			rs.setFree("N");
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
				trart.setTradeEnd("TRADE_ENDED");
				trart.setFree("FREE");
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
		String query = "SELECT "
				+ "    RNUM, NO, USER_NO, TYPE, TITLE, CONTENT, READ_COUNT, VISABLE, POST_DATE, EDITED, EDIT_DATE "
				+ "FROM" 
				+ "    (SELECT " 
				+ "        ROWNUM AS RNUM," 
				+ "        NO," 
				+ "        USER_NO,"
				+ "        TYPE," 
				+ "        TITLE," 
				+ "        CONTENT," 
				+ "        READ_COUNT," 
				+ "        VISABLE,"
				+ "        POST_DATE," 
				+ "        EDITED," 
				+ "        EDIT_DATE " 
				+ "    FROM " 
				+ "        ARTICLE "
				+ "    WHERE" 
				+ "        TYPE IN ('거래') " 
				+ "    ORDER BY " 
				+ "        NO DESC) " 
				+ "WHERE "
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
				tart.setReadCount(rs.getInt("READ_COUNT"));
				tart.setVisable(rs.getString("VISABLE"));
				tart.setPostDate(rs.getDate("POST_DATE"));
				tart.setEdited(rs.getString("EDITED"));
				tart.setEditDate(rs.getDate("EDIT_DATE"));

				artlist.add(tart);
			}

			System.out.println(artlist);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return artlist;
	}

	// 문의글 DB에 넣기
	public int insertQna(Connection connection, Article article) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "INSERT INTO ARTICLE VALUES(SEQ_ARTICLE_NO.NEXTVAL, NULL, ?, NULL, NULL, '문의', ?, ?, DEFAULT, DEFAULT, DEFAULT, NULL, NULL)";

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, article.getNo());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	// 게시판에서 제목 클릭하면 상세 페이지 나오게 만들기
	// 상세 게시글에 들어갈 값 불러오는 메소드
	public Article findArticleByNoForCommunity(Connection connection, int no) {
		
		Article article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT A.NO, A.TYPE, A.TITLE, M.NICKNAME, A.CONTENT, A.READ_COUNT, A.ORIGINAL_FILENAME, A.RENAMED_FILENAME, A.POST_DATE, A.EDITED, A.EDIT_DATE "
				+ "FROM ARTICLE A "
				+ "JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "WHERE A.VISABLE = 'Y' AND TYPE IN('공지', '자유') AND A.NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new Article();
				
				article.setNo(rs.getInt("NO"));
				article.setType(rs.getString("TYPE"));
				article.setTitle(rs.getString("TITLE"));
				article.setUserNickname(rs.getString("NICKNAME"));
				article.setContent(rs.getString("CONTENT"));
				article.setReadCount(rs.getInt("READ_COUNT"));
				article.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				article.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				article.setPostDate(rs.getDate("POST_DATE"));
				article.setEdited(rs.getString("EDITED"));
				article.setEditDate(rs.getDate("EDIT_DATE"));
//// 230216 5교시 댓글 조회
//				// dao에서 게시글 상세 조회를 할 때 그 게시글에 관련된 댓글까지 조회될 수 있게 수정
//				board.setReplies(this.getRepliesByNo(connection, no));
//				board.setCreateDate(rs.getDate("CREATE_DATE"));
//				board.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}

	public List<TradeArticle> findTradeArticleByNos(Connection connection, ArrayList<Integer> numbers) {
		List<TradeArticle> trarts = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM TRADE_ARTICLE WHERE ARTICLE_NO IN (";
		
		for(int i = 0 ; i < numbers.size()-1 ; i++ ) {
			query += (numbers.get(i) +", "); 
		}
		query += numbers.get(numbers.size()-1);
		query += ")";
		
		System.out.println(query);

		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TradeArticle trart = new TradeArticle();

				trart.setNo(rs.getInt("ARTICLE_NO"));
				trart.setClothNumber(rs.getInt("CLOTH_NO"));
				trart.setPrice(rs.getInt("PRICE"));
				trart.setClothInfo(rs.getString("CLOTH_INFO"));
				trart.setTradeEnd("TRADE_ENDED");
				trart.setFree("FREE");
				trart.setTradeMethod(rs.getString("TRADE_METHOD"));
				trart.setLocation(rs.getString("LOCATION"));
			
				trarts.add(trart);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return trarts;

	}
	
	// 수정내용으로 게시글 바뀌게 UPDATE 작업 article service의save()
	public int updateBoard(Connection connection, Article article) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE ARTICLE SET TITLE=?, CONTENT=?, ORIGINAL_FILENAME=?, RENAMED_FILENAME=?, EDIT_DATE=SYSDATE, TYPE=? WHERE NO=?";
						
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getOriginalFileName());
			pstmt.setString(4, article.getRenamedFileName());
			pstmt.setString(5, article.getType());
			pstmt.setInt(6, article.getUserNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
	}
	
	// 수정내용으로 게시글 바뀌게 insert 작업 article service의save()
	public int insertBoard(Connection connection, Article article) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO ARTICLE VALUES(SEQ_ARTICLE_NO.NEXTVAL, NULL, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, SYSDATE)";
		
		try {
			pstmt = connection.prepareStatement(query);	// query문 받을 prepareStatement 생성
			
			pstmt.setInt(1, article.getUserNo());	// 유저넘버
			pstmt.setString(2, article.getOriginalFileName());	// 파일네임
			pstmt.setString(3, article.getRenamedFileName());	// 리네임드파일네임
			pstmt.setString(4, article.getType());	//타입
			pstmt.setString(5, article.getTitle());	//타이틀
			pstmt.setString(6, article.getContent());//내용
			pstmt.setString(7, article.getContent());// 수정내용
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
