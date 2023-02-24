package com.closetou.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.Reply;
import com.closetou.common.util.PageInfo;
import com.closetou.member.model.vo.Member;

public class MemberDao {

	public Member findMemberById(Connection connection, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE USER_ID=? AND STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("USER_ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("USER_NAME"));
				member.setNickname(rs.getString("NICKNAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	public int updatePwd(Connection connection, int no, String userPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET PASSWORD=? WHERE NO=?";
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, userPwd);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;	
	}

	public int updateMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET USER_NAME=?, NICKNAME=?, PHONE=?, EMAIL=?, ADDRESS=?, ADDRESS_DETAIL=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getAddress_detail());
			pstmt.setInt(7, member.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	public int insertMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL,?,?,DEFAULT,?,?,?,?,?,?,DEFAULT,DEFAULT)";
		
		try {					
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getAddress_detail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET STATUS=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 거래 글 전체 조회
	public List<Article> findAllArticlesForTrade(Connection connection, PageInfo pageInfo, int memNo) {
		List<Article> artlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article = new Article();

		String query = "SELECT ROWNUM, USER_NO, TITLE, NICKNAME, POST_DATE, READ_COUNT, VISABLE, TYPE "
				+ "FROM (SELECT NO, "
				+ "                       USER_NO, "
				+ "                       TITLE, "
				+ "                       NICKNAME, "
				+ "                       POST_DATE, "
				+ "                       READ_COUNT, "
				+ "                       VISABLE, "
				+ "                       TYPE  "
				+ "FROM (SELECT A.NO, "
				+ "             A.USER_NO, "
				+ "             A.TITLE, "
				+ "			 M.NICKNAME, "
				+ "			 A.POST_DATE, "
				+ "			 A.READ_COUNT, "
				+ "			 A.VISABLE, "
				+ "             A.TYPE "
				+ "FROM ARTICLE A JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "WHERE A.VISABLE = 'Y' AND TYPE IN ('거래') ORDER BY A.NO DESC)) "
				+ "WHERE NO BETWEEN 1 and 1000 and USER_NO=?";

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();
			
			// 반복문
			while (rs.next()) {
				Article tart = new Article();
				
				tart.setRowNum(rs.getInt("ROWNUM"));
				tart.setNo(rs.getInt("NO"));
				tart.setUserNo(rs.getInt("USER_NO"));
				tart.setTitle(rs.getString("TITLE"));
				tart.setUserNickname(rs.getString("NICKNAME"));
				tart.setPostDate(rs.getDate("POST_DATE"));
				tart.setReadCount(rs.getInt("READ_COUNT"));
				tart.setVisable(rs.getString("VISABLE"));
				tart.setType(rs.getString("TYPE"));

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
	
	// 자유게시판 자유 게시글 목록 가져와 리스트에 담기
	public List<Article> findAllArticleForCommunity(Connection connection, PageInfo pageInfo, int memNo) {
		List<Article> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String query = "SELECT ROWNUM, NO, USER_NO, TITLE, NICKNAME, POST_DATE, READ_COUNT, VISABLE, TYPE "
				+ "FROM (SELECT 		  NO, "
				+ "                       USER_NO, "
				+ "                       TITLE, "
				+ "                       NICKNAME, "
				+ "                       POST_DATE, "
				+ "                       READ_COUNT, "
				+ "                       VISABLE, "
				+ "                       TYPE  "
				+ "FROM (SELECT A.NO,"
				+ "				A.USER_NO, "
				+ "             A.TITLE, "
				+ "			 M.NICKNAME, "
				+ "			 A.POST_DATE, "
				+ "			 A.READ_COUNT, "
				+ "			 A.VISABLE, "
				+ "             A.TYPE "
				+ "FROM ARTICLE A JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "WHERE A.VISABLE = 'Y' AND TYPE IN ('자유') ORDER BY A.NO DESC)) "
				+ "WHERE NO BETWEEN 1 and 1000 and USER_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
		
		// 반복문
		while (rs.next()) {
			Article article = new Article();
			
			article.setRowNum(rs.getInt("ROWNUM"));
			article.setNo(rs.getInt("NO"));
			article.setUserNo(rs.getInt("USER_NO"));
			article.setTitle(rs.getString("TITLE"));
			article.setUserNickname(rs.getString("NICKNAME"));
			article.setPostDate(rs.getDate("POST_DATE"));
			article.setReadCount(rs.getInt("READ_COUNT"));
			article.setVisable(rs.getString("VISABLE"));
			article.setType(rs.getString("TYPE"));
			
			list.add(article);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
		}
	

	// 1:1 문의 내역 수량 전체 가져오기
	public List<Article> findAllArticleForAsk(Connection connection, PageInfo pageInfo, int memNo) {
		List<Article> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT ROWNUM, NO, USER_NO, TITLE, NICKNAME, POST_DATE, READ_COUNT, VISABLE, TYPE"
				+ "FROM (SELECT NO,"
				+ "      USER_NO,"
				+ "	  TITLE,"
				+ "      NICKNAME,"
				+ "      POST_DATE,"
				+ "      READ_COUNT,"
				+ "      VISABLE,"
				+ "      TYPE"
				+ "FROM (SELECT A.NO,"
				+ "             A.USER_NO,"
				+ "			 A.TITLE,"
				+ "             M.NICKNAME,"
				+ "             A.POST_DATE,"
				+ "             A.READ_COUNT,"
				+ "             A.VISABLE,"
				+ "             A.TYPE"
				+ "FROM ARTICLE A JOIN MEMBER M ON(A.USER_NO = M.NO)"
				+ "WHERE A.VISABLE = 'Y' AND TYPE IN ('문의') ORDER BY A.NO DESC))"
				+ "WHERE NO BETWEEN 1 and 1000 and USER_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
		
		// 반복문
			while (rs.next()) {
				Article article = new Article();
				
				article.setRowNum(rs.getInt("ROWNUM"));
				article.setNo(rs.getInt("NO"));
				article.setUserNo(rs.getInt("USER_NO"));
				article.setTitle(rs.getString("TITLE"));
				article.setUserNickname(rs.getString("NICKNAME"));
				article.setPostDate(rs.getDate("POST_DATE"));
				article.setReadCount(rs.getInt("READ_COUNT"));
				article.setVisable(rs.getString("VISABLE"));
				article.setType(rs.getString("TYPE"));
				
				list.add(article);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return list;
		}

	public int getBoardCountForTrade(Connection connection, int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('거래') AND USER_NO=?";

		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
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
	
	public int getBoardCountForCommunity(Connection connection, int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('자유') AND USER_NO=?";

		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);

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

	// 1:1 문의 내역 조회
	public int getBoardAsk(Connection connection, int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('문의') AND USER_NO=?";

		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);

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
	
	public Member findMemberByNo(Connection connection, int no) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE NO=? AND STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("USER_ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("USER_NAME"));
				member.setNickname(rs.getString("NICKNAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	public int getBoardComment(Connection connection, int memNo) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM REPLY WHERE VISABLE='Y' AND ID_NO=?";


		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, memNo);

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

	public List<Reply> findAllArticleForComment(Connection connection, PageInfo pageInfo, int memNo) {
		List<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "    SELECT ROWNUM, NO, ID_NO, CONTENT, NICKNAME, COMMENT_DATE, EDIT_DATE, VISABLE "
					 + "	FROM (SELECT NO,"
					 + "                 ID_NO, "
					 + "				 CONTENT,    "
					 + "				 NICKNAME,      "
					 + "				 COMMENT_DATE,      "
					 + "				 EDIT_DATE,     "
					 + "				 VISABLE     "
					 + "	FROM (SELECT R.NO, "
					 + "                 R.ID_NO, "
					 + "				 R.CONTENT,      "
					 + "				 M.NICKNAME,      "
					 + "				 R.COMMENT_DATE,      "
					 + "				 R.EDIT_DATE,    "
					 + "				 R.VISABLE    "
					 + "	FROM REPLY R      "
					 + "	JOIN MEMBER M ON(R.ID_NO = M.NO)      "
					 + "	WHERE R.VISABLE = 'Y')) WHERE ROWNUM BETWEEN 1 and 1000 and ID_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setRowNum(rs.getInt("ROWNUM"));
				reply.setNo(rs.getInt("NO"));
				reply.setUserNo(rs.getInt("ID_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setUserNickname(rs.getString("NICKNAME"));
				reply.setCommentDate(rs.getDate("COMMENT_DATE"));
				reply.setEditDate(rs.getDate("EDIT_DATE"));
				
				list.add(reply);
				
				System.out.println(list);
				
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
