<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/sub-header.jsp" />
<link rel="stylesheet" href="${ path }/resources/css/myComment.css">
<section>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/myPageSideMenu.jsp" /></div>
	
	<article>
		<h2 align="center">나의 댓글 </h2>
		<div id="board-list-container">

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>게시글 번호</th>
				<th>댓글 내용</th>
				<th>댓글 작성일자</th>
			</tr>
<!-- 게시글 목록 가져오기 각 게시글을 리스트에 담기 -->
			<!-- list 가 비어있으면 아래와 같이 화면을 그리겠다 url의 list?page=숫자에 14 이상의 값 넣어보기-->
			<c:if test="${ empty list }">
				<tr>
					<td colspan="4">
						조회된 댓글이 없습니다.
					</td>
				</tr>	
			</c:if>

			<c:if test="${ not empty list }">
				<c:forEach var="reply" items="${ list }">
					<tr>
						<td>${ reply.rowNum }</td>		<!--순번 -->
						<td>${ reply.article_no }</td>  <!--게시글 번호-->
						<td>${ reply.content }</td>		<!-- 내용 -->
						<td>${ reply.comment_date }</td>	<!-- 작성일 -->
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div id="pageBar">
<!-- 게시판 페이지 설정 맨 처음으로 ~ 맨 끝으로 onclick= 설정으로 url에 페이지값을 넘길 수 있음-->
			<!-- 맨 처음으로 -->
			<button onclick="location.href= '${ path }/myComment?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href= '${ path }/myComment?page=${ pageInfo.prevPage }'">&lt;</button>

			<!--  10개 페이지 목록 8교시 페이지 버튼 누르면 버튼 disabled상태 되고 해당 페이지로 이동-->
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
						<!--  PageInfo.java의 getStartPage()에서부터 getEndPage()까지 반복
								게터(getStartPage()...)를 통해 가져옴.
								네이밍 규칙만 맞으면 마치 필드에 접근하는 것처럼 필드를 접근해서 가지고 오는 것처럼 만들 수 있다.-->	
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href= '${ path }/myComment?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href= '${ path }/myComment?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href= '${ path }/myComment?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>
	</article>

</section>
<jsp:include page="/views/common/footer.jsp" />

</body>
</html>