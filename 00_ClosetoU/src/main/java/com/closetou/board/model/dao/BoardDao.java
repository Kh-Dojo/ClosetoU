package com.closetou.board.model.dao;

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

import static com.closetou.common.jdbc.JDBCTemplate.close;

public class BoardDao {

	// 자유게시판용 공지, 자유 게시글 목록 수 조회 쿼리
	public int getBoardCountForCommunity(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ARTICLE WHERE VISABLE='Y' AND TYPE IN('공지','자유')";

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
	
	// 자유게시판용 공지, 자유 게시글 목록 가져와 리스트에 담기
	public List<Article> findAllArticleForCommunity(Connection connection, PageInfo pageInfo) {
		List<Article> list = new ArrayList<>();
			// 셀렉해서 데이터가 없으면 null이 아닌 빈 list 리턴, 데이터가 있으면 그 데이터로 리스트 생성해 리턴
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, TITLE, NICKNAME, POST_DATE, READCOUNT, VISABLE, TYPE "
				+ "FROM (SELECT ROWNUM AS RNUM, "
				+ "             NO, "
				+ "             TITLE, "
				+ "             NICKNAME, "
				+ "             POST_DATE, "
				+ "             READCOUNT, "
				+ "             VISABLE,"
				+ "				TYPE "
				+ "        FROM (SELECT A.NO, "
				+ "                     A.TITLE, "
				+ "                     M.NICKNAME, "
				+ "                     A.POST_DATE, "
				+ "                     A.READCOUNT, "
				+ "                     A.VISABLE, "
				+ "						A.TYPE "
				+ "              FROM ARTICLE A "
				+ "              JOIN MEMBER M ON(A.USER_NO = M.NO) "
				+ "              WHERE A.VISABLE = 'Y' AND TYPE IN ('공지','자유') ORDER BY A.NO DESC) "
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
				article.setReadCount(rs.getInt("READCOUNT"));
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

	
	

}
