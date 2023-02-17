package com.closetou.board.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.io.Closeable;
import java.sql.Connection;
import java.util.ArrayList;

import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.dao.BoardDao;

public class BoardService {

	// 거래페이지에서 거래글과 아이템을 검색하는 메소드
	public ArrayList<TradeArticle> searchItem(String keyword, String[] attribute) {
		ArrayList<TradeArticle> result = null;
		
		Connection connection = getConnection();
		
		result = new BoardDao().searchTradeArticle(connection, keyword, attribute);
			
		
		close(connection);

		return result;
	}

}
