<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/communityBoardView.css" />
<section  id="article-section">  
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/communitySideMenu.jsp" /></div> 
 	<article id="article-content">
 	<div id="article-outline">
		<table id="article-view">
			<tr>
				<td colspan="3">
					<c:if test="${ not empty loginMember && loginMember.nickname == Article.userNickname }">
						<button type="button" class="btn-article-view" onclick="location.href='${ path }/board/communityBoardUpdate?no=${ Article.no }'">수정</button>					
						<button type="button" class="btn-article-view" id="btnDelete">삭제</button>
					</c:if>
					<button type="button" class="btn-article-view" onclick="location.href='${ path }/board/communityBoardList'">목록으로</button>		
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<sub>${ Article.type }</sub><br>
					${ Article.title }
				</td>
			</tr>
			<tr>
				<td>${ Article.userNickname }</td>
				<td>${ Article.readCount }</td>
				<td>${ Article.postDate }</td>
			</tr>
			<tr>
				<td colspan="3">
					${ Article.content }
<%-- 					<img src="${ path }/resouces/boardUpfile/${ Article.renamedFileName }"/> --%>
				</td>
			</tr>
			<tr>
				<c:if test="${ empty Article.originalFileName }">
					<td colspan="3">
						<span> - </span>
					</td>
				</c:if>
				<c:if test="${ not empty Article.originalFileName }">
					<td colspan="3">
						<a href="${ path }/resources/boardUpfile/${ Article.renamedFileName }"
                    	 			download="${ Article.originalFileName }" >
                  			<span> ${ Article.originalFileName } </span>
                  		</a>
					</td>	
				</c:if>
			</tr>
			
		</table>
<!-- 230216 6교시 댓글 작성란 만들기 -->
		<div id="comment-container">
	    	<div class="comment-editor">
	    		<form action="${ path }/article/reply" method="POST">
	    			<input type="hidden" name="articleNo" value="${ Article.no }">
	    <!-- 로그인 한 회원만 댓글 작성할 수 있게하기 id속성 주고 javascript script 태그로-->	
					<textarea name="content" id="replyContent" cols="55" rows="3" placeholder="댓글을 입력해주세요."></textarea>
					<button type="submit" id="btn-insert">등록</button>	    			
	    		</form>
	    	</div>
	    </div>
<!-- 230216 6교시 댓글 화면에 표시하기 -->
	    <table id="tbl-comment">
	    <!-- tr태그 자체가 하나의 댓글. 여러 댓글이 있을 수 있으니 foreach문으로 만들어주기 c:foreach -->
    	   	<c:forEach var="reply" items="${ Article.replies }">    	   	
	    	   	<tr class="level1">
		    		<td>
		    			<sub class="comment-writer">${ reply.userNickname }</sub>
		    			<sub class="comment-date">${ reply.commentDate }</sub>
		    			<br>
		    			<span>${ reply.content }</span>
		    		</td>
		    		<td>
		 <!-- loginMember이면서 loginMember의 ID와 댓글 작성한 사람의 ID가 같을 때 버튼태그보이게 -->		
		    			<c:if test="${ not empty loginMember && loginMember.nickname == reply.userNickname}">
		    				<form action="${ path }/article/replyDelete" method="GET">
		    					<input type="hidden" name="articleNo" value="${ Article.no }">
		    					<input type="hidden" name="replyNo" value="${ reply.no }">
			    				<button class="btn-delete" id="btnReplyDelete">삭제</button>
		    				</form>
		    			</c:if>
	
		    		</td>
		    	</tr>
    	   	</c:forEach>
	    </table>
    </div>
    </article>
	

<script>
//230216 2교시 게시글 삭제하기
	$(document).ready(() => {
		$('#btnDelete').on('click', () => {
			if(confirm('게시글을 삭제하시겠습니까?')) {
				location.replace('${ path }/article/delete?no=${ Article.no }');
			}
		});
	
//첨부파일 다운로드하기
		$('#fileDown').on('click', () => {
						
			let oname = encodeURIComponent('${ Article.originalFileName }');
	        let rname = encodeURIComponent('${ Article.renamedFileName }');
	       
	        location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);

//로그인 한 회원만 댓글 작성할 수 있게하기 -->
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

