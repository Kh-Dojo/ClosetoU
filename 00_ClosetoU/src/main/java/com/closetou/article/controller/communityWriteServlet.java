package com.closetou.article.controller;

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

// 게시판 글쓰기
@WebServlet(name = "communityWrite", urlPatterns = { "/article/communityWrite" })
public class communityWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public communityWriteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 목록에서 글쓰기 버튼 누르면 작성 페이지로
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if (loginMember != null) {
			request.getRequestDispatcher("/views/board/communityBoardWrite.jsp").forward(request, response);
		} else {	// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 첨부파일 넣으면 서버로 전달
    	
    	/*// 사용자가 보낸 파라미터값 받기
    	System.out.println(request.getParameter("title"));	// wirte.jsp의 50행 name
    	System.out.println(request.getParameter("writer"));
    	System.out.println(request.getParameter("upfile"));	// 이렇게 하면 파일을 보내는 게 아니라 파일명만 보냄
    	System.out.println(request.getParameter("content"));*/
    	
//    	MultipartRequest mr = new MultipartRequest(request, getServletName(), 0, getServletInfo(), null);
    	
  	
    	// 로그인 체크
    		//로그인해야 글쓰기 가능하니
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
    	if(loginMember != null) {	// 로그인 상태
    		
        	// 파일이 저장될 경로 얻어오기
        	String path = getServletContext().getRealPath("/resources/boardUpfile");	// / = 현재 웹 애플리케이션에서 webapp에 해당
        	
        	// 파일의 최대 사이즈 지정(10MB로 지정)
        		// 사이즈 지정은 바이트 단위로 한다. 10485760byte
        	int maxSize = 10485760;
        	
        	// 파일 인코딩 설정
        	String encoding = "UTF-8";
        	
        	// MultipartRequest로 파라미터값 받기
//        	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
        // 위 의 코드를 /mvc/common/util FileRename 클래스 사용을 하면서 아래처럼 바꿈 FileRename 정책
        	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
        		// MultipartRequest(리퀘스트객체, 파일저장경로, 파일최대사이즈, 파일 인코딩 설정, 파일 리네임 정책)
	        	// DefaultFileRenamePolicy() : 중복되는 이름 뒤에 1 ~ 9999 붙인다.
        	
        	
// 230214 5교시 글 작성할 때 첨부파일 넣으면 서버로 전달
        	// 폼 파라미터로 넘어온 값들
        	// String writer = mr.getParameter("writer");
        	
            // String title = mr.getParameter("title");
            // String writer = mr.getParameter("writer");
            // String content = mr.getParameter("content");
             // 파일에 대한 정보를 가져올 때
             // String filesystemName = mr.getFilesystemName("upfile");          
             // 실제 서버에 업로드된 이름
             // String originalFileName = mr.getOriginalFileName("upfile");
             // alt shift i 를 통해 인라인 처리하였음		board.setTitle(mr.getParameter("title"));형태 코드들.....
        	
        	
        	Article article = new Article();
        	
        	article.setUserNo(loginMember.getNo());
        	article.setUserNickname(loginMember.getNickname());
        	article.setTitle(mr.getParameter("title"));
        	article.setContent(mr.getParameter("content"));
        	article.setRenamedFileName(mr.getFilesystemName("upfile"));		// 클라이언트가 파일을 선택해서 올린 이름	   ex) 정처기해설집
        	article.setOriginalFileName(mr.getOriginalFileName("upfile"));	// 실제 서버에 업로드(저장) 될 때 파일 이름 ex)
        		// 두 개의 파일명을 다 저장해야 함. 서버 저장용, 클라이언트에게 다시 보낼 때 사용하는 용
        	article.setType(mr.getParameter("type"));
        	
        	
        	int result = new ArticleService().save(article);
        	
        	
        	if(result > 0) {	// 게시글 작성 성공
        		request.setAttribute("msg", "게시글 등록 성공.");
    			request.setAttribute("location", "/board/communityBoardList");
        	} else {			// 게시글 작성 실패
        		request.setAttribute("msg", "게시글 등록 실패.");
    			request.setAttribute("location", "/board/communityBoardList");
        	}
        	
    	} else {					// 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    
    
    }



}
