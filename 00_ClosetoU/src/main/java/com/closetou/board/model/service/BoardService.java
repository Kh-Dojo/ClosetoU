package com.closetou.board.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.dao.BoardDao;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.common.util.PageInfo;

public class BoardService {

	// (자유게시판용)조회되는 결과의 갯수를 확인하기 위한 메소드
	public int getBoardCountForCommunity() {
		int count = 0;
		Connection connection = getConnection();

		count = new BoardDao().getBoardCountForCommunity(connection);

		close(connection);

		return count;
	}

	// 자유게시판 게시물의 리스트를 가져오기 위한 메소드
	public List<Article> getArticleForCommunity(PageInfo pageInfo) {
		List<Article> list = null;
		Connection connection = getConnection();

		list = new BoardDao().findAllArticleForCommunity(connection, pageInfo);

		close(connection);

		return list;
	}

//////////////////////////////	위 주희 아래 정준	//////////////////////////////////////

	// 거래게시글 작성 시 등록될 의류 카테고리 리스트를 가져오기 위한 메소드
	public ArrayList<ClothCategory> getClothCategories() {
		ArrayList<ClothCategory> cate = new ArrayList<>();

		Connection connection = getConnection();

		cate = new BoardDao().getClothCategories(connection);

		close(connection);

		return cate;
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

	// 키워드를 통해 거래 게시물을 찾는 메소드 (작업중)
	public List<TradeArticle> searchArticleForTrade(String keyword, PageInfo pageInfo, String[] attribute) {
		List<TradeArticle> trarts = null;

		Connection connection = getConnection();

//		trarts = new BoardDao().searchArticleForTrade(connection, keyword, attribute, pageInfo);

		close(connection);

		return trarts;
	}

	
	// 로직 수정으로 인한 주석처리
	
//	public List<TradeArticle> searchTradeArticlebyArticleArray(List<Article> searchList, String[] attribute) {
//		List<TradeArticle> result = null;
//		ArrayList<Integer> numbers = new ArrayList<>();
//		Connection connection = getConnection();
//
//		for (Article art : searchList) {
//			numbers.add(art.getNo());
//		}
//
//		result = new ArticleService().getTradeArticleByNosWithAttributes(numbers, attribute);
//		
//		close(connection);
//		
//		return result;
//	}

}
