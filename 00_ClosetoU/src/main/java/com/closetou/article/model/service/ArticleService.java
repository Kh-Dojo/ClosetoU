package com.closetou.article.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;

public class ArticleService {

	// 아티클로부터 NO를 추출하여 ArrayList 객체로 반환하는 메소드
	@SuppressWarnings("null")
	public ArrayList<Integer> noFromArticle(List<Article> list) {
		ArrayList<Integer> numbers = new ArrayList<>();

		for (Article article : list) {
			int no = article.getNo();

			numbers.add(no);
		}
		return numbers;
	}

	// No값으로 TradeArticle을 가져오는 메소드
	public TradeArticle getTradeArticleByNo(int no) {
		TradeArticle trart = null;

		Connection connection = getConnection();

		trart = new ArticleDao().findTradeArticleByNo(connection, no);

		close(connection);

		return trart;
	}

}
