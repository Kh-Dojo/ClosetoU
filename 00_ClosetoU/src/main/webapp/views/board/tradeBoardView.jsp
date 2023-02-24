<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/tradeArticle.css" />
<article>
	<div id="sidemenu"><jsp:include
			page="/views/common/sidemenu/tradeSideMenu.jsp" /></div>
	<section>
		<div id="item_area">
			<div id="item_photos">${ trart.clothNumber }${ article.renamedFileName }
			</div>
			<div id="item_details">
				<div id="item_info">${ trart.price }${ trart.tradeEnd }${ trart.free }
					${ trart.tradeMethod } ${ trart.location }</div>
				<div id="buttons_area">
					<button>채팅하기</button>
					<button>찜하기</button>
					<button>신고하기</button>
				</div>
			</div>
			<div id="seller_content_area">${ article.title }${ article.content }
			</div>
			<div id="seller_info_area">${ member.nickname }${ member.id }</div>
		</div>

		<c:if
			test="${ not empty loginMember && loginMember.nickname == Article.userNickname }">
			<button type="button"
				onclick="location.href='${ path }/board/communityBoardUpdate?no=${ Article.no }'">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		</c:if>
		<button type="button"
			onclick="location.href='${ path }/board/communityBoardList'">목록으로</button>
		<!-- onclick="location.href='${ path }/board/list'" 상세 게시물에서 1페이지 목록으로 보내는 코드 문제! 모든 수정하기에서 뒤로가기가 됨...-->
		<!-- onclick="location.href='javascript:history.back()'" 상세 게시물에서 현재 게시물이 있는 목록으로 보내는 코드 javascript: 생략 가능 -->
		<div id="comment-container">
			<div class="comment-editor">
				<form action="${ path }/article/reply" method="POST">
					<input type="hidden" name="articleNo" value="${ Article.no }">
					<textarea name="content" id="replyContent" cols="55" rows="3"
						placeholder="댓글을 입력해주세요."></textarea>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<div id="comment_area">
			<div>
				<c:forEach var="reply" items="${ Article.replies }">
					<sub class="comment-writer">${ reply.userNickname }</sub>
					<sub class="comment-date">${ reply.commentDate }</sub>
					<br>
					<span>${ reply.content }</span>
					<c:if
						test="${ not empty loginMember && loginMember.nickname == reply.userNickname}">
						<form action="${ path }/article/replyDelete" method="GET">
							<input type="hidden" name="articleNo" value="${ Article.no }">
							<input type="hidden" name="replyNo" value="${ reply.no }">
							<button class="btn-delete" id="btnReplyDelete">삭제</button>
						</form>
					</c:if>
				</c:forEach>
			</div>
			<div>댓글의 페이지 바가 표시될 영역입니다.</div>
		</div>
		<div id="other_articles_area">
			다른 상품들이 출력될 자리입니다.
			<div id="other_item"></div>
		</div>
	</section>
</article>

<jsp:include page="/views/common/footer.jsp" />

