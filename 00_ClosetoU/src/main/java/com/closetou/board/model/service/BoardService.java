package com.closetou.board.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.io.Closeable;
import java.sql.Connection;
import java.util.ArrayList;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.dao.BoardDao;

public class BoardService {

	public ArrayList<Article> searchArticle(String keyword) {
		
		System.out.println("searchArticle service 실행");
		
		ArrayList<Article> result = null;

		Connection connection = getConnection();

		result = new BoardDao().searchArticle(connection, keyword);

		close(connection);

		return result;
	}

	// 거래페이지에서 거래글과 아이템을 검색하는 메소드
	public ArrayList<TradeArticle> searchItem(String keyword, String[] attribute) {
		ArrayList<TradeArticle> result = null;

		System.out.println("TradeArticle 실행");
		
		Connection connection = getConnection();

		result = new BoardDao().searchTradeArticle(connection, keyword, attribute);

		close(connection);

		return result;
	}

}
