package com.closetou.member.model.service;


import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.commit;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.Reply;
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

	public Member findMemberById(String id) {
		Connection connection = getConnection();
		
		Member member = new MemberDao().findMemberById(connection, id);			
		
		close(connection);
		
		return member;
	}

	public boolean isDuplicateId(String id) {
		return this.findMemberById(id) != null;
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
		
		// 거래 글 불러오기
		public List<Article> getArticleForTradeList(PageInfo pageInfo, int memNo) {
			List<Article> list = null;
			
			Connection connection = getConnection();

			list = new MemberDao().findAllArticlesForTrade(connection, pageInfo, memNo);

			close(connection);

			return list;
		}
		
		// 자유게시판 게시물의 리스트를 가져오기 위한 메소드
		public List<Article> getArticleForCommunity(PageInfo pageInfo, int memNo) {
			List<Article> list = null;
						
			Connection connection = getConnection();
			
			list = new MemberDao().findAllArticleForCommunity(connection, pageInfo, memNo);
			
			close(connection);
			
			return list;
		}
		
		public int getBoardCountForTrade(int no) {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardCountForTrade(connection, no);

			close(connection);

			return count;
		}

	// 자유게시판 관련
		// (자유게시판용)조회되는 결과의 갯수를 확인하기 위한 메소드
		public int getBoardCountForCommunity(int no) {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardCountForCommunity(connection, no);

			close(connection);

			return count;
		}

		
		// 1:1 문의 관련
		public int getBoardAsk(int no) {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardAsk(connection, no);

			close(connection);

			return count;
		}
		
		// 1:1 문의 관련
		public List<Article> getArticleAsk(PageInfo pageInfo, int memNo) {
			List<Article> list = null;
			
			Connection connection = getConnection();

			list = new MemberDao().findAllArticleForAsk(connection, pageInfo, memNo);

			close(connection);

			return list;
		}
		
		
		// 댓글
		public int getBoardComment(int no) {
			int count = 0;
			Connection connection = getConnection();

			count = new MemberDao().getBoardComment(connection, no);

			close(connection);

			return count;
		}

		public List<Reply> getArticleComment(PageInfo pageInfo, int memNo) {
			List<Reply> list = null;
			
			Connection connection = getConnection();

			list = new MemberDao().findAllComment(connection, pageInfo, memNo);

			close(connection);

			return list;
		}
		
		// No 관련
		public Member findMemberByNo(int No) {
			Connection connection = getConnection();
			
			Member member = new MemberDao().findMemberByNo(connection, No);
			
			close(connection);
			
			return member;
		}
		
		public Member loginNo(int No) {
			Member member = this.findMemberByNo(No);
			
			return member;
		}
		
}
