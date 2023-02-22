<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/communityBoardView.css" />

<section>   
<div id="sidemenu"><jsp:include page="/views/common/sidemenu/communitySideMenu.jsp" /></div>
<article id="content">
	<div id="board-write-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
<!-- 230214 3교시 상세 게시글 화면에 그려주기 -->
				<td>${ Article.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ Article.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ Article.userNickname }</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${ Article.postDate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ Article.readCount }</td>
			</tr>
			<tr>
<!-- 230214 6교시 게시글 내에서 첨부파일 이름 보이게 만들기 test 속성-->
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty Article.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty Article.originalFileName }">
						<a href="${ path }/resources/boardUpfile/${ Article.renamedFileName}"
                    	 					download="${ Article.originalFileName }" >
                  			<span> ${ Article.originalFileName } </span>
                  		</a>
						
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ Article.content }</td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<tr>
				<th colspan="2">
<!-- 230214 3교시 수정 삭제 로그인 한 작성자만 보이게 하기 -->
					<c:if test="${ not empty loginMember && loginMember.nickname == Article.userNickname }">
<!-- 230214 6교시 게시글 내 수정 버튼 누르면 수정 페이지로 이동 -->
						<button type="button" onclick="location.href='${ path }/board/communityBoardUpdate?no=${ Article.no }'">수정</button>
<!-- 230216 2교시 게시글 삭제하기 -->						
						<button type="button" id="btnDelete">삭제</button>
					</c:if>
<!-- 230214 4교시 상세 게시물에서 목록으로 가게 만들기 -->
					<button type="button" onclick="location.href='${ path }/board/communityBoardList'">목록으로</button>
										<!-- onclick="location.href='${ path }/board/list'" 상세 게시물에서 1페이지 목록으로 보내는 코드 문제! 모든 수정하기에서 뒤로가기가 됨...-->
										<!-- onclick="location.href='javascript:history.back()'" 상세 게시물에서 현재 게시물이 있는 목록으로 보내는 코드 javascript: 생략 가능 -->		
				</th>
			</tr>
		</table>
<!-- 230216 6교시 댓글 작성란 만들기 -->
		<div id="comment-container">
	    	<div class="comment-editor">
	    		<form action="${ path }/board/reply" method="POST">
	    			<input type="hidden" name="boardNo" value="${ board.no }">
	    <!-- 로그인 한 회원만 댓글 작성할 수 있게하기 id속성 주고 javascript script 태그로-->	
					<textarea name="content" id="replyContent" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>	    			
	    		</form>
	    	</div>
	    </div>
<!-- 230216 6교시 댓글 화면에 표시하기 -->
	    <table id="tbl-comment">
	    <!-- tr태그 자체가 하나의 댓글. 여러 댓글이 있을 수 있으니 foreach문으로 만들어주기 c:foreach -->
    	   	<c:forEach var="reply" items="${ board.replies }">    	   	
	    	   	<tr class="level1">
		    		<td>
		    			<sub class="comment-writer">${ reply.writerId }</sub>
		    			<sub class="comment-date">${ reply.createDate }</sub>
		    			<br>
		    			<span>${ reply.content }</span>
		    		</td>
		    		<td>
		 <!-- loginMember이면서 loginMember의 ID와 댓글 작성한 사람의 ID가 같을 때 버튼태그보이게 -->		
		    			<c:if test="${ not empty loginMember && loginMember.id == reply.writerId}">
		    				<button class="btn-delete">삭제</button>
		    			</c:if>
	
		    		</td>
		    	</tr>
    	   	</c:forEach>
	    </table>
    </div>
</article>
<%-- <script src="${ path }/resources/js/communityBoardView.js"></script> --%>
<script>
/* 제이쿼리 영억 */
//230216 2교시 게시글 삭제하기
	$(document).ready(() => {
		$('#btnDelete').on('click', () => {
			if(confirm('정말로 게시글을 삭제하시겠습니까?')) {
				location.replace('${ path }/board/delete?no=${ board.no }');
							// 요청이 왔을 때 받을 서블릿 생성. BoardDeleteServelt.java
			}
		});
	
//<!-- 230216 3교시 첨부파일 다운로드하기 a 태그 누르면 -->
		$('#fileDown').on('click', () => {
						// encodeURIComponent() URI로 데이터를 전달하기 위해서 문자열을 인코딩 첨부파일 이름이 한글이거나 공백 특문인경우 url에 이상하게 나오는데 그거를 바꿔줌
			let oname = encodeURIComponent('${ board.originalFileName }');
	        let rname = encodeURIComponent('${ board.renamedFileName }');
	       
	        location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);
									// ㄴ board/fileDown 요청 처리할 수 잇는 서블릿 생성. BoardFileDownServelt.java
									
	        // location.assign('${ path }/board/fileDown?oname=${ board.originalFileName }&rname=${ board.renamedFileName }');
									
		/*
			getParameter()의 매개변수는 어디서 나온 건가요???
				태그의 name 속성!
				but! 무조건 태그의 name 속성이 아니라 받아온 값의 이름을 써준다고 생각하기
		 */
		});

		
//<!-- 230216 6교시 로그인 한 회원만 댓글 작성할 수 있게하기 -->
		$('#replyContent').on('focus', () => {
			if(${ empty loginMember }) {
				alert('로그인 후 이용해 주세요.')
				
				$('#userId').focus();
			}			
		});
		
		

	});
</script>

</section>

<jsp:include page="/views/common/footer.jsp" />

