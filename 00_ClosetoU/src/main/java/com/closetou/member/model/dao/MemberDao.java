package com.closetou.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import com.closetou.article.model.vo.Article;
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
		String query = "UPDATE MEMBER SET NAME=?, NICKNAME=?, PHONE=?, EMAIL=?, ADDRESS=?, ADDRESS_DETAIL=? WHERE NO=?";
		
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
	
	// 자유 글 수량 조회
//	public int getBoardCountForCommunity(Connection connection) {
//		int count = 0;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('자유')";
//
//
//		try {
//			pstmt = connection.prepareStatement(query);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				count = rs.getInt(1);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//	
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//
//		return count;
//	}
	
	// 거래 글 전체 조회
	public List<Article> findAllArticlesForTrade(Connection connection, PageInfo pageInfo) {
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
	
	// 자유게시판 자유 게시글 목록 가져와 리스트에 담기
	public List<Article> findAllArticleForCommunity(Connection connection, PageInfo pageInfo) {
		List<Article> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String query = "SELECT RNUM, NO, TITLE, NICKNAME, POST_DATE, READ_COUNT, VISABLE, TYPE "
				+ "FROM (SELECT ROWNUM AS RNUM, "
				+ "             NO, "
				+ "             TITLE, "
				+ "             NICKNAME, "
				+ "             POST_DATE, "
				+ "             READ_COUNT, "
				+ "             VISABLE, "
				+ "             TYPE  "
				+ "       FROM (SELECT A.NO, "
				+ "                    A.TITLE, "
				+ "                    M.NICKNAME, "
				+ "                    A.POST_DATE, "
				+ "                    A.READ_COUNT, "
				+ "                    A.VISABLE, "
				+ "                    A.TYPE "
				+ "             FROM ARTICLE A "
				+ "             JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "             WHERE A.VISABLE = 'Y' AND TYPE IN ('자유') ORDER BY A.NO DESC) "
				+ "        ) WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());// .getStartList() 현재 페이지의 시작 리스트 번호 (1페이지는 1 2페이지은 11 3페이지는 21...)
			pstmt.setInt(2, pageInfo.getEndList());  // .getEndList() 현재 페이지의 마지막 리스트 번호 (1페이지는 10 2페이지은 20 3페이지는 20...)
					// WHERE RNUM BETWEEN ? and ?의 ? 정수값 받아서 setInt(), 첫 번째 물음표에는 pageInfo.getStartList() 들어가라...
			rs = pstmt.executeQuery();
		
		// 반복문
		while (rs.next()) {
			Article article = new Article();
			
			article.setRowNum(rs.getInt("RNUM"));
			article.setNo(rs.getInt("NO"));
			article.setTitle(rs.getString("TITLE"));
			article.setUserNickname(rs.getString("NICKNAME"));
			article.setPostDate(rs.getDate("POST_DATE"));
			article.setReadCount(rs.getInt("READ_COUNT"));
			article.setVisable(rs.getString("VISABLE"));
			article.setType(rs.getString("TYPE"));
			
			//열 개가 조회되면 열 개의 데이터를 리스트에 담아줌, 조회되는 게 없으면 빈 리스트 리턴
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
	
	// 1:1 문의 내역 조회
	public int getBoardAsk(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('문의')";

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

	// 1:1 문의 내역 수량 전체 가져오기
	public List<Article> findAllArticleForAsk(Connection connection, PageInfo pageInfo) {
		List<Article> asklist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, NO, TITLE, NICKNAME, POST_DATE, READ_COUNT, VISABLE, TYPE "
				+ "FROM (SELECT ROWNUM AS RNUM, "
				+ "             NO, "
				+ "             TITLE, "
				+ "             NICKNAME, "
				+ "             POST_DATE, "
				+ "             READ_COUNT, "
				+ "             VISABLE, "
				+ "             TYPE  "
				+ "       FROM (SELECT A.NO, "
				+ "                    A.TITLE, "
				+ "                    M.NICKNAME, "
				+ "                    A.POST_DATE, "
				+ "                    A.READ_COUNT, "
				+ "                    A.VISABLE, "
				+ "                    A.TYPE "
				+ "             FROM ARTICLE A "
				+ "             JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "             WHERE A.VISABLE = 'Y' AND TYPE IN ('문의') ORDER BY A.NO DESC) "
				+ "        ) WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());// .getStartList() 현재 페이지의 시작 리스트 번호 (1페이지는 1 2페이지은 11 3페이지는 21...)
			pstmt.setInt(2, pageInfo.getEndList());  // .getEndList() 현재 페이지의 마지막 리스트 번호 (1페이지는 10 2페이지은 20 3페이지는 20...)
					// WHERE RNUM BETWEEN ? and ?의 ? 정수값 받아서 setInt(), 첫 번째 물음표에는 pageInfo.getStartList() 들어가라...
			rs = pstmt.executeQuery();
		
		// 반복문
		while (rs.next()) {
			Article article = new Article();
			
			article.setRowNum(rs.getInt("RNUM"));
			article.setNo(rs.getInt("NO"));
			article.setTitle(rs.getString("TITLE"));
			article.setUserNickname(rs.getString("NICKNAME"));
			article.setPostDate(rs.getDate("POST_DATE"));
			article.setReadCount(rs.getInt("READ_COUNT"));
			article.setVisable(rs.getString("VISABLE"));
			article.setType(rs.getString("TYPE"));
			
			//열 개가 조회되면 열 개의 데이터를 리스트에 담아줌, 조회되는 게 없으면 빈 리스트 리턴
			asklist.add(article);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return asklist;
	}

}
