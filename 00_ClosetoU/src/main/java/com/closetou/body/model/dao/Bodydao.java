package com.closetou.body.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.closetou.article.model.vo.Article;
import static com.closetou.common.jdbc.JDBCTemplate.*;

public class Bodydao {

	

	public List<HashMap<Object, String>> findList(Connection conn) {
		
		List<HashMap<Object, String>> resultList = new ArrayList<HashMap<Object,String>>();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		String query = "SELECT TYPE, TITLE, POST_DATE, NO FROM ( SELECT TYPE, TITLE, POST_DATE, NO FROM ARTICLE WHERE TYPE='공지' ORDER BY POST_DATE DESC) WHERE ROWNUM < 4";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<Object, String> noticeList = new HashMap<Object, String>();
				noticeList.put("td1", rs.getString("TYPE"));
				noticeList.put("td2", rs.getString("TITLE"));
				noticeList.put("td3", rs.getDate("POST_DATE").toString());
				noticeList.put("no", rs.getString("NO"));
				
				resultList.add(noticeList);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		
		return resultList;
	}

	public List<HashMap<Object, String>> findPopList(Connection conn) {

		List<HashMap<Object, String>> re = new ArrayList<HashMap<Object, String>>();
		PreparedStatement pstmt  = null;
		ResultSet rs  = null;
		String query = "SELECT ROWNUM, TYPE, TITLE, READ_COUNT, POST_DATE , NO FROM ( SELECT TYPE, TITLE, READ_COUNT, POST_DATE,NO FROM ARTICLE WHERE TYPE='자유' ORDER BY READ_COUNT DESC)    WHERE ROWNUM <4";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<Object, String> poplist = new HashMap<Object, String>();
				poplist.put("td2_1", rs.getString("TYPE"));
				poplist.put("td2_2", rs.getString("TITLE"));
				poplist.put("td2_3", rs.getString("READ_COUNT"));
				poplist.put("td2_4", rs.getDate("POST_DATE").toString());
				poplist.put("no", rs.getString("NO"));
				
				re.add(poplist);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return re;
		
	}

//	public List<Article> findList(Connection conn) {
//		List<Article> noticelist = new ArrayList<>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String query = "SELECT TYPE, TITLE, POST_DATE FROM ( SELECT TYPE, TITLE, POST_DATE FROM ARTICLE WHERE TYPE='공지' ORDER BY POST_DATE DESC) WHERE ROWNUM < 4";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			System.out.println("findList=================" );
//			while(rs.next()) {
//				Article article = new Article();
//				
//				article.setType(rs.getString("TYPE"));
//				article.setTitle(rs.getString("TITLE"));
//				article.setPostDate(rs.getDate("POST_DATE"));
//				
//				noticelist.add(article);
//				System.out.println(article);
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//			
//		}
//		
//		
//		return noticelist;
//	}





}
