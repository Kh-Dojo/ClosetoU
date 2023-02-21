package com.closetou.board.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.dao.BoardDao;
import com.closetou.common.util.PageInfo;

public class BoardService {

	// 아티클을 검색하는 메소드
	public List<Article> searchArticle(String keyword) {

		System.out.println("searchArticle service 실행");

		List<Article> result = null;

		Connection connection = getConnection();

		result = new ArticleDao().searchArticle(connection, keyword);

		close(connection);

		return result;
	}

	// 거래페이지에서 거래글과 아이템을 검색하는 메소드
	public List<TradeArticle> searchItem(String keyword, String[] attribute) {
		List<TradeArticle> result = null;

		Connection connection = getConnection();

		result = new ArticleDao().searchTradeArticle(connection, keyword, attribute);

		close(connection);

		return result;
	}

	// 조회되는 결과의 개수를 확인하기 위한 메소드
	public int getBoardCountForTrade() {
		int count = 0;
		Connection connection = getConnection();

		count = new BoardDao().getBoardCountForTrade(connection);

		close(connection);

		return count;
	}

	// 게시물의 리스트를 가져오기 위한 메소드
	public List<Article> getArticleForTradeList(PageInfo pageInfo) {
		List<Article> list = null;
		Connection connection = getConnection();

		list = new ArticleDao().findAllArticlesForTrade(connection, pageInfo);

		close(connection);

		return list;
	}

}
