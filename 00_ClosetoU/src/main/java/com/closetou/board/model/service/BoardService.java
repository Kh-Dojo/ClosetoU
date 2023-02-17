package com.closetou.board.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.io.Closeable;
import java.sql.Connection;

public class BoardService {

	// 거래페이지에서 거래글과 아이템을 검색하는 메소드
	public int searchItem() {
		int result = 0;

		Connection connection = getConnection();


		result = new BoardDao().searchTradeArticle();
		
		
		close(connection);

		return result;
	}

}
