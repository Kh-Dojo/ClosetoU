package com.closetou.board.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.vo.Article;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.common.util.PageInfo;

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
				+ "             WHERE A.VISABLE = 'Y' AND TYPE IN ('공지','자유') ORDER BY A.NO DESC) "
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


	
	
//////////////////////////// 위 주희 아래 정준 //////////////////////////////
	
	// 쿼리문의 BOARD 자리를 손보면 될 것 같은데 조회되는 결과 값을 기준으로 페이징
		public int getBoardCountForTrade(Connection connection) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(*) FROM ARTICLE WHERE TYPE = '거래'";

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
		
		public ArrayList<ClothCategory> getClothCategories(Connection connection) {
			ArrayList<ClothCategory> categories = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT CLOTH_CODE, CLOTH_CATEGORY FROM CLOTH_CATEGORY";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ClothCategory ccate = new ClothCategory();
					
					ccate.setClothCode(rs.getString("CLOTH_CODE"));
					ccate.setClothCategory(rs.getString("CLOTH_CATEGORY"));
				
				categories.add(ccate);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				close(rs);
				close(pstmt);
			}
			
			return categories;
		}

		public List<Article> searchArticleForTrade(Connection connection, String keyword, PageInfo pageInfo) {
			int count = 0;
			List<Article> artlist = new ArrayList();
			Article article;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			
			String query1 = "SELECT "
					+ "    COUNT(*) "
					+ "FROM "
					+ "    ARTICLE "
					+ "WHERE "
					+ "    TITLE LIKE '%"
					+ keyword
					+ "%'";
			
			try {
				
				pstmt1 = connection.prepareStatement(query1);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next()) {
					
					count = rs1.getInt("COUNT(*)");				
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			System.out.println(keyword);
			
			pageInfo.setListCount(count);
			
			String query2 = "SELECT "
					+ "    * "
					+ "FROM "
					+ "    ( "
					+ "        SELECT "
					+ "            ROWNUM, "
					+ "            RNUM, "
					+ "            NO, "
					+ "            PHOTO_NO, "
					+ "            USER_NO, "
					+ "            ORIGINAL_FILENAME, "
					+ "            RENAMED_FILENAME, "
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
					+ "                    PHOTO_NO, "
					+ "                    USER_NO, "
					+ "                    ORIGINAL_FILENAME, "
					+ "                    RENAMED_FILENAME, "
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
					+ "                    TITLE LIKE '%"
					+ keyword 
					+ "%' "
					+ "                ORDER BY "
					+ "                    NO DESC "
					+ "            ) "
					+ "    ) "
					+ "WHERE "
					+ "    ROWNUM BETWEEN ? AND ?"; 
			
			System.out.println(pageInfo.getStartList());
			System.out.println(pageInfo.getEndList());
			
			try {

				pstmt2 = connection.prepareStatement(query2);
				
				pstmt2.setInt(1, pageInfo.getStartList());
				pstmt2.setInt(2, pageInfo.getEndList());
				
				rs2 = pstmt2.executeQuery();
				
				System.out.println(query2);
				
				while(rs2.next()) {
					
					article = new Article();
					
					article.setNo(rs2.getInt("NO"));
					article.setNo(rs2.getInt("USER_NO"));
					article.setOriginalFileName(rs2.getString("ORIGINAL_FILENAME"));
					article.setRenamedFileName(rs2.getString("RENAMED_FILENAME"));
					article.setType(rs2.getString("TYPE"));
					article.setTitle(rs2.getString("TITLE"));
					article.setContent(rs2.getString("CONTENT"));
					article.setReadCount(rs2.getInt("READ_COUNT"));
					article.setPostDate(rs2.getDate("POST_DATE"));
					article.setEdited(rs2.getString("EDITED"));
					article.setEditDate(rs2.getDate("EDIT_DATE"));
					
					artlist.add(article);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs2);
				close(pstmt2);
			}
			
			System.out.println(artlist);
			
			return artlist;
		}
}
