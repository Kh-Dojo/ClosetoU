package com.closetou.article.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.Reply;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.common.util.PageInfo;

public class ArticleDao {


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

		List<Article> artlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
				+ "    * "
				+ "FROM "
				+ "    ( "
				+ "        SELECT "
				+ "            ROWNUM AS RNUM, "
				+ "            NO, "
				+ "            USER_NO, "
				+ "            TYPE, "
				+ "            TITLE, "
				+ "            CONTENT, "
				+ "            READ_COUNT, "
				+ "            VISABLE, "
				+ "            POST_DATE, "
				+ "            EDITED, "
				+ "            EDIT_DATE "
				+ "        FROM "
				+ "            ( "
				+ "                SELECT "
				+ "                    ROWNUM AS RNUM, "
				+ "                    NO, "
				+ "                    USER_NO, "
				+ "                    TYPE, "
				+ "                    TITLE, "
				+ "                    CONTENT, "
				+ "                    READ_COUNT, "
				+ "                    VISABLE, "
				+ "                    POST_DATE, "
				+ "                    EDITED, "
				+ "                    EDIT_DATE "
				+ "                FROM "
				+ "                    ARTICLE "
				+ "                WHERE "
				+ "                    TYPE IN ( '거래' ) "
				+ "                ORDER BY "
				+ "                    NO DESC "
				+ "            ) "
				+ "    ) "
				+ "WHERE RNUM BETWEEN ? AND ?";

		try {
			pstmt = connection.prepareStatement(query);

			System.out.println(pageInfo.getStartList());
			System.out.println(pageInfo.getEndList());
			
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



	public List<TradeArticle> findTradeArticleByNos(Connection connection, ArrayList<Integer> numbers) {
		List<TradeArticle> trarts = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM TRADE_ARTICLE WHERE ARTICLE_NO IN (";
		
		for(int i = 0 ; i < numbers.size()-1 ; i++ ) {
			query += (numbers.get(i) +", "); 
		}
		query += numbers.get(numbers.size()-1);
		query += ") ORDER BY ARTICLE_NO DESC";
		
		System.out.println(query);
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TradeArticle trart = new TradeArticle();

				trart.setNo(rs.getInt("ARTICLE_NO"));
				trart.setClothNumber(rs.getInt("CLOTH_NO"));
				trart.setPrice(rs.getInt("PRICE"));
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
	
	// 가장 최근에 작성한 Article의 No를 가져오는 메소드
	public int getMostRecentlyArticleNoByMemberNo(Connection connection, int no) {
		int recentNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
				+ "    MAX(NO) "
				+ "FROM "
				+ "    ( "
				+ "        SELECT "
				+ "            NO "
				+ "        FROM "
				+ "            ARTICLE "
				+ "        WHERE"
				+ "            USER_NO = ?"
				+ "    )";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				recentNo = rs.getInt("MAX(NO)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return recentNo;
	}

	public int saveTradeArticle(Connection connection, TradeArticle trart) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TRADE_ARTICLE ( "
				+ "    ARTICLE_NO, "
				+ "    CLOTH_NO, "
				+ "    PRICE, "
				+ "    TRADE_ENDED, "
				+ "    FREE, "
				+ "    TRADE_METHOD, "
				+ "    LOCATION "
				+ ") VALUES ( "
				+ "    ?, "
				+ "    ?, "
				+ "    ?,  "
				+ "    DEFAULT, "
				+ "    ?, "
				+ "    ?, "
				+ "    ? "
				+ ")";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, trart.getNo());
			pstmt.setInt(2, trart.getClothNumber());
			pstmt.setInt(3, trart.getPrice());
			pstmt.setString(4, trart.getFree());
			pstmt.setString(5, trart.getTradeMethod());
			pstmt.setString(6, trart.getLocation());
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

//	----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	

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

// 230216 5교시 댓글 조회
				// dao에서 게시글 상세 조회를 할 때 그 게시글에 관련된 댓글까지 조회될 수 있게 수정
				article.setReplies(this.getRepliesByNoForCommunity(connection, no));
				article.setPostDate(rs.getDate("POST_DATE"));
				article.setEditDate(rs.getDate("EDIT_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}
	
	// 댓글 조회 리스트만들기
	public List<Reply> getRepliesByNoForCommunity (Connection connection, int no) {
		List<Reply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT R.NO, "
							+ "R.ARTICLE_NO, "
							+ "R.CONTENT, "
							+ "M.NICKNAME, "
							+ "R.COMMENT_DATE, "
							+ "R.EDIT_DATE "
					 + "FROM REPLY R "
					 + "JOIN MEMBER M ON(R.ID_NO = M.NO) "
					 + "WHERE R.VISABLE='Y' AND ARTICLE_NO=? "
					 + "ORDER BY R.NO DESC";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setNo(rs.getInt("NO"));
				reply.setArticleNo(rs.getInt("ARTICLE_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setUserNickname(rs.getString("NICKNAME"));
				reply.setCommentDate(rs.getDate("COMMENT_DATE"));
				reply.setEditDate(rs.getDate("EDIT_DATE"));
				
				replies.add(reply);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replies;
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
			pstmt.setInt(6, article.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
	}
	
	// 게시글 바뀌게 insert 작업 article service의save()
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

	public int updateReadCount(Connection connection, Article article) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE ARTICLE SET READ_COUNT=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			article.setReadCount(article.getReadCount() + 1);		// 기존 readCount에 1 더해서 그 값을 받음
			
			pstmt.setInt(1, article.getReadCount());
			pstmt.setInt(2, article.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

	
	// 게시글 삭제
	public int updateStatus(Connection connection, int no, String visable) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE ARTICLE SET VISABLE=? WHERE NO=?";
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, visable);	// db의 상태값 Y N 
			pstmt.setInt(2, no);
		
			// 영향받은 행의 개수를 result로 리턴
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);	// pstmt 변수 선언 try 구문 안에서 하지 않는 이유 try 구문이 끝나면 변수 소멸됨 close(pstmt)를 못해줌
		}
		
		return result;
		
		//웹에서는 삭제되나 DB에서는 행이 삭제되지 않고 STATUS 값을 N으로 바꿔줌
	}
	
	// 댓글 등록
	public int insertReply(Connection connection, Reply reply) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, 'N', DEFAULT, NULL, NULL)";
		
		try {
			pstmt = connection.prepareStatement(query);

			// 쿼리 수행 전 물음표 값 넣기
			pstmt.setInt(1, reply.getArticleNo());
			pstmt.setInt(2, reply.getUserNo());
			pstmt.setString(3, reply.getContent());
			
			
			// 쿼리문 수행해서 그 값을 result에 담아줌
							// executeQuery > SELECT 구문 수행할 때 사용. SELECT문 수행하면 resultSet 수행.
							// executeUpdate > INSERT, UPDATE, DELETE 수행할 때 사용
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyStatus(Connection connection, int articleNo, int replyNo, String visable) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE REPLY SET VISABLE=? WHERE NO=? AND ARTICLE_NO=?";
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, visable);	// db의 상태값 Y N 
			pstmt.setInt(2, replyNo);
			pstmt.setInt(3, articleNo);
		
			// 영향받은 행의 개수를 result로 리턴
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);	// pstmt 변수 선언 try 구문 안에서 하지 않는 이유 try 구문이 끝나면 변수 소멸됨 close(pstmt)를 못해줌
		}
		
		return result;
		
		//웹에서는 삭제되나 DB에서는 행이 삭제되지 않고 STATUS 값을 N으로 바꿔줌
	}

	
	
}
