package com.closetou.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.common.util.FileRename;
import com.closetou.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

// 게시물 수정

@WebServlet(name = "communityBoardUpdate", urlPatterns = { "/board/communityBoardUpdate" })
public class CommunityBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommunityBoardUpdateServlet() {
    }
    
    // 로그인체크
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));	// 파라미터에서 no값 가져와 integer로 읽어옴
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if (loginMember != null) {	// 로그인 상태

// 230216 8교시 한 번 조회한 회원은 조회수 처리에 포함되지 않게 만들기 > getBoardByNo()를 ,true 포함시켜 수정. true는 이미 조회한 이력이 있는 상태
			Article article = new ArticleService().getArticleByNoForCommunity(no, true);		//  게시글을 작성한 회원이 맞는지 체크 안쪽 if else 까지
						
			if(article != null && loginMember.getNickname().equals(article.getUserNickname())) {
				request.setAttribute("Article", article);
				request.getRequestDispatcher("/views/board/communityBoardUpdate.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/communityBoardList");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}
	
	// 수정
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if(loginMember != null) {
			
			// MultipartRequest
	    	
	    	// 파일이 저장될 경로 얻어오기
	    	String path = getServletContext().getRealPath("/resources/boardUpfile");
	    	
	    	// 파일의 최대 사이즈 지정(10MB로 지정)
	    	int maxSize = 10485760;
	    	
	    	// 파일 인코딩 설정
	    	String encoding = "UTF-8";
	    	
	    	// MultipartRequest로 파라미터값 받기
//	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
	    // 위 의 코드를 /mvc/common/util FileRename 클래스 사용을 하면서 아래처럼 바꿈 FileRename 정책
	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
	    		// MultipartRequest(리퀘스트객체, 파일저장경로, 파일최대사이즈, 파일 인코딩 설정, 파일 리네임 정책)
	        	// DefaultFileRenamePolicy() : 중복되는 이름 뒤에 1 ~ 9999 붙인다.

// 230216 1교시 코드 약간 수정
	    	// 사용자가 업데이트 할 때 숨겨서 받아옴 (update.jsp의 48행)
// 230216 8교시 한 번 조회한 회원은 조회수 처리에 포함되지 않게 만들기 > getBoardByNo()를 ,true 포함시켜 수정. true는 이미 조회한 이력이 있는 상태
	    	Article article = new ArticleService().getArticleByNoForCommunity(Integer.parseInt(mr.getParameter("no")), true);


	    	if(article != null && loginMember.getNickname().equals(article.getUserNickname())) {
// 230214 8교시 수정한 내용으로 게시글이 바뀌게 만들기
	    

	    		
//	    		board.setNo(Integer.parseInt(mr.getParameter("no")));	// 230216 1교시 코드 수정하며 밖으로 빼냄
	    		article.setTitle(mr.getParameter("title"));
	    		article.setContent(mr.getParameter("content"));
	    		article.setType(mr.getParameter("type"));
	    		
	    		
// 230216 1교시 첨부 파일을 포함해 게시글 수정하기
	    		String originalFileName = mr.getOriginalFileName("upfile");
	    		String filesystemName = mr.getFilesystemName("upfile");	    		
	    		
	    		// 파일을 넘겨주지 않았을 때 뭐가 찍히나(null of 빈문자열) 확인하기
	    		System.out.println(originalFileName);
	    		System.out.println(filesystemName);
	    		
	    		// 파일을 첨부하고 수정했을 때 첨부파일이 수정되게
	    		if(originalFileName != null && filesystemName != null) {	// 사용자가 파일을 수정하지 않으면 null
	    			
	    			// 230216 1교시 수정 후 기존에 업로드된 파일 삭제하기
	    			File file = new File(path + "/" + article.getRenamedFileName()); // 실제 물리적인 경로에 있는 파일 오브젝트(객체) 가져오기
	    							// 파일 경로로 가서 기존에 있는 파일 찾아옴
	    			
	    			if(file.exists()) {		// 찾은 파일이 존재한다면, 삭제하기
	    				file.delete();
	    			}
	    			
	    			article.setOriginalFileName(originalFileName);
	    			article.setRenamedFileName(filesystemName);
	    		}
	    		

	    		int result = new ArticleService().save(article);

	    		if(result > 0 ) {
	    			request.setAttribute("msg", "게시글 수정 성공");
	    			request.setAttribute("location", "/communityArticleView?no=" + article.getNo());
	    		} else {
	    			request.setAttribute("msg", "게시글 수정 실패");
	    			request.setAttribute("location", "/board/communityBoardUpdate?no=" + article.getNo());
	    		}
	    		

	    		/*
	    		 * System.out.println(Integer.parseInt(mr.getParameter("no")));
	    		 * System.out.println(mr.getParameter("title"));
	    		 * System.out.println(mr.getParameter("writer"));
	    		 * System.out.println(mr.getParameter("content")); // 수정된 내용이 콘솔에 출력
	    		 */
	    		
	    	} else {
	    		request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/list");
				// request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	    	}
	    	
	    	
	    	
	    	
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
