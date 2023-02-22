package com.closetou.member.model.service;


import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.commit;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.board.model.dao.BoardDao;
import com.closetou.common.util.PageInfo;
import com.closetou.member.model.dao.MemberDao;
import com.closetou.member.model.vo.Member;

public class MemberService {

	public Member login(String userId, String userPwd) {
		Member member = this.findMemberById(userId);
		
		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		} 			
			return member;
	}

	public Member findMemberById(String userId) {
		Connection connection = getConnection();
		
		Member member = new MemberDao().findMemberById(connection, userId);
		
		close(connection);
		
		return member;
	}

	public Boolean isDuplicateId(String userId) {
		return this.findMemberById(userId) != null;
	}

	public int updatePwd(int no, String userPwd) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new MemberDao().updatePwd(connection, no, userPwd);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int save(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		if(member.getNo() > 0) {
			// update 작업
			result = new MemberDao().updateMember(connection, member);
		} else {
			// insert 작업
			result = new MemberDao().insertMember(connection, member);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new MemberDao().updateMemberStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	
	// 거래내역 관련 - 아티클로부터 NO를 추출하여 ArrayList 객체로 반환하는 메소드
		@SuppressWarnings("null")
		public ArrayList<Integer> noFromArticle(List<Article> list) {
			ArrayList<Integer> numbers = new ArrayList<>();

			for (Article article : list) {
				int no = article.getNo();

				numbers.add(no);
			}
			return numbers;
		}

		public List<Article> getArticleForTradeList(PageInfo pageInfo) {
			List<Article> list = null;
			Connection connection = getConnection();

			list = new MemberDao().findAllArticlesForTrade(connection, pageInfo);

			close(connection);

			return list;
		}

	// 자유게시판 관련
		// (자유게시판용)조회되는 결과의 갯수를 확인하기 위한 메소드
		public int getBoardCountForCommunity() {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardCountForCommunity(connection);

			close(connection);

			return count;
		}

		// 자유게시판 게시물의 리스트를 가져오기 위한 메소드
		public List<Article> getArticleForCommunity(PageInfo pageInfo) {
			List<Article> list = null;
			Connection connection = getConnection();

			list = new MemberDao().findAllArticleForCommunity(connection, pageInfo);

			close(connection);

			return list;
		}

		public int getBoardAsk() {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardAsk(connection);

			close(connection);

			return count;
		}

		public List<Article> getArticleAsk(PageInfo pageInfo) {
			List<Article> list = null;
			Connection connection = getConnection();

			list = new MemberDao().findAllArticleForAsk(connection, pageInfo);

			close(connection);

			return list;
		}
		


}
