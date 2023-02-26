<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/sub-header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/communityBoardList.css" />

	<section id="board-list-section">
	<div id="sidemenu"><jsp:include page="/views/common/sidemenu/communitySideMenu.jsp" /></div>
	<article id="content">
		<h2>자유로운 수다방</h2>
		<div id="board-list-container">
			<c:if test="${ not empty loginMember }">
				<button type="button" id="btn-add" onclick="location.href='${ path }/article/communityWrite'">글쓰기</button><br>
			</c:if>	
	
			<table id="tbl-board">
				<tr id="board-header">
					<th></th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
	 			<c:if test="${ empty list }">
					<tr>
						<td colspan="5">
							조회된 게시글이 없습니다.
						</td>
					</tr>	
				</c:if>
				<c:if test="${ not empty list }">
					<c:forEach var="article" items="${ list }">
						<tr>
							<td id="td-no">${ article.no }</td>		<!--순번 -->
							<td id="td-title">	<!-- 제목 -->
								<c:if test="${ article.type eq '공지' }">
									<a href="${ path }/communityArticleView?no=${ article.no }" class="title-td-a">
										<span>[${ article.type }]</span> ${ article.title }
										<c:if test="${ not empty article.originalFileName }">
											<img width="20px" src="${ path }/resources/img/iconForBoardTitle.png">
										</c:if>
									</a>
								</c:if>
								<c:if test="${ article.type eq '자유' }">
									<a href="${ path }/communityArticleView?no=${ article.no }" class="title-td-a">
										${ article.title }
										<c:if test="${ not empty article.originalFileName }">
											<img width="20px" src="${ path }/resources/img/iconForBoardTitle.png">
										</c:if>
									</a>
								</c:if>
							</td>
							<td id="td-nickname">${ article.userNickname }</td>	<!-- 작성자 -->
							<td id="td-date">${ article.postDate }</td>	<!-- 작성일 -->
							<td id="td-viewcount">${ article.readCount }</td>	<!-- 조회수 --> 
						</tr>
					</c:forEach>
				</c:if>
				
			</table>
<!-- 댓글검색창 -->
			<div id="searchBar">
				<form name="articleSearch" action="${ path }/article/articleSearch" post="GET">
					<table id="table-articleSearch">
						<tr>
							<td><input type="text" id="input-articleSearch" name="input-articleSearch"></td>
							<td><input type="submit" id="btn-articleSearch" name="btn-articleSearch" value="검색"></td>
						</tr>
					</table>
				</form>
			</div>
<!-- 게시판 페이지 설정 맨 처음으로 ~ 맨 끝으로 -->
			<div id="pageBar">
				<!-- 맨 처음으로 -->
				<button onclick="location.href= '${ path }/board/communityBoardList?page=1'">&lt;&lt;</button>
				<!-- 이전 페이지로 -->
				<button onclick="location.href= '${ path }/board/communityBoardList?page=${ pageInfo.prevPage }'">&lt;</button>
				<!--  10개 페이지 목록 8교시 페이지 버튼 누르면 버튼 disabled상태 되고 해당 페이지로 이동-->
				<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">	
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
			<br><br><br><br>
		</div>
	</article>
<%-- 	<script src="${ path }/resources/js/communityBoardList.js"></script> --%>
</section>


<jsp:include page="/views/common/footer.jsp" />

