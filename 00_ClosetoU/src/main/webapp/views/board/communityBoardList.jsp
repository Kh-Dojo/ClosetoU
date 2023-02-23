<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>

<link rel="stylesheet" href="${ path }/resources/css/communityBoardList.css" />
	<section>
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/communitySideMenu.jsp" /></div>
<article id="content">
	<h2 align="center">게시판 </h2>
	
	<div id="board-list-container">
<!-- 230214 2교시 게시판 글쓰기 버튼 로그인 했을 때만 나타나게 만들기 -->
		<c:if test="${ not empty loginMember }">
<!-- 230214 4교시 게시판에서 글쓰기 버튼 누르면 글 작성페이지로 -->
			<button type="button" onclick="location.href='${ path }/article/communityWrite'">글쓰기</button>
		</c:if>	

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
<!-- 				<th>첨부파일</th> -->
				<th>조회수</th>
			</tr>
<!-- 230214 2교시 게시글 목록 가져오기 각 게시글을 리스트에 담기 -->
			<!-- list 가 비어있으면 아래와 같이 화면을 그리겠다 url의 list?page=숫자에 14 이상의 값 넣어보기-->
			<c:if test="${ empty list }">
				<tr>
					<td colspan="5">
						조회된 게시글이 없습니다.
					</td>
				</tr>	
			</c:if>
			<!-- list 가 비어있지 않으면 아래와 같이 화면을 그리겠다. 조회된 게시글 갯수만큼 그려줌 -->
			<c:if test="${ not empty list }">
				<c:forEach var="article" items="${ list }">
					<tr>
						<td>${ article.rowNum }</td>		<!--순번 -->
						<td>
<!-- 230214 2교시 게시판에서 제목 클릭시 게시글 상세페이지 나타나게 링크 걸기 -->
							<c:if test="${ article.type eq '공지' }">
								<a href="${ path }/communityBoardView?no=${ article.no }">
									<span>[${ article.type }]</span> ${ article.title }
								</a>
							</c:if>
							<c:if test="${ article.type eq '자유' }">
								<a href="${ path }/communityBoardView?no=${ article.no }">
									${ article.title }
									<c:if test="${ not empty board.originalFileName }">
										<img width="20px" src="${ path }/resources/images/file.png">		<!-- path의 / = webapp -->
									</c:if>
								</a>
							</c:if>
						</td>		<!-- 제목 -->
						<td>${ article.userNickname }</td>	<!-- 작성자 -->
						<td>${ article.postDate }</td>	<!-- 작성일 -->
						<%-- <td>
<!-- 230214 6교시 게시판의 게시글에 첨부파일이 있으면 게시판 메인페이지에 보이게 만들기 -->
							<c:if test="${ empty board.originalFileName }">
								<span> - </span>
							</c:if>
							<c:if test="${ not empty board.originalFileName }">
								<img width="20px" src="${ path }/resources/images/file.png">		<!-- path의 / = webapp -->
							</c:if>
						</td> --%>
						<td>${ article.readCount }</td>	<!-- 조회수 --> 
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div id="pageBar">
<!-- 230213 8교시 게시판 페이지 설정 맨 처음으로 ~ 맨 끝으로 onclick= 설정으로 url에 페이지값을 넘길 수 있음-->
			<!-- 맨 처음으로 -->
			<button onclick="location.href= '${ path }/board/communityBoardList?page=1'">&lt;&lt;</button>

			<!-- 이전 페이지로 -->
			<button onclick="location.href= '${ path }/board/communityBoardList?page=${ pageInfo.prevPage }'">&lt;</button>

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
						<button onclick="location.href= '${ path }/board/communityBoardList?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지로 -->
			<button onclick="location.href= '${ path }/board/communityBoardList?page=${ pageInfo.nextPage }'">&gt;</button>

			<!-- 맨 끝으로 -->
			<button onclick="location.href= '${ path }/board/communityBoardList?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
		</div>
	</div>
</article>

<%-- 	<script src="${ path }/resources/js/communityBoardList.js"></script> --%>
</section>

<jsp:include page="/views/common/footer.jsp" />

